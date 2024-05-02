package hu.bme.mit.trainbenchmark.benchmark.gcl.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class GclBenchmarkConfigBuilder extends BenchmarkConfigBuilder<GclBenchmarkConfig, GclBenchmarkConfigBuilder> {
	private boolean collectiveTransformation = false;

	@Override
	public GclBenchmarkConfig createConfig() {
		checkNotNulls();
		return new GclBenchmarkConfig(configBase, this.collectiveTransformation);
	}

	public GclBenchmarkConfigBuilder setCollectiveTransformation(final boolean mode) {
		this.collectiveTransformation = mode;
		return this;
	}
}
