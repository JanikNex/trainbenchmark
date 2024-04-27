package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class GclDriverFactory extends DriverFactory<GclDriver> {
	@Override
	public GclDriver createInstance() throws Exception {
		return new GclDriver();
	}
}
