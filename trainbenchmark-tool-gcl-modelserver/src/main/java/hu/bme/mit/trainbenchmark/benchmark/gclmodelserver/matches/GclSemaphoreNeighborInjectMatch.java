package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclSemaphoreNeighborInjectMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborInjectMatch {

	public GclSemaphoreNeighborInjectMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.SEMAPHORENEIGHBOR_INJECT, 2, match);
	}

	@Override
	public Integer getRoute() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_ROUTE).getNodeId();
	}

	@Override
	public Integer getSemaphore() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEMAPHORE).getNodeId();
	}
}
