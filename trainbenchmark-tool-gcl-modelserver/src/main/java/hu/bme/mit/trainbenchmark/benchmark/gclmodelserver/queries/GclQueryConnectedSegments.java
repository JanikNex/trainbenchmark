package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQueryConnectedSegments<TDriver extends GclDriver> extends GclQuery<GclConnectedSegmentsMatch, TDriver> {
	public GclQueryConnectedSegments(final TDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS, driver);
	}

	@Override
	public Collection<GclConnectedSegmentsMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclConnectedSegmentsMatch::new).toList();
	}
}
