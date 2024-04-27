package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclConnectedSegmentsMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch {

	public GclConnectedSegmentsMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.CONNECTEDSEGMENTS, 7, match);
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
	public Integer getSegment2() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT2).getNodeId();
	}

	@Override
	public Integer getSegment3() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT3).getNodeId();
	}

	@Override
	public Integer getSegment4() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT4).getNodeId();
	}

	@Override
	public Integer getSegment5() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT5).getNodeId();
	}

	@Override
	public Integer getSegment6() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT6).getNodeId();
	}
}
