package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.emf.EmfConstants;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GclDriver extends Driver {
	private final static String MODEL_SERVER_GRPC_HOST = "localhost";
	private final static int MODEL_SERVER_GRPC_PORT = 9090;

	private ManagedChannel channel;

	private ConstraintServiceClient constraintServiceClient;
	private EditServiceClient editServiceClient;
	private ManagementServiceClient managementServiceClient;

	@Override
	public void initialize() throws Exception {
		System.out.println("[GCL] Initialize");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("[GCL] Destroy");
		if (this.managementServiceClient != null) {
			this.managementServiceClient.shutdown();
		}
		ModelServerRunner.cleanup();
		Thread.sleep(5000);
		this.channel.shutdownNow();
	}

	@Override
	public void read(String modelPath) throws Exception {
		System.out.println("[GCL] Read " + modelPath);
		ModelServerRunner.start(modelPath);

		this.channel = ManagedChannelBuilder.forAddress(MODEL_SERVER_GRPC_HOST, MODEL_SERVER_GRPC_PORT)
			.usePlaintext()
			.build();

		this.constraintServiceClient = new ConstraintServiceClient(channel);
		this.editServiceClient = new EditServiceClient(channel);
		this.managementServiceClient = new ManagementServiceClient(channel);
	}

	@Override
	public String getPostfix() {
		return "." + EmfConstants.MODEL_EXTENSION;
	}

	@Override
	public Number generateNewVertexId() throws Exception {
		return null;
	}

	public ConstraintServiceClient getConstraintServiceClient() {
		return this.constraintServiceClient;
	}

	public EditServiceClient getEditServiceClient() {
		return this.editServiceClient;
	}

	public ManagementServiceClient getManagementServiceClient() {
		return this.managementServiceClient;
	}
}
