package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclConnectedSegmentsInjectMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsInjectMatch {
	public GclConnectedSegmentsInjectMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.CONNECTEDSEGMENTS_INJECT, 4, match);
	}

	@Override
	public Integer getSensor() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SENSOR).getNodeId();
	}

	@Override
	public Integer getSegment1() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT1).getNodeId();
	}

	@Override
	public Integer getSegment3() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT3).getNodeId();
	}

	public Integer getRegion() {
		return this.nodeRegistry.getNode("region").getNodeId();
	}
}
