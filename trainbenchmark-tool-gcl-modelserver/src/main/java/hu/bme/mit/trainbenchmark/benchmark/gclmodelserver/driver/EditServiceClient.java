package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import de.nexus.modelserver.proto.ModelServerEdits;
import de.nexus.modelserver.proto.ModelServerEditsGrpc;
import io.grpc.Channel;

public class EditServiceClient {
	private final ModelServerEditsGrpc.ModelServerEditsBlockingStub blockingStub;

	public EditServiceClient(Channel channel) {
		this.blockingStub = ModelServerEditsGrpc.newBlockingStub(channel);
	}

	public void executeEditRequest(final ModelServerEditStatements.EditChainRequest editChainRequest) {
		ModelServerEdits.PostEditResponse response = this.blockingStub.requestEdit(ModelServerEdits.PostEditRequest.newBuilder().setEditChain(editChainRequest).build());
		checkIfEditChainResponseIsSuccess(response.getEditChain());
	}

	private void checkIfEditChainResponseIsSuccess(ModelServerEditStatements.EditChainResponse response) {
		for (ModelServerEditStatements.EditResponse editResponse : response.getEditsList()) {
			ModelServerEditStatements.EditState state = switch (editResponse.getResponseCase()) {
				case CREATEEDGERESPONSE -> editResponse.getCreateEdgeResponse().getState();
				case CREATENODERESPONSE -> editResponse.getCreateNodeResponse().getState();
				case DELETEEDGERESPONSE -> editResponse.getDeleteEdgeResponse().getState();
				case DELETENODERESPONSE -> editResponse.getDeleteNodeResponse().getState();
				case SETATTRIBUTERESPONSE -> editResponse.getSetAttributeResponse().getState();
				case RESPONSE_NOT_SET -> ModelServerEditStatements.EditState.FAILURE;
			};

			if (state != ModelServerEditStatements.EditState.SUCCESS) {
				System.out.println(editResponse.toString());
				throw new RuntimeException("Failed to execute EditChain!");
			}
		}
	}
}
