package hu.bme.mit.trainbenchmark.benchmark.gcl.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

public class GclBenchmarkConfig extends BenchmarkConfig {
	protected boolean collectiveTransformation;

	protected GclBenchmarkConfig(final BenchmarkConfigBase configBase, boolean collectiveTransformation) {
		super(configBase);
		this.collectiveTransformation = collectiveTransformation;
	}

	@Override
	public String getToolName() {
		String collectiveMode = this.collectiveTransformation ? "CollectiveFix" : "SingleFix";
		return String.format("GCL (%s)", collectiveMode);
	}

	@Override
	public String getProjectName() {
		return "gcl";
	}

	public boolean isCollectiveTransformation() {
		return collectiveTransformation;
	}
}
