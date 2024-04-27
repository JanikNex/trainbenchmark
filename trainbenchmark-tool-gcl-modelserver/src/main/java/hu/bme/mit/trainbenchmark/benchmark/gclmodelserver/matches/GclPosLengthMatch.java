package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclPosLengthMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch {
	public GclPosLengthMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.POSLENGTH, 1, match);
	}

	@Override
	public Integer getSegment() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SEGMENT).getNodeId();
	}
}
