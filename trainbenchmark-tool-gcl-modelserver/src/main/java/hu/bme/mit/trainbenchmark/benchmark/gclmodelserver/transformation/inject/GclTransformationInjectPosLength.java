package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.List;

public class GclTransformationInjectPosLength<TDriver extends GclDriver, TPosLengthInjectMatch extends GclPosLengthInjectMatch> extends GclTransformation<TPosLengthInjectMatch, TDriver> {

	public GclTransformationInjectPosLength(TDriver driver, boolean collectiveTransformation) {
		super(driver, collectiveTransformation);
	}

	protected List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match) {
		ModelServerEditStatements.Node node = GclTransformationUtils.getNode(((GclPosLengthInjectMatch) match).getSegment());
		return List.of(
			GclTransformationUtils.getSetAttributeRequest(node, QueryConstants.VAR_LENGTH, "0")
		);
	}
}
