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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;

public abstract class Neo4jJavaBenchmarkCase extends Neo4jBenchmarkCase {

	@Override
	public void init(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		this.nbc = (Neo4jBenchmarkConfig) bc;

		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		graphDbPath = bc.getWorkspacePath() + "/models/neo4j-dbs/railway-database";

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void check() {
		bmr.startStopper();

		invalids = checkJava();

		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}
}