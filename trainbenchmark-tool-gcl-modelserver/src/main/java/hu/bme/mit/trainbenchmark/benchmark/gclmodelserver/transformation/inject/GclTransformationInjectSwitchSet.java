package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Collection;
import java.util.List;

public class GclTransformationInjectSwitchSet<TDriver extends GclDriver, TSwitchSetInjectMatch extends GclSwitchSetInjectMatch> extends GclTransformation<TSwitchSetInjectMatch, TDriver> {

	public GclTransformationInjectSwitchSet(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(Collection<TSwitchSetInjectMatch> matches) throws Exception {
		for (GclSwitchSetInjectMatch match : matches) {
			ModelServerEditStatements.Node sw = GclTransformationUtils.getNode(match.getSw());
			String currentPosition = match.getSwPosition();
			String newPosition = nextPosition(currentPosition);
			ModelServerEditStatements.EditRequest editRequest = GclTransformationUtils.getSetAttributeRequest(sw, QueryConstants.VAR_CURRENTPOSITION, newPosition);
			ModelServerEditStatements.EditChainRequest request = GclTransformationUtils.getEditChainRequest(List.of(editRequest));

			this.driver.getEditServiceClient().executeEditRequest(request);
		}
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
