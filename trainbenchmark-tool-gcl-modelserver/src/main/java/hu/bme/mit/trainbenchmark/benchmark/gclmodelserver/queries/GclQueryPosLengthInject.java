package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQueryPosLengthInject<TDriver extends GclDriver> extends GclQuery<GclPosLengthInjectMatch, TDriver> {
	public GclQueryPosLengthInject(final TDriver driver) {
		super(RailwayQuery.POSLENGTH_INJECT, driver);
	}

	@Override
	public Collection<GclPosLengthInjectMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclPosLengthInjectMatch::new).toList();
	}
}
