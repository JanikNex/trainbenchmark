package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.List;

public class GclTransformationInjectSwitchSet<TDriver extends GclDriver, TSwitchSetInjectMatch extends GclSwitchSetInjectMatch> extends GclTransformation<TSwitchSetInjectMatch, TDriver> {

	public GclTransformationInjectSwitchSet(TDriver driver, boolean collectiveTransformation) {
		super(driver, collectiveTransformation);
	}

	@Override
	protected List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match) {
		ModelServerEditStatements.Node sw = GclTransformationUtils.getNode(((GclSwitchSetInjectMatch) match).getSw());
		String currentPosition = ((GclSwitchSetInjectMatch) match).getSwPosition();
		String newPosition = nextPosition(currentPosition);
		return List.of(GclTransformationUtils.getSetAttributeRequest(sw, QueryConstants.VAR_CURRENTPOSITION, newPosition));
	}

	private String nextPosition(String currentPosition) {
		return switch (currentPosition) {
			case "FAILURE" -> "STRAIGHT";
			case "STRAIGHT" -> "DIVERGING";
			case "DIVERGING" -> "FAILURE";
			default -> throw new IllegalStateException("Unexpected value: " + currentPosition);
		};
	}
}
