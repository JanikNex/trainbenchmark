package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclRouteSensorInjectMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorInjectMatch {

	public GclRouteSensorInjectMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.ROUTESENSOR_INJECT, 2, match);
	}

	@Override
	public Integer getRoute() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_ROUTE).getNodeId();
	}

	@Override
	public Integer getSensor() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SENSOR).getNodeId();
	}
}
