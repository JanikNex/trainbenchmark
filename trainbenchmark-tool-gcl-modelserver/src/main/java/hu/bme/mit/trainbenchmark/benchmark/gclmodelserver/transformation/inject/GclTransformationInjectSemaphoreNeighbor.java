package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;

import java.util.Collection;
import java.util.List;

public class GclTransformationInjectSemaphoreNeighbor<TDriver extends GclDriver, TSemaphoreNeighborInjectMatch extends GclSemaphoreNeighborInjectMatch> extends GclTransformation<TSemaphoreNeighborInjectMatch, TDriver> {

	public GclTransformationInjectSemaphoreNeighbor(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(Collection<TSemaphoreNeighborInjectMatch> matches) throws Exception {
		for (GclSemaphoreNeighborInjectMatch match : matches) {
			ModelServerEditStatements.Node route = GclTransformationUtils.getNode(match.getRoute());
			ModelServerEditStatements.Node semaphore = GclTransformationUtils.getNode(match.getSemaphore());
			ModelServerEditStatements.EditRequest editRequest = GclTransformationUtils.getDeleteEdgeRequest(route, semaphore, "entry");
			ModelServerEditStatements.EditChainRequest request = GclTransformationUtils.getEditChainRequest(List.of(editRequest));

			this.driver.getEditServiceClient().executeEditRequest(request);
		}
	}
}
