package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver;

import de.nexus.modelserver.proto.ModelServerConstraints;
import de.nexus.modelserver.proto.ModelServerConstraintsGrpc;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import io.grpc.Channel;

import java.util.Collections;
import java.util.List;

public class ConstraintServiceClient {
	private final ModelServerConstraintsGrpc.ModelServerConstraintsBlockingStub blockingStub;

	public ConstraintServiceClient(Channel channel) {
		this.blockingStub = ModelServerConstraintsGrpc.newBlockingStub(channel);
	}

	public List<ModelServerConstraints.FixMatch> getConstraintMatches(RailwayQuery query) {
		return getConstraintMatches(query.toString());
	}

	public List<ModelServerConstraints.FixMatch> getConstraintMatches(String constraintName) {
		ModelServerConstraints.GetConstraintRequest request = ModelServerConstraints.GetConstraintRequest.newBuilder()
			.setConstraintName(constraintName)
			.build();
		ModelServerConstraints.Constraint constraint = this.blockingStub.getConstraint(request).getConstraint();
		if (!constraint.getViolated()) {
			return Collections.emptyList();
		}
		if (constraint.getAssertionsCount() != 1) {
			throw new IllegalStateException("Benchmark Constraint should not have more than one assertion!");
		}

		ModelServerConstraints.ConstraintAssertion assertion = constraint.getAssertions(0);
		if (!assertion.getViolated()) {
			return Collections.emptyList();
		}

		ModelServerConstraints.FixProposalContainer container = assertion.getProposalContainer();

		if (!container.getType().equals(ModelServerConstraints.FixProposalContainerType.SINGLE_FIX)) {
			throw new IllegalStateException("Benchmark Constraint should not have more than one possible FixProposal!");
		}
		ModelServerConstraints.FixProposal proposal = container.getProposals(0);
		return proposal.getMatchesList();
	}
}
