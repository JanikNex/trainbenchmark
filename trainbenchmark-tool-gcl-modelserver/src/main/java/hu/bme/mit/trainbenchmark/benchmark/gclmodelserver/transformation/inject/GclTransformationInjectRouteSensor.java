package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;

import java.util.List;

public class GclTransformationInjectRouteSensor<TDriver extends GclDriver, TRouteSensorInjectMatch extends GclRouteSensorInjectMatch> extends GclTransformation<TRouteSensorInjectMatch, TDriver> {

	public GclTransformationInjectRouteSensor(TDriver driver, boolean collectiveTransformation) {
		super(driver, collectiveTransformation);
	}

	@Override
	protected List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match) {
		ModelServerEditStatements.Node route = GclTransformationUtils.getNode(((GclRouteSensorInjectMatch) match).getRoute());
		ModelServerEditStatements.Node sensor = GclTransformationUtils.getNode(((GclRouteSensorInjectMatch) match).getSensor());
		return List.of(GclTransformationUtils.getDeleteEdgeRequest(route, sensor, "requires"));
	}
}
