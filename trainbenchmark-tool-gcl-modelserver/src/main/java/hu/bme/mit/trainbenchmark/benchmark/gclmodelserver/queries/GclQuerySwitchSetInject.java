package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQuerySwitchSetInject<TDriver extends GclDriver> extends GclQuery<GclSwitchSetInjectMatch, TDriver> {
	public GclQuerySwitchSetInject(final TDriver driver) {
		super(RailwayQuery.SWITCHSET_INJECT, driver);
	}

	@Override
	public Collection<GclSwitchSetInjectMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclSwitchSetInjectMatch::new).toList();
	}
}
