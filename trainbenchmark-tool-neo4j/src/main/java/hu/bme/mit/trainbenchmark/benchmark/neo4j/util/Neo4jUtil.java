package hu.bme.mit.trainbenchmark.benchmark.neo4j.util;

import java.util.ArrayList;
import java.util.Collection;

import org.neo4j.graphdb.*;

public class Neo4jUtil {

	public static boolean isConnected(final Node source, final Node target, final RelationshipType relationshipType) {
		final int sourceDegree = source.getDegree(relationshipType, Direction.OUTGOING);
		final int targetDegree = target.getDegree(relationshipType, Direction.INCOMING);

		final Direction searchDirection;
		final Node searchSource;
		final Node searchTarget;
		if (sourceDegree <= targetDegree) {
			searchDirection = Direction.OUTGOING;
			searchSource = source;
			searchTarget = target;
		} else {
			searchDirection = Direction.INCOMING;
			searchSource = target;
			searchTarget = source;
		}

		try (ResourceIterable<Relationship> relationships = searchSource.getRelationships(searchDirection, relationshipType)) {
			for (Relationship edge : relationships.stream().toList()) {
				final Node otherNode = edge.getOtherNode(searchSource);
				if (searchTarget.equals(otherNode)) {
					return true;
				}
			}
		}

		return false;
	}

	public static Iterable<Node> getAdjacentNodes(final Node sourceNode, final RelationshipType relationshipType, final Direction direction, final Label targetNodeLabel) {
		final Collection<Node> nodes = new ArrayList<>();

		try(ResourceIterable<Relationship> relationships = sourceNode.getRelationships(direction,relationshipType)) {
			for (final Relationship relationship : relationships) {
				final Node candidate = switch (direction) {
					case INCOMING -> relationship.getStartNode();
					case OUTGOING -> relationship.getEndNode();
					default -> throw new UnsupportedOperationException("Direction: " + direction + " not supported.");
				};
				if (!candidate.hasLabel(targetNodeLabel)) {
					continue;
				}
				nodes.add(candidate);
			}
		}
		return nodes;
	}
}
