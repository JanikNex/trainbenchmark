package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclSwitchMonitoredInjectMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredInjectMatch {

	public GclSwitchMonitoredInjectMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.SWITCHMONITORED_INJECT, 2, match);
	}

	@Override
	public Integer getSw() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SW).getNodeId();
	}

	public Integer getSensor() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SENSOR).getNodeId();
	}
}
