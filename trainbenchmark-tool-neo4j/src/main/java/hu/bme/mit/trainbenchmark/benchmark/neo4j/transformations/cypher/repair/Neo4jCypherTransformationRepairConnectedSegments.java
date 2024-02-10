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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair;

import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCypherTransformation;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import org.neo4j.graphdb.Transaction;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class Neo4jCypherTransformationRepairConnectedSegments extends Neo4jCypherTransformation<Neo4jConnectedSegmentsMatch> {

	public Neo4jCypherTransformationRepairConnectedSegments(final Neo4jDriver driver, final String workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayOperation.CONNECTEDSEGMENTS_REPAIR);
	}

	@Override
	public void activate(final Collection<Neo4jConnectedSegmentsMatch> matches) throws IOException {
		Transaction tx = Neo4jDriver.getTmpTransaction();
		for (final Neo4jConnectedSegmentsMatch match : matches) {
			final Map<String, Object> parameters = ImmutableMap.of( //
				QueryConstants.VAR_SEGMENT2, tx.getNodeByElementId(match.getSegment2()) //
			);
			driver.runTransformation(tx, transformationDefinition, parameters);
		}
	}

}
