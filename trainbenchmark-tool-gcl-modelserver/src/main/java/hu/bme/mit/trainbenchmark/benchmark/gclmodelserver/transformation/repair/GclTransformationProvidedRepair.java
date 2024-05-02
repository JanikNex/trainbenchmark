package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.repair;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;

import java.util.List;

public class GclTransformationProvidedRepair<TDriver extends GclDriver, TMatch extends GclMatch> extends GclTransformation<TMatch, TDriver> {
	public GclTransformationProvidedRepair(TDriver driver, boolean collectiveTransformation) {
		super(driver, collectiveTransformation);
	}

	@Override
	protected List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match) {
		return match.getRepairEditChainRequest();
	}
}
