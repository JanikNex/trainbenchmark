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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.Neo4jBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkBaseTest;

public abstract class Neo4jTest extends BenchmarkBaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final Neo4jBenchmarkConfigWrapper nbcw = createNeo4jBenchmarkConfigWrapper(bc);
		final Neo4jBenchmarkScenario scenario = new Neo4jBenchmarkScenario(nbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

	protected abstract Neo4jBenchmarkConfigWrapper createNeo4jBenchmarkConfigWrapper(final BenchmarkConfigCore bc);

}
