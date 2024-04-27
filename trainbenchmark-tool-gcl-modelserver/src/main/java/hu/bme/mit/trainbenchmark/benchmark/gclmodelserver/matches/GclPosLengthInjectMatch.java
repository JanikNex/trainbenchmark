package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclPosLengthInjectMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthInjectMatch {

	public GclPosLengthInjectMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.POSLENGTH_INJECT, 1, match);
	}

	@Override
	public Integer getSegment() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT).getNodeId();
	}
}
