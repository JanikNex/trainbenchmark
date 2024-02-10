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

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.*;

public class Neo4jSwitchSetMatch extends Neo4jMatch implements SwitchSetMatch {

	public Neo4jSwitchSetMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public String getSemaphore() {
		return (String) match.get(VAR_SEMAPHORE);
	}

	@Override
	public String getRoute() {
		return (String) match.get(VAR_ROUTE);
	}

	@Override
	public String getSwP() {
		return (String) match.get(VAR_SWP);
	}

	@Override
	public String getSw() {
		return (String) match.get(VAR_SW);
	}

	public String getCurrentPosition() {
		return (String) match.get(QueryConstants.VAR_CURRENTPOSITION);
	}

	public String getPosition() {
		return (String) match.get(QueryConstants.VAR_POSITION);
	}

}
