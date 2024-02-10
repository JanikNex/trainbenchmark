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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;

import java.util.Collection;

public class Neo4jApiTransformationRepairConnectedSegments extends Neo4jApiTransformation<Neo4jConnectedSegmentsMatch> {

	public Neo4jApiTransformationRepairConnectedSegments(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jConnectedSegmentsMatch> matches) {
		Transaction tx = Neo4jDriver.getTmpTransaction();
		for (final Neo4jConnectedSegmentsMatch match : matches) {
			// delete segment2 with all its relationships
			final Node segment2 = tx.getNodeByElementId(match.getSegment2());
			try (final ResourceIterable<Relationship> relationships = segment2.getRelationships()) {
				for (final Relationship relationship : relationships) {
					relationship.delete();
				}
			}
			segment2.delete();
			// (segment1)-[:connectsTo]->(segment3)
			final Node segment1 = tx.getNodeByElementId(match.getSegment1());
			final Node segment3 = tx.getNodeByElementId(match.getSegment3());
			segment1.createRelationshipTo(segment3, Neo4jConstants.relationshipTypeConnectsTo);
		}
	}

}
