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
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;

import java.util.Collection;

public class Neo4jApiTransformationInjectRouteSensor extends Neo4jApiTransformation<Neo4jRouteSensorInjectMatch> {

	public Neo4jApiTransformationInjectRouteSensor(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jRouteSensorInjectMatch> matches) {
		Transaction tx = Neo4jDriver.getTmpTransaction();
		for (final Neo4jRouteSensorInjectMatch match : matches) {
			Node route = tx.getNodeByElementId(match.getRoute());
			Node sensor = tx.getNodeByElementId(match.getSensor());
			try (final ResourceIterable<Relationship> requiress = route.getRelationships(Neo4jConstants.relationshipTypeRequires)) {
				for (final Relationship requires : requiress) {
					if (requires.getEndNode().equals(sensor)) {
						requires.delete();
					}
				}
			}
		}
	}

}
