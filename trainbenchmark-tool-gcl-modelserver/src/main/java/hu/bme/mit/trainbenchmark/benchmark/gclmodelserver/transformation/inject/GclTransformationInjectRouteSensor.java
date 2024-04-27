package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;

import java.util.Collection;
import java.util.List;

public class GclTransformationInjectRouteSensor<TDriver extends GclDriver, TRouteSensorInjectMatch extends GclRouteSensorInjectMatch> extends GclTransformation<TRouteSensorInjectMatch, TDriver> {

	public GclTransformationInjectRouteSensor(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(Collection<TRouteSensorInjectMatch> matches) throws Exception {
		for (GclRouteSensorInjectMatch match : matches) {
			ModelServerEditStatements.Node route = GclTransformationUtils.getNode(match.getRoute());
			ModelServerEditStatements.Node sensor = GclTransformationUtils.getNode(match.getSensor());
			ModelServerEditStatements.EditRequest editRequest = GclTransformationUtils.getDeleteEdgeRequest(route, sensor, "requires");
			ModelServerEditStatements.EditChainRequest request = GclTransformationUtils.getEditChainRequest(List.of(editRequest));

			this.driver.getEditServiceClient().executeEditRequest(request);
		}
	}
}
