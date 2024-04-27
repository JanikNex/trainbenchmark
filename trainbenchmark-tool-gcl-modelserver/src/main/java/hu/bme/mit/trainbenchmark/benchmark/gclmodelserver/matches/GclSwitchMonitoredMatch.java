package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclSwitchMonitoredMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredMatch {

	public GclSwitchMonitoredMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.SWITCHMONITORED, 1, match);
	}

	@Override
	public Integer getSw() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SW).getNodeId();
	}
}
