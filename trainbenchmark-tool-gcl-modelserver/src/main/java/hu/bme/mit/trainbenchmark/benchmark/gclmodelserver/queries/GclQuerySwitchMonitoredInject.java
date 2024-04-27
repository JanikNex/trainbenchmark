package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQuerySwitchMonitoredInject<TDriver extends GclDriver> extends GclQuery<GclSwitchMonitoredInjectMatch, TDriver> {
	public GclQuerySwitchMonitoredInject(final TDriver driver) {
		super(RailwayQuery.SWITCHMONITORED_INJECT, driver);
	}

	@Override
	public Collection<GclSwitchMonitoredInjectMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclSwitchMonitoredInjectMatch::new).toList();
	}
}
