package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

import java.util.Optional;

public class GclSwitchSetInjectMatch extends GclMatch implements hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetInjectMatch {

	public GclSwitchSetInjectMatch(final ModelServerConstraints.FixMatch match) {
		super(RailwayOperation.SWITCHSET_INJECT, 1, match);
	}

	@Override
	public Integer getSw() {
		return this.nodeRegistry.getNode(QueryConstants.VAR_SW).getNodeId();
	}

	public String getSwPosition() {
		Optional<ModelServerConstraints.MatchNodeAttribute> position = this.nodeRegistry.getNode(QueryConstants.VAR_SW).getNodeAttributesList().stream().filter(x -> x.getAttributeName().equals("currentPosition")).findFirst();
		if (position.isPresent()) {
			return position.get().getAttributeValue();
		} else {
			throw new RuntimeException("Unable to find currentPosition Attribute!");
		}
	}
}
