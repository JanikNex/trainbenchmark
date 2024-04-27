package hu.bme.mit.trainbenchmark.benchmark.gcl;

import hu.bme.mit.trainbenchmark.benchmark.gcl.config.GclBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.gcl.operations.GclModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.comparators.GclMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class GclBenchmarkScenario extends BenchmarkScenario<GclMatch, GclDriver, GclBenchmarkConfig> {
	public GclBenchmarkScenario(final GclBenchmarkConfig bc) throws Exception {
		super(new GclDriverFactory(), new GclModelOperationFactory<>(), new GclMatchComparator(), bc);
	}
}
