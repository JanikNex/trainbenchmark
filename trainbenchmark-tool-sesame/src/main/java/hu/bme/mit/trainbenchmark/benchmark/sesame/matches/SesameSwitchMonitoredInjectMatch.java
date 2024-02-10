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
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.query.BindingSet;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredInjectMatch;

public class SesameSwitchMonitoredInjectMatch extends SesameMatch implements SwitchMonitoredInjectMatch {

	public SesameSwitchMonitoredInjectMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public IRI getSw() {
		return (IRI) bs.getValue(VAR_SW);
	}

}
