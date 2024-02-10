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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.BaseMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Arrays;
import java.util.Map;

public abstract class Neo4jMatch extends BaseMatch {

	protected final Map<String, Object> match;

	public Neo4jMatch(final Map<String, Object> match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Neo4jMatch [match=" + Arrays.toString(toArray()) + "]";
	}

	public static Neo4jMatch createMatch(final RailwayQuery query, final Map<String, Object> match) {
        return switch (query) {
            case ACTIVEROUTE -> new Neo4jActiveRouteMatch(match);
            case CONNECTEDSEGMENTS -> new Neo4jConnectedSegmentsMatch(match);
            case CONNECTEDSEGMENTS_INJECT -> new Neo4jConnectedSegmentsInjectMatch(match);
            case POSLENGTH -> new Neo4jPosLengthMatch(match);
            case POSLENGTH_INJECT -> new Neo4jPosLengthInjectMatch(match);
            case ROUTELENGTH -> new Neo4jRouteLengthMatch(match);
            case ROUTEREACHABILITY -> new Neo4jRouteReachabilityMatch(match);
            case ROUTESENSOR -> new Neo4jRouteSensorMatch(match);
            case ROUTESENSOR_INJECT -> new Neo4jRouteSensorInjectMatch(match);
            case SEMAPHORENEIGHBOR -> new Neo4jSemaphoreNeighborMatch(match);
            case SEMAPHORENEIGHBOR_INJECT -> new Neo4jSemaphoreNeighborInjectMatch(match);
            case SWITCHMONITORED -> new Neo4jSwitchMonitoredMatch(match);
            case SWITCHMONITORED_INJECT -> new Neo4jSwitchMonitoredInjectMatch(match);
            case SWITCHSET -> new Neo4jSwitchSetMatch(match);
            case SWITCHSET_INJECT -> new Neo4jSwitchSetInjectMatch(match);
            default -> throw new UnsupportedOperationException("Query not supported: " + query);
        };
	}

}
