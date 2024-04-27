package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation;

import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;

public abstract class GclTransformation<TMatch extends GclMatch, TDriver extends GclDriver> extends ModelTransformation<TMatch, TDriver> {
	public GclTransformation(TDriver driver) {
		super(driver);
	}
}
