package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQuerySwitchMonitored<TDriver extends GclDriver> extends GclQuery<GclSwitchMonitoredMatch, TDriver> {
	public GclQuerySwitchMonitored(final TDriver driver) {
		super(RailwayQuery.SWITCHMONITORED, driver);
	}

	@Override
	public Collection<GclSwitchMonitoredMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclSwitchMonitoredMatch::new).toList();
	}
}
