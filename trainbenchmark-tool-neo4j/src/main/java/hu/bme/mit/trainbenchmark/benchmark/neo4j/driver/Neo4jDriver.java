/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import apoc.export.graphml.ExportGraphML;
import apoc.graph.Graphs;
import com.google.common.collect.Maps;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jHelper;
import hu.bme.mit.trainbenchmark.neo4j.apoc.ApocHelper;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;
import org.apache.commons.io.FileUtils;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.exceptions.KernelException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.schema.Schema;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.neo4j.configuration.GraphDatabaseSettings.DEFAULT_DATABASE_NAME;

public class Neo4jDriver extends Driver {

	private static Transaction TMP_TRANSACTION;
	protected GraphDatabaseService graphDb;
	protected DatabaseManagementService dbms;
	protected final Neo4jDeployment deployment;
	protected final Neo4jGraphFormat graphFormat;
	protected final String NEO4J_HOME = "../neo4j-server/";
	protected final File databaseDirectory;

	public Neo4jDriver(final String modelDir, final Neo4jDeployment deployment, final Neo4jGraphFormat graphFormat) throws IOException {
		super();
		this.deployment = deployment;
		this.graphFormat = graphFormat;

		final String dbPath = switch (graphFormat) {
			case CSV -> NEO4J_HOME + "data/databases/railway-database";
			case GRAPHML, CYPHER -> modelDir + "/neo4j-dbs/railway-database";
		};
		this.databaseDirectory = new File(dbPath);
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();

		if (deployment == Neo4jDeployment.EMBEDDED) {
			// delete old database directory
			if (databaseDirectory.exists()) {
				FileUtils.deleteDirectory(databaseDirectory);
			}
		}
	}

	@Override
	public void destroy() {
		if (dbms != null) {
			dbms.shutdown();
		}
	}

	@Override
	public void beginTransaction() {
		TMP_TRANSACTION = graphDb.beginTx();
	}

	@Override
	public void finishTransaction() {
		TMP_TRANSACTION.commit();
		TMP_TRANSACTION.close();
	}

	public static Transaction getTmpTransaction() {
		if (TMP_TRANSACTION == null) {
			throw new RuntimeException("Tried to request temporary transaction but has not been initialized!");
		}
		return TMP_TRANSACTION;
	}

	@Override
	public void read(final String modelPathStr)
		throws XMLStreamException, IOException, KernelException {

		System.out.println("[Neo4jDriver] Format: " + graphFormat.toString() + " | ModelPath: " + modelPathStr);

		Path modelPath = Path.of(modelPathStr);
		Path modelDirectory = modelPath.toFile().getCanonicalFile().toPath().getParent();
		Path fileName = modelPath.getFileName();
		Path truePath = modelDirectory.resolve("neo4j-export").resolve(fileName);
		String updatedModelPath = truePath.toString();

		switch (graphFormat) {
			case CSV:
				readCsv(updatedModelPath);
				break;
			case GRAPHML:
				readGraphMl(updatedModelPath);
				break;
			case CYPHER:
				readCypher(updatedModelPath);
				break;
			default:
				throw new UnsupportedOperationException("Format " + graphFormat + " not supported");
		}
	}

	private void startDb() {
		if (graphFormat == Neo4jGraphFormat.CSV) {
			dbms = Neo4jHelper.startGraphDatabase(deployment, databaseDirectory.getParentFile().getParentFile().getParentFile());
		} else {
			dbms = Neo4jHelper.startGraphDatabase(deployment, databaseDirectory);
		}
		graphDb = dbms.database(DEFAULT_DATABASE_NAME);

		try (final Transaction tx = graphDb.beginTx()) {
			final Schema schema = tx.schema();
			schema.indexFor(Neo4jConstants.labelSegment).on(ModelConstants.ID).create();
			schema.indexFor(Neo4jConstants.labelSegment).on(ModelConstants.LENGTH).create();
			schema.indexFor(Neo4jConstants.labelSemaphore).on(ModelConstants.SIGNAL).create();
			schema.indexFor(Neo4jConstants.labelRoute).on(ModelConstants.ACTIVE).create();
			tx.commit();
		}
		try (final Transaction tx = graphDb.beginTx()) {
			final Schema schema = tx.schema();
			schema.awaitIndexesOnline(5, TimeUnit.MINUTES);
		}
	}

	private void readCsv(String modelPath) throws IOException {
		if (databaseDirectory.exists()) {
			FileUtils.deleteDirectory(databaseDirectory);
		}

		final ArrayList<String> importCommand = new ArrayList<>(List.of(NEO4J_HOME + "bin/neo4j-admin", "database", "import", "full"));
		importCommand.add("--id-type=INTEGER");
		importCommand.add("--overwrite-destination=true");
		importCommand.add(String.format("--nodes=Region=%s-Region.csv", modelPath));
		importCommand.add(String.format("--nodes=Route=%s-Route.csv", modelPath));
		importCommand.add(String.format("--nodes=Segment:TrackElement=%s-Segment.csv", modelPath));
		importCommand.add(String.format("--nodes=Semaphore=%s-Semaphore.csv", modelPath));
		importCommand.add(String.format("--nodes=Sensor=%s-Sensor.csv", modelPath));
		importCommand.add(String.format("--nodes=Switch:TrackElement=%s-Switch.csv", modelPath));
		importCommand.add(String.format("--nodes=SwitchPosition=%s-SwitchPosition.csv", modelPath));
		importCommand.add(String.format("--relationships=connectsTo=%s-connectsTo.csv", modelPath));
		importCommand.add(String.format("--relationships=entry=%s-entry.csv", modelPath));
		importCommand.add(String.format("--relationships=exit=%s-exit.csv", modelPath));
		importCommand.add(String.format("--relationships=follows=%s-follows.csv", modelPath));
		importCommand.add(String.format("--relationships=monitoredBy=%s-monitoredBy.csv", modelPath));
		importCommand.add(String.format("--relationships=requires=%s-requires.csv", modelPath));
		importCommand.add(String.format("--relationships=target=%s-target.csv", modelPath));
		importCommand.add("neo4j");

		System.out.println("Running import command: " + String.join(" ", importCommand));

		try {
			ProcessBuilder builder = new ProcessBuilder(importCommand);
			Process process = builder.inheritIO().start();

			int exitCode = process.waitFor();
			if (exitCode != 0) {
				throw new IOException("Neo4j-admin process returned non-zero exit value: " + exitCode);
			}
		} catch (InterruptedException ex) {
			throw new RuntimeException("Interrupted while waiting for CSV import - Neo4j import failed!");
		}

		startDb();
	}

	private void readCypher(String modelPath) throws IOException {
		startDb();
		final File cypherFile = new File(modelPath);
		try (final Transaction t = graphDb.beginTx()) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(cypherFile));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				t.execute(line);
			}
			t.commit();
		}
	}

	private void readGraphMl(String modelPath) throws KernelException {
		startDb();

		ApocHelper.registerProcedure(graphDb, ExportGraphML.class, Graphs.class);
		ApocHelper.updateApocConfig();

		try (final Transaction tx = graphDb.beginTx()) {
			Result res = tx.execute(String.format( //
				"CALL apoc.import.graphml('%s', {batchSize: 10000, readLabels: true})", //
				modelPath //
			));

			System.out.println(res.resultAsString());

			tx.commit();
		}
	}

	@Override
	public String getPostfix() {
		return switch (graphFormat) {
			case CSV -> ""; // hack as we have multiple CSVs
			case GRAPHML -> Neo4jConstants.GRAPHML_POSTFIX;
			case CYPHER -> ".cypher";
		};
	}

	public Collection<Neo4jMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		final Collection<Neo4jMatch> results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			final Result executionResult = tx.execute(queryDefinition);
			while (executionResult.hasNext()) {
				final Map<String, Object> row = executionResult.next();
				final Map<String, Object> cleanedRow = clearMatchValues(row);
				results.add(Neo4jMatch.createMatch(query, cleanedRow));
			}
			tx.commit();
		}

		return results;
	}

	private Map<String, Object> clearMatchValues(Map<String, Object> match) {
		return Maps.transformValues(match, entry -> {
			if (entry instanceof Node node) {
				return node.getElementId();
			}
			return entry;
		});
	}

	public void runTransformation(final Transaction tx, final String transformationDefinition, final Map<String, Object> parameters)
		throws IOException {
		Result res = tx.execute(transformationDefinition, parameters);
		System.out.println(res.resultAsString());
	}

	// utility

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	public Long generateNewVertexId() {
		// Cypher's toInteger returns a Long
		final String GET_MAX_ID_QUERY = "MATCH (n) RETURN toInteger(max(n.id)) AS max";
		try (Transaction tx = graphDb.beginTx()) {
			Number res = (Number) tx.execute(GET_MAX_ID_QUERY).next().get("max");
			tx.commit();
			return res.longValue();
		}
	}

}
