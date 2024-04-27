package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQueryRouteSensorInject<TDriver extends GclDriver> extends GclQuery<GclRouteSensorInjectMatch, TDriver> {
	public GclQueryRouteSensorInject(final TDriver driver) {
		super(RailwayQuery.ROUTESENSOR_INJECT, driver);
	}

	@Override
	public Collection<GclRouteSensorInjectMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclRouteSensorInjectMatch::new).toList();
	}
}
