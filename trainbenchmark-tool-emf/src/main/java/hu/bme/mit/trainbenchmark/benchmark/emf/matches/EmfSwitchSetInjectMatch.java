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
package hu.bme.mit.trainbenchmark.benchmark.emf.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EmfSwitchSetInjectMatch extends EmfMatch implements SwitchSetMatch {

	public EmfSwitchSetInjectMatch(final Semaphore semaphore, final Route route, final SwitchPosition switchPosition, final Switch sw) {
		super();
		match = new RailwayElement[] { semaphore, route, switchPosition, sw };
	}

	@Override
	public Semaphore getSemaphore() {
		return (Semaphore) match[0];
	}

	@Override
	public Route getRoute() {
		return (Route) match[1];
	}

	@Override
	public SwitchPosition getSwP() {
		return (SwitchPosition) match[2];
	}

	@Override
	public Switch getSw() {
		return (Switch) match[3];
	}

}
