package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Collection;
import java.util.List;

public class GclTransformationInjectPosLength<TDriver extends GclDriver, TPosLengthInjectMatch extends GclPosLengthInjectMatch> extends GclTransformation<TPosLengthInjectMatch, TDriver> {

	public GclTransformationInjectPosLength(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(Collection<TPosLengthInjectMatch> matches) throws Exception {
		for (GclPosLengthInjectMatch match : matches) {
			ModelServerEditStatements.Node node = GclTransformationUtils.getNode(match.getSegment());
			ModelServerEditStatements.EditChainRequest request = GclTransformationUtils.getEditChainRequest(
				List.of(
					GclTransformationUtils.getSetAttributeRequest(node, QueryConstants.VAR_LENGTH, "0")
				)
			);
			this.driver.getEditServiceClient().executeEditRequest(request);
		}
	}
}
