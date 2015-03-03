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
package hu.bme.mit.trainbenchmark.benchmark.jena.driver;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class JenaDriver extends DatabaseDriver<Resource> {

	protected long newVertexId = 1000000000;

	protected String basePrefix;
	protected Model model;
	protected Query query;
	protected String resultVar;

	public JenaDriver(final String basePrefix, final String queryPath) {
		this.basePrefix = basePrefix;
		query = QueryFactory.read(queryPath);
		resultVar = query.getResultVars().get(0);
	}

	@Override
	public void read(final String modelPath) throws IOException {
		model = ModelFactory.createDefaultModel();
		model.read(modelPath);
	}

	@Override
	public List<Resource> runQuery() throws IOException {
		final List<Resource> results = new ArrayList<>();
		try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
			final ResultSet resultSet = queryExecution.execSelect();

			while (resultSet.hasNext()) {
				final QuerySolution qs = resultSet.next();
				final Resource resource = qs.getResource(resultVar);
				results.add(resource);
			}
		}

		return results;
	}

	@Override
	public Comparator<Resource> getComparator() {
		return new JenaComparator();
	}

	@Override
	public void destroy() throws IOException {
		model.close();
	}

	// filter
	
	@Override
	public List<Resource> filterVertices(List<Resource> vertices,
			String vertexType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// create

	@Override
	public void insertVertexWithEdge(final List<Resource> sourceVertices, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		final Property edge = model.getProperty(basePrefix + edgeType);
		final Resource vertexType = model.getResource(basePrefix + targetVertexType);

		for (final Resource sourceVertex : sourceVertices) {
			final Resource targetVertex = model.createResource(basePrefix + "x" + newVertexId);
			newVertexId++;

			model.add(model.createStatement(sourceVertex, edge, targetVertex));
			model.add(model.createStatement(targetVertex, RDF.type, vertexType));
		}
	}

	@Override
	public Resource insertVertexWithEdge(Resource sourceVertex,
			String sourceVertexType, String targetVertexType, String edgeType)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEdge(Resource sourceVertex, Resource targetVertex,
			String edgeType) {
		// TODO Auto-generated method stub
		
	}
	
	// read

	@Override
	public List<Resource> collectVertices(final String type) throws IOException {
		final ResIterator vertexStatements = model.listSubjectsWithProperty(RDF.type, model.getResource(RDFConstants.BASE_PREFIX + type));
		final List<Resource> vertices = vertexStatements.toList();
		return vertices;
	}

	@Override
	public List<Resource> collectOutgoingConnectedVertices(
			Resource sourceVertex, String edgeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> collectOutgoingFilteredConnectedVertices(
			Resource sourceVertex, String targetVertexType, String edgeType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// update

	@Override
	public void updateProperties(final List<Resource> vertices, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) throws IOException {
		final Property property = model.getProperty(basePrefix + propertyName);

		for (final Resource vertex : vertices) {
			final Selector selector = new SimpleSelector(vertex, property, (RDFNode) null);
			final StmtIterator statementsToRemove = model.listStatements(selector);
			if (statementsToRemove.hasNext()) {
				final Statement oldStatement = statementsToRemove.next();
				final Integer value = oldStatement.getInt();
				final Statement newStatement = model.createLiteralStatement(vertex, property, attributeOperation.op(value));
				model.remove(oldStatement);
				model.add(newStatement);
			}
		}
	}

	// delete

	@Override
	public void deleteAllIncomingEdges(final List<Resource> vertices, final String sourceVertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, false, true);
	}

	@Override
	public void deleteAllOutgoingEdges(final List<Resource> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	@Override
	public void deleteOneOutgoingEdge(final List<Resource> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, false);
	}

	@Override
	public void deleteSingleOutgoingEdge(final List<Resource> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	protected void deleteEdges(final List<Resource> vertices, final String edgeType, final boolean outgoing, final boolean all) {
		final Property property = model.getProperty(RDFConstants.BASE_PREFIX + edgeType);

		for (final Resource vertex : vertices) {
			final Selector selector = outgoing ? new SimpleSelector(vertex, property, (RDFNode) null) //
					: new SimpleSelector(null, property, vertex);

			final StmtIterator edges = model.listStatements(selector);

			final List<Statement> statementsToRemove = new ArrayList<>();
			while (edges.hasNext()) {
				final Statement statementToRemove = edges.next();

				statementsToRemove.add(statementToRemove);

				if (!all) {
					break;
				}
			}

			for (final Statement statement : statementsToRemove) {
				model.remove(statement);
			}
		}
	}

}
