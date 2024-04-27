package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQuerySemaphoreNeighbor<TDriver extends GclDriver> extends GclQuery<GclSemaphoreNeighborMatch, TDriver> {
	public GclQuerySemaphoreNeighbor(final TDriver driver) {
		super(RailwayQuery.SEMAPHORENEIGHBOR, driver);
	}

	@Override
	public Collection<GclSemaphoreNeighborMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclSemaphoreNeighborMatch::new).toList();
	}
}
