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
package hu.bme.mit.trainbenchmark.generator.graph.neo4j;

import apoc.export.csv.ExportCSV;
import apoc.export.graphml.ExportGraphML;
import apoc.graph.Graphs;
import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfig;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jHelper;
import hu.bme.mit.trainbenchmark.neo4j.apoc.ApocHelper;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import org.apache.commons.io.FileUtils;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.exceptions.KernelException;
import org.neo4j.graphdb.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Map.Entry;

import static org.neo4j.configuration.GraphDatabaseSettings.DEFAULT_DATABASE_NAME;

public class Neo4jGraphSerializer extends ModelSerializer<Neo4jGraphGeneratorConfig> {

	protected GraphDatabaseService graphDb;
	protected DatabaseManagementService dbms;

	protected Transaction tx;
	protected final File databaseDirectory;

	public Neo4jGraphSerializer(final Neo4jGraphGeneratorConfig generatorConfig) {
		super(generatorConfig);
		databaseDirectory = new File(generatorConfig.getConfigBase().getModelDir() + "neo4j-gen/"
			+ generatorConfig.getConfigBase().getModelFileNameWithoutExtension() + ".neo4j");
	}

	@Override
	public String syntax() {
		return "Neo4j graph " + gc.getGraphFormat();
	}

	@Override
	public void initModel() throws IOException {
		cleanupDatabaseDirectory();
		dbms = Neo4jHelper.startGraphDatabase(Neo4jDeployment.EMBEDDED, databaseDirectory);
		graphDb = dbms.database(DEFAULT_DATABASE_NAME);
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
							   final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) {
		try (Transaction tx = graphDb.beginTx()) {
			final Node node = tx.createNode(Label.label(type));

			// this only works for inheritance hierarchies
			if (ModelConstants.SUPERTYPES.containsKey(type)) {
				final String ancestor = ModelConstants.SUPERTYPES.get(type);
				node.addLabel(Label.label(ancestor));
			}

			node.setProperty(ModelConstants.ID, id);
			for (final Entry<String, ? extends Object> attribute : attributes.entrySet()) {
				final String key = attribute.getKey();
				Object value = attribute.getValue();

				// convert the value to string if it's an enum
				value = enumsToString(value);
				node.setProperty(key, value);
			}

			for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
				final String label = outgoingEdge.getKey();
				if (outgoingEdge.getValue() instanceof Node targetNode) {
					node.createRelationshipTo(targetNode, relationship(label));
				}
			}

			for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
				final String label = incomingEdge.getKey();
				if (incomingEdge.getValue() instanceof Node sourceNode) {
					sourceNode.createRelationshipTo(node, relationship(label));
				}
			}

			tx.commit();

			return node;
		}
	}

	private Object enumsToString(Object value) {
		if (value instanceof Enum<?> e) {
			value = e.toString();
		}
		return value;
	}

	@Override
	public void createEdge(final String label, final Object from, final Object to) {
		if (from == null || to == null) {
			return;
		}

		final Node source = (Node) from;
		final Node target = (Node) to;


		try (Transaction tx = graphDb.beginTx()) {
			final Node txSourceNode = tx.getNodeByElementId(source.getElementId());
			final Node txTargetNode = tx.getNodeByElementId(target.getElementId());
			final RelationshipType relationshipType = relationship(label);
			txSourceNode.createRelationshipTo(txTargetNode, relationshipType);
			tx.commit();
		}

	}

	public RelationshipType relationship(final String label) {
		return RelationshipType.withName(label);
	}

	@Override
	public void persistModel() throws IOException {
//		try (Transaction tx = this.graphDb.beginTx()) {
//			System.out.println(String.format("Database contains %d nodes!", Math.toIntExact(tx.getAllNodes().stream().count())));
//		}

		createExportDirectory();

		try {
			switch (gc.getGraphFormat()) {
				case CSV:
					saveToCsv();
					break;
				case GRAPHML:
					saveToGraphMl();
					break;
				default:
					throw new UnsupportedOperationException("Graph format " + gc.getGraphFormat() + " not supported.");
			}
		} catch (KernelException kex) {
			kex.printStackTrace();
		} finally {
			dbms.shutdown();

			// cleanup: delete the database directory
			cleanupDatabaseDirectory();
		}
	}

	private Path getExportDirectory() {
		try {
			return this.databaseDirectory.getCanonicalFile().toPath().getParent().getParent().resolve("neo4j-export");
		} catch (IOException e) {
			throw new RuntimeException("Could not get canonical file: " + this.databaseDirectory.toString());
		}
	}

	private void createExportDirectory() {
		Path exportTargetPath = getExportDirectory();
		try {
			if (!exportTargetPath.toFile().exists()) {
				Files.createDirectory(exportTargetPath);
			}
		} catch (IOException ex) {
			throw new RuntimeException("Could not create directory: " + exportTargetPath.toString());
		}
	}

	private void createExportFile(Path target) {
		try {
			if (!target.toFile().exists()) {
				Files.createFile(target);
			}
		} catch (IOException ex) {
			throw new RuntimeException("Could not create file: " + target.toString());
		}
	}

	private void saveToCsv() throws KernelException {
		ApocHelper.registerProcedure(graphDb, ExportCSV.class, Graphs.class);
		ApocHelper.updateApocConfig();

		final Map<String, String> exportCommands = ImmutableMap.<String, String>builder()
			// nodes
			.put(ModelConstants.REGION, "MATCH (n:Region)              RETURN n.id AS `id:ID`") //
			.put(ModelConstants.ROUTE, "MATCH (n:Route)               RETURN n.id AS `id:ID`, n.active AS `active:BOOLEAN`") //
			.put(ModelConstants.SEGMENT, "MATCH (n:Segment)             RETURN n.id AS `id:ID`, n.length AS `length:INT`") //
			.put(ModelConstants.SEMAPHORE, "MATCH (n:Semaphore)           RETURN n.id AS `id:ID`, n.signal AS signal") //
			.put(ModelConstants.SENSOR, "MATCH (n:Sensor)              RETURN n.id AS `id:ID`") //
			.put(ModelConstants.SWITCH, "MATCH (n:Switch)              RETURN n.id AS `id:ID`, n.currentPosition AS currentPosition") //
			.put(ModelConstants.SWITCHPOSITION, "MATCH (n:SwitchPosition)      RETURN n.id AS `id:ID`, n.position AS position") //
			// relationships
			.put(ModelConstants.CONNECTS_TO, "MATCH (n)-[:connectsTo]->(m)  RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
			.put(ModelConstants.ENTRY, "MATCH (n)-[:entry]->(m)       RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
			.put(ModelConstants.EXIT, "MATCH (n)-[:exit]->(m)        RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
			.put(ModelConstants.FOLLOWS, "MATCH (n)-[:follows]->(m)     RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
			.put(ModelConstants.MONITORED_BY, "MATCH (n)-[:monitoredBy]->(m) RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
			.put(ModelConstants.REQUIRES, "MATCH (n)-[:requires]->(m)    RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
			.put(ModelConstants.TARGET, "MATCH (n)-[:target]->(m)      RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
			.build();


		for (Entry<String, String> entry : exportCommands.entrySet()) {
			final String type = entry.getKey();
			final String query = entry.getValue();

			final String fileName = gc.getConfigBase().getModelFileNameWithoutExtension() + "-" + type + "."
				+ Neo4jConstants.CSV_EXTENSION;

			//System.out.println("Export CSV to: " + fileName);
			Path exportDirectory = getExportDirectory();

			try (Transaction tx = graphDb.beginTx()) {
				//System.out.println("Calling csv export...");
				Path csvPath = exportDirectory.resolve(fileName);
				createExportFile(csvPath);
				//System.out.println("CSV target: " + csvPath.toString());

				Result res = tx.execute(String.format( //
					"CALL apoc.export.csv.query('%s', '%s', null)", //
					query,
					csvPath.toString() //
				));

				System.out.println(res.resultAsString());
			}
		}
	}

	private void saveToGraphMl() throws KernelException {
		ApocHelper.registerProcedure(graphDb, ExportGraphML.class, Graphs.class);
		ApocHelper.updateApocConfig();

		final String fileName = gc.getConfigBase().getModelFileNameWithoutExtension() + Neo4jConstants.GRAPHML_POSTFIX;

		Path exportDirectory = getExportDirectory();
		Path graphmlPath = exportDirectory.resolve(fileName);
		createExportFile(graphmlPath);

		try (Transaction tx = graphDb.beginTx()) {
			Result res = tx.execute(String.format( //
				"CALL apoc.export.graphml.all('%s', {useTypes: true})", //
				graphmlPath.toString() //
			));

			System.out.println(res.resultAsString());
		}
	}

	private void cleanupDatabaseDirectory() throws IOException {
		if (databaseDirectory.exists()) {
			FileUtils.deleteDirectory(databaseDirectory);
		}
	}

	@Override
	public void beginTransaction() {
		tx = graphDb.beginTx();
	}

	@Override
	public void endTransaction() {
		tx.commit();
		tx.close();
	}

}
