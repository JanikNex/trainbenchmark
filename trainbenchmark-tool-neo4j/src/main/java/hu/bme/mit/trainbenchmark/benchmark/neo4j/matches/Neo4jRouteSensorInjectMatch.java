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

import java.util.Map;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

public class Neo4jRouteSensorInjectMatch extends Neo4jMatch implements RouteSensorInjectMatch {

	public Neo4jRouteSensorInjectMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public String getRoute() {
		return (String) match.get(QueryConstants.VAR_ROUTE);
	}

	@Override
	public String getSensor() {
		return (String) match.get(QueryConstants.VAR_SENSOR);
	}

}
