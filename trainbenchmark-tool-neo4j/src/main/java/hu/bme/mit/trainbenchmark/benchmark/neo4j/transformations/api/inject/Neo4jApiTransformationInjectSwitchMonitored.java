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
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;

import java.util.Collection;

public class Neo4jApiTransformationInjectSwitchMonitored
	extends Neo4jApiTransformation<Neo4jSwitchMonitoredInjectMatch> {

	public Neo4jApiTransformationInjectSwitchMonitored(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jSwitchMonitoredInjectMatch> matches) {
		Transaction tx = Neo4jDriver.getTmpTransaction();
		for (final Neo4jSwitchMonitoredInjectMatch match : matches) {
			Node sw = tx.getNodeByElementId(match.getSw());
			try(final ResourceIterable<Relationship> monitoredBys = sw.getRelationships(Neo4jConstants.relationshipTypeMonitoredBy)) {
				for (final Relationship monitoredBy : monitoredBys) {
					monitoredBy.delete();
				}
			}
		}
	}

}
