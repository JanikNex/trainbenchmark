/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.drools5.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.drools5.ResultListener;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

import java.util.ArrayList;
import java.util.Collection;

public class SwitchSet extends Drools5BenchmarkCase<SwitchPosition> {

	@Override
	protected Collection<SwitchPosition> check() {
		if (query == null) {
			listener = new ResultListener<SwitchPosition>("switchPosition");
			query = ksession.openLiveQuery("SwitchSet check", new Object[] {}, listener);
		}
		results = new ArrayList<>(listener.getMatching());
		return results;
	}

}