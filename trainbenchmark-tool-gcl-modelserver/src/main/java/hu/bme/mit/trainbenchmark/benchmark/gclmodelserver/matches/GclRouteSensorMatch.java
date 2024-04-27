package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclRouteSensorMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch {

	public GclRouteSensorMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.ROUTESENSOR, 4, match);
	}

	@Override
	public Integer getRoute() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_ROUTE).getNodeId();
	}

	@Override
	public Integer getSensor() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SENSOR).getNodeId();
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
