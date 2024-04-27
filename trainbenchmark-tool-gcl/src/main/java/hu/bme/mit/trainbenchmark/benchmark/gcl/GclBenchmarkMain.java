package hu.bme.mit.trainbenchmark.benchmark.gcl;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.gcl.config.GclBenchmarkConfig;

public class GclBenchmarkMain {
	public static void main(final String[] args) throws Exception {
		final GclBenchmarkConfig bc = BenchmarkConfig.fromFile(args[0], GclBenchmarkConfig.class);
		final GclBenchmarkScenario scenario = new GclBenchmarkScenario(bc);
		scenario.performBenchmark();
	}
}
