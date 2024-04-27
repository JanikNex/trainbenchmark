package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclSemaphoreNeighborMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch {

	public GclSemaphoreNeighborMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.SEMAPHORENEIGHBOR,7,match);
	}

	@Override
	public Integer getSemaphore() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEMAPHORE).getNodeId();
	}

	@Override
	public Integer getRoute1() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_ROUTE1).getNodeId();
	}

	@Override
	public Integer getRoute2() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_ROUTE2).getNodeId();
	}

	@Override
	public Integer getSensor1() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SENSOR1).getNodeId();
	}

	@Override
	public Integer getSensor2() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SENSOR2).getNodeId();
	}

	@Override
	public Integer getTe1() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_TE1).getNodeId();
	}

	@Override
	public Integer getTe2() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_TE2).getNodeId();
	}
}
