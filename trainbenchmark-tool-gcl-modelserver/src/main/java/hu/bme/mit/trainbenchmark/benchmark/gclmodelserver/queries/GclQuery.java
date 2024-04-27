package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class GclQuery<TPatternMatch extends GclMatch, TDriver extends GclDriver> extends ModelQuery<TPatternMatch, TDriver> {
	public GclQuery(final RailwayQuery query, final TDriver driver) {
		super(query, driver);
	}
}
