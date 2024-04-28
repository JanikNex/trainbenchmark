package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation;

import de.nexus.modelserver.proto.ModelServerEditStatements;

public class GclTransformationUtils {
	public static ModelServerEditStatements.EditChainRequest getEditChainRequest(Iterable<? extends ModelServerEditStatements.EditRequest> edits) {
		return ModelServerEditStatements.EditChainRequest.newBuilder()
			.addAllEdits(edits)
			.build();
	}

	public static ModelServerEditStatements.EditRequest getCreateEdgeRequest(ModelServerEditStatements.Node startNode, ModelServerEditStatements.Node targetNode, String edgeName) {
		return ModelServerEditStatements.EditRequest.newBuilder()
			.setCreateEdgeRequest(
				ModelServerEditStatements.EditCreateEdgeRequest.newBuilder()
					.setStartNode(startNode)
					.setTargetNode(targetNode)
					.setReferenceName(edgeName)
					.build()
			)
			.build();
	}

	public static ModelServerEditStatements.EditRequest getDeleteEdgeRequest(ModelServerEditStatements.Node startNode, ModelServerEditStatements.Node targetNode, String edgeName) {
		return ModelServerEditStatements.EditRequest.newBuilder()
			.setDeleteEdgeRequest(
				ModelServerEditStatements.EditDeleteEdgeRequest.newBuilder()
					.setStartNode(startNode)
					.setTargetNode(targetNode)
					.setReferenceName(edgeName)
					.build()
			)
			.build();
	}

	public static ModelServerEditStatements.EditRequest getDeleteAllEdgesRequest(ModelServerEditStatements.Node startNode, String edgeName) {
		return ModelServerEditStatements.EditRequest.newBuilder()
			.setDeleteAllEdgesRequest(
				ModelServerEditStatements.EditDeleteAllEdgesRequest.newBuilder()
					.setStartNode(startNode)
					.setReferenceName(edgeName)
					.build()
			)
			.build();
	}

	public static ModelServerEditStatements.EditRequest getCreateNodeRequest(String tempId, String nodeType, Iterable<? extends ModelServerEditStatements.EditCreateNodeAttributeAssignment> attributes) {
		return ModelServerEditStatements.EditRequest.newBuilder()
			.setCreateNodeRequest(
				ModelServerEditStatements.EditCreateNodeRequest.newBuilder()
					.setTempId(tempId)
					.setNodeType(nodeType)
					.addAllAssignments(attributes)
					.build()
			)
			.build();
	}

	public static ModelServerEditStatements.EditRequest getDeleteNodeRequest(ModelServerEditStatements.Node node) {
		return ModelServerEditStatements.EditRequest.newBuilder()
			.setDeleteNodeRequest(
				ModelServerEditStatements.EditDeleteNodeRequest.newBuilder()
					.setNode(node)
					.build()
			)
			.build();
	}

	public static ModelServerEditStatements.EditRequest getSetAttributeRequest(ModelServerEditStatements.Node node, String attributeName, String attributeValue) {
		return ModelServerEditStatements.EditRequest.newBuilder()
			.setSetAttributeRequest(
				ModelServerEditStatements.EditSetAttributeRequest.newBuilder()
					.setNode(node)
					.setAttributeName(attributeName)
					.setAttributeValue(attributeValue)
					.build()
			)
			.build();
	}

	public static ModelServerEditStatements.Node getNode(String tempId) {
		return ModelServerEditStatements.Node.newBuilder()
			.setTempId(tempId)
			.build();
	}

	public static ModelServerEditStatements.Node getNode(int nodeId) {
		return ModelServerEditStatements.Node.newBuilder()
			.setNodeId(nodeId)
			.build();
	}
}
