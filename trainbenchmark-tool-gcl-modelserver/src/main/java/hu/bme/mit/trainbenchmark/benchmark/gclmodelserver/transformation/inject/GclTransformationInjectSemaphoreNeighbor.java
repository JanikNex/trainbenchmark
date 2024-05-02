package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;

import java.util.List;

public class GclTransformationInjectSemaphoreNeighbor<TDriver extends GclDriver, TSemaphoreNeighborInjectMatch extends GclSemaphoreNeighborInjectMatch> extends GclTransformation<TSemaphoreNeighborInjectMatch, TDriver> {

	public GclTransformationInjectSemaphoreNeighbor(TDriver driver, boolean collectiveTransformation) {
		super(driver, collectiveTransformation);
	}

	@Override
	protected List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match) {
		ModelServerEditStatements.Node route = GclTransformationUtils.getNode(((GclSemaphoreNeighborInjectMatch) match).getRoute());
		ModelServerEditStatements.Node semaphore = GclTransformationUtils.getNode(((GclSemaphoreNeighborInjectMatch) match).getSemaphore());
		return List.of(GclTransformationUtils.getDeleteEdgeRequest(route, semaphore, "entry"));
	}
}
