package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclSwitchSetMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch {

	public GclSwitchSetMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.SWITCHSET, 4, match);
	}

	@Override
	public Integer getSemaphore() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEMAPHORE).getNodeId();
	}

	@Override
	public Integer getRoute() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_ROUTE).getNodeId();
	}

	@Override
	public Integer getSwP() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SWP).getNodeId();
	}

	@Override
	public Integer getSw() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SW).getNodeId();
	}
}
