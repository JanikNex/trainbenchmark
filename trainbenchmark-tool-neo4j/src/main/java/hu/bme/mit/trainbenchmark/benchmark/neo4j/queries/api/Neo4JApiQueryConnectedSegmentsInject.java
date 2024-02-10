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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.*;

public class Neo4JApiQueryConnectedSegmentsInject extends Neo4jApiQuery<Neo4jConnectedSegmentsInjectMatch> {

	public Neo4JApiQueryConnectedSegmentsInject(final Neo4jDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS_INJECT, driver);
	}

	@Override
	public Collection<Neo4jConnectedSegmentsInjectMatch> evaluate() throws IOException {
		final Collection<Neo4jConnectedSegmentsInjectMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (final Transaction tx = graphDb.beginTx()) {
			// (sensor:Sensor)
			try (final ResourceIterator<Node> sensors = tx.findNodes(Neo4jConstants.labelSensor)) {
				for (final Node sensor : sensors.stream().toList()) {
					// (sensor:Sensor)<-[:sensor]-(segment1:Segment)
					final Iterable<Node> segment1s = Neo4jUtil.getAdjacentNodes(sensor, Neo4jConstants.relationshipTypeMonitoredBy, Direction.INCOMING,
						Neo4jConstants.labelSegment);

					for (final Node segment1 : segment1s) {
						// (segment1:Segment)-[:connectsTo]->(segment3:Segment)
						final Iterable<Node> segment3s = Neo4jUtil.getAdjacentNodes(segment1, Neo4jConstants.relationshipTypeConnectsTo, Direction.OUTGOING,
							Neo4jConstants.labelSegment);
						for (final Node segment3 : segment3s) {
							// (segment3:Segment)-[:sensor]->(sensor:Sensor)
							if (!Neo4jUtil.isConnected(segment3, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
								continue;
							}

							final Map<String, Object> match = new HashMap<>();
							match.put(VAR_SENSOR, sensor.getElementId());
							match.put(VAR_SEGMENT1, segment1.getElementId());
							match.put(VAR_SEGMENT3, segment3.getElementId());
							matches.add(new Neo4jConnectedSegmentsInjectMatch(match));
						}
					}
				}
			}
		}
		return matches;
	}

}
