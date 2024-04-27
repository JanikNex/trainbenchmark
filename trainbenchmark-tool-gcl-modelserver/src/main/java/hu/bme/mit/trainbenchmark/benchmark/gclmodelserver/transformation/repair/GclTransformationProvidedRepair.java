package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.repair;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;

import java.util.Collection;
import java.util.List;

public class GclTransformationProvidedRepair<TDriver extends GclDriver, TMatch extends GclMatch> extends GclTransformation<TMatch, TDriver> {
	public GclTransformationProvidedRepair(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(Collection<TMatch> tMatches) throws Exception {
		for (TMatch tMatch : tMatches) {
			List<ModelServerEditStatements.EditRequest> providedRepairEdits = tMatch.getRepairEditChainRequest();
			ModelServerEditStatements.EditChainRequest editChainRequest = ModelServerEditStatements.EditChainRequest.newBuilder()
				.addAllEdits(providedRepairEdits)
				.build();
			this.driver.getEditServiceClient().executeEditRequest(editChainRequest);
		}
	}
}
