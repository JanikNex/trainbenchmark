package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;

import java.util.Collection;
import java.util.List;

public class GclTransformationInjectSwitchMonitored<TDriver extends GclDriver, TSwitchMonitoredInjectMatch extends GclSwitchMonitoredInjectMatch> extends GclTransformation<TSwitchMonitoredInjectMatch, TDriver> {

	public GclTransformationInjectSwitchMonitored(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(Collection<TSwitchMonitoredInjectMatch> matches) throws Exception {
		for (GclSwitchMonitoredInjectMatch match : matches) {
			ModelServerEditStatements.Node sw = GclTransformationUtils.getNode(match.getSw());
			ModelServerEditStatements.Node sensor = GclTransformationUtils.getNode(match.getSensor());
			ModelServerEditStatements.EditRequest editRequest = GclTransformationUtils.getDeleteEdgeRequest(sw, sensor, "monitoredBy");
			ModelServerEditStatements.EditChainRequest request = GclTransformationUtils.getEditChainRequest(List.of(editRequest));

			this.driver.getEditServiceClient().executeEditRequest(request);
		}
	}
}
