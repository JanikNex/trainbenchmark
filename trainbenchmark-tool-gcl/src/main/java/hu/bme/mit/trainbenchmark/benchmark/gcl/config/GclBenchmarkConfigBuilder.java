package hu.bme.mit.trainbenchmark.benchmark.gcl.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class GclBenchmarkConfigBuilder extends BenchmarkConfigBuilder<GclBenchmarkConfig, GclBenchmarkConfigBuilder> {
	@Override
	public GclBenchmarkConfig createConfig() {
		checkNotNulls();
		return new GclBenchmarkConfig(configBase);
	}
}
