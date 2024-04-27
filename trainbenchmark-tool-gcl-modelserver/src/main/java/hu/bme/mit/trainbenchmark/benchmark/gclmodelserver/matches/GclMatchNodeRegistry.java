package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches;

import de.nexus.modelserver.proto.ModelServerConstraints;

import java.util.HashMap;

public class GclMatchNodeRegistry {
	private final HashMap<String, ModelServerConstraints.MatchNode> registry = new HashMap<>();

	public GclMatchNodeRegistry(ModelServerConstraints.FixMatch match) {
		for (ModelServerConstraints.MatchNode node : match.getNodesList()) {
			this.registry.put(node.getNodeName(), node);
		}
	}

	public ModelServerConstraints.MatchNode getNode(String nodeName) {
		return this.registry.get(nodeName);
	}
}
