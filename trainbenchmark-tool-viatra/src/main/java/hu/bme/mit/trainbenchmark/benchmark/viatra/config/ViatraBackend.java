package hu.bme.mit.trainbenchmark.benchmark.viatra.config;

public enum ViatraBackend {
	INCREMENTAL("Incremental"),
	LOCAL_SEARCH("Local Search"),
	HYBRID_LOCAL_SEARCH("Hybrid Local Search");

	private String name;

	ViatraBackend(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
