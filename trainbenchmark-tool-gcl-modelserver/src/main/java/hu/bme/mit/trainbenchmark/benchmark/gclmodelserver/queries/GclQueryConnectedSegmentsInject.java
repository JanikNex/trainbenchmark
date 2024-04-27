package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQueryConnectedSegmentsInject<TDriver extends GclDriver> extends GclQuery<GclConnectedSegmentsInjectMatch, TDriver> {
	public GclQueryConnectedSegmentsInject(final TDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS_INJECT, driver);
	}

	@Override
	public Collection<GclConnectedSegmentsInjectMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclConnectedSegmentsInjectMatch::new).toList();
	}
}
