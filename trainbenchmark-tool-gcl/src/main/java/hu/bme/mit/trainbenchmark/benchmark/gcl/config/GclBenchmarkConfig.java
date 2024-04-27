package hu.bme.mit.trainbenchmark.benchmark.gcl.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

public class GclBenchmarkConfig extends BenchmarkConfig {
	protected GclBenchmarkConfig(final BenchmarkConfigBase configBase) {
		super(configBase);
	}

	@Override
	public String getToolName() {
		return "GCL";
	}

	@Override
	public String getProjectName() {
		return "gcl";
	}
}
