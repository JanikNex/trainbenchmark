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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;

import java.util.Map;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;

public class Neo4jConnectedSegmentsMatch extends Neo4jMatch implements ConnectedSegmentsMatch {

	public Neo4jConnectedSegmentsMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public String getSensor() {
		return (String) match.get(VAR_SENSOR);
	}

	@Override
	public String getSegment1() {
		return (String) match.get(VAR_SEGMENT1);
	}

	@Override
	public String getSegment2() {
		return (String) match.get(VAR_SEGMENT2);
	}

	@Override
	public String getSegment3() {
		return (String) match.get(VAR_SEGMENT3);
	}

	@Override
	public String getSegment4() {
		return (String) match.get(VAR_SEGMENT4);
	}

	@Override
	public String getSegment5() {
		return (String) match.get(VAR_SEGMENT5);
	}

	@Override
	public String getSegment6() {
		return (String) match.get(VAR_SEGMENT6);
	}

}
