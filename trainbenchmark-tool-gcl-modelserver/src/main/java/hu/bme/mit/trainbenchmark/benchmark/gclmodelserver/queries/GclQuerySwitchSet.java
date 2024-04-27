package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQuerySwitchSet<TDriver extends GclDriver> extends GclQuery<GclSwitchSetMatch, TDriver> {
	public GclQuerySwitchSet(final TDriver driver) {
		super(RailwayQuery.SWITCHSET, driver);
	}

	@Override
	public Collection<GclSwitchSetMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclSwitchSetMatch::new).toList();
	}
}
