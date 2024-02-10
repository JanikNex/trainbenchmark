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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject;

import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCypherTransformation;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class Neo4jCypherTransformationInjectConnectedSegments extends Neo4jCypherTransformation<Neo4jConnectedSegmentsInjectMatch> {

	public Neo4jCypherTransformationInjectConnectedSegments(final Neo4jDriver driver, final String workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayOperation.CONNECTEDSEGMENTS_INJECT);
	}

	@Override
	public void activate(final Collection<Neo4jConnectedSegmentsInjectMatch> matches) throws IOException {
		Transaction tx = Neo4jDriver.getTmpTransaction();
		for (final Neo4jConnectedSegmentsInjectMatch match : matches) {
			Node sensor = tx.getNodeByElementId(match.getSensor());
			Node segment1 = tx.getNodeByElementId(match.getSegment1());
			Node segment3 = tx.getNodeByElementId(match.getSegment3());
			final Map<String, Object> parameters = ImmutableMap.of( //
				QueryConstants.VAR_ID, driver.generateNewVertexId(), //
				QueryConstants.VAR_SENSOR, sensor, //
				QueryConstants.VAR_SEGMENT1, segment1, //
				QueryConstants.VAR_SEGMENT3, segment3, //
				QueryConstants.VAR_LENGTH, TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH //
			);
			driver.runTransformation(tx, transformationDefinition, parameters);
		}
	}

}
