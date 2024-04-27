package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;
import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.matches.BaseMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

import java.util.Arrays;
import java.util.List;

public abstract class GclMatch extends BaseMatch {
	protected final GclMatchNodeRegistry nodeRegistry;
	protected final ModelServerConstraints.FixMatch match;

	public GclMatch(RailwayOperation operation, int expectedNodes, ModelServerConstraints.FixMatch match) {
		if (match.getEmptyMatch() || match.getNodesCount() != expectedNodes) {
			throw new IllegalStateException(String.format("Match does not qualify for %s!", operation.name()));
		}

		this.match = match;
		this.nodeRegistry = new GclMatchNodeRegistry(match);
	}

	@Override
	public String toString() {
		return "GCLMatch [match=" + Arrays.toString(toArray()) + "]";
	}

	public List<ModelServerEditStatements.EditRequest> getRepairEditChainRequest() {
		if (this.match.getVariantsCount() != 1) {
			throw new IllegalStateException("Match does not contain any repair variant!");
		}
		ModelServerConstraints.FixVariant variant = this.match.getVariants(0);
		return variant.getStatementsList().stream()
			.filter(ModelServerConstraints.FixStatement::hasEdit)
			.map(ModelServerConstraints.FixStatement::getEdit)
			.toList();
	}

}

