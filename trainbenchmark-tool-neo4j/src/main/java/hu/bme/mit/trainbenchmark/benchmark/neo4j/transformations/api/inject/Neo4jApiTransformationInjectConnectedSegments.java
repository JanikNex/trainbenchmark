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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.*;

import java.util.Collection;

public class Neo4jApiTransformationInjectConnectedSegments
	extends Neo4jApiTransformation<Neo4jConnectedSegmentsInjectMatch> {

	public Neo4jApiTransformationInjectConnectedSegments(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jConnectedSegmentsInjectMatch> matches) {
		Transaction tx = Neo4jDriver.getTmpTransaction();
		for (final Neo4jConnectedSegmentsInjectMatch match : matches) {
			// create (segment2) node
			final Node segment2 = tx.createNode(Neo4jConstants.labelSegment);
			segment2.setProperty(ModelConstants.ID, driver.generateNewVertexId());
			segment2.setProperty(ModelConstants.LENGTH, TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH);

			// (segment2)-[:monitoredBy]->(sensor)
			Node sensorNode = tx.getNodeByElementId(match.getSensor());
			segment2.createRelationshipTo(sensorNode, Neo4jConstants.relationshipTypeMonitoredBy);

			// (segment1)-[:connectsTo]->(segment2)
			Node segment1 = tx.getNodeByElementId(match.getSegment1());
			segment1.createRelationshipTo(segment2, Neo4jConstants.relationshipTypeConnectsTo);
			// (segment2)-[:connectsTo]->(segment3)
			Node segment3 = tx.getNodeByElementId(match.getSegment3());
			segment2.createRelationshipTo(segment3, Neo4jConstants.relationshipTypeConnectsTo);

			// remove (segment1)-[:connectsTo]->(segment3)
			final ResourceIterable<Relationship> connectsToEdges = segment1.getRelationships(Direction.OUTGOING,
				Neo4jConstants.relationshipTypeConnectsTo);
			for (final Relationship connectsToEdge : connectsToEdges) {
				if (connectsToEdge.getEndNode().getElementId().equals(match.getSegment3())) {
					connectsToEdge.delete();
				}
			}
			connectsToEdges.close();
		}
	}
}
