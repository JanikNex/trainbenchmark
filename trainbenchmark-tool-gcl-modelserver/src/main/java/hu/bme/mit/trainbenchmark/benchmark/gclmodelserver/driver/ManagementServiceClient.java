package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver;

import de.nexus.modelserver.proto.ModelServerManagement;
import de.nexus.modelserver.proto.ModelServerManagementGrpc;
import io.grpc.Channel;

public class ManagementServiceClient {
	private final ModelServerManagementGrpc.ModelServerManagementBlockingStub blockingStub;

	public ManagementServiceClient(Channel channel) {
		this.blockingStub = ModelServerManagementGrpc.newBlockingStub(channel);
	}

	public void shutdown() {
		this.blockingStub.terminateServer(ModelServerManagement.TerminateServerRequest.newBuilder().getDefaultInstanceForType());
	}
}
