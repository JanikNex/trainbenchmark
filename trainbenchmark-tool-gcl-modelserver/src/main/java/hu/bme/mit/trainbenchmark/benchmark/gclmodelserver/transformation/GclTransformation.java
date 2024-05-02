package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GclTransformation<TMatch extends GclMatch, TDriver extends GclDriver> extends ModelTransformation<TMatch, TDriver> {
	protected final boolean collectiveTransformation;

	public GclTransformation(TDriver driver, boolean collectiveTransformation) {
		super(driver);
		this.collectiveTransformation = collectiveTransformation;
	}

	@Override
	public void activate(Collection<TMatch> matches) throws Exception {
		if (this.collectiveTransformation) {
			List<ModelServerEditStatements.EditRequest> requests = new ArrayList<>();
			for (GclMatch match : matches) {
				requests.addAll(getTransformation(match));
			}
			ModelServerEditStatements.EditChainRequest request = GclTransformationUtils.getEditChainRequest(requests);
			this.driver.getEditServiceClient().executeEditRequest(request);
		} else {
			for (GclMatch match : matches) {
				List<ModelServerEditStatements.EditRequest> requests = getTransformation(match);
				ModelServerEditStatements.EditChainRequest request = GclTransformationUtils.getEditChainRequest(requests);
				this.driver.getEditServiceClient().executeEditRequest(request);
			}
		}
	}

	protected abstract List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match);
}
