package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;

import java.util.List;

public class GclTransformationInjectSwitchMonitored<TDriver extends GclDriver, TSwitchMonitoredInjectMatch extends GclSwitchMonitoredInjectMatch> extends GclTransformation<TSwitchMonitoredInjectMatch, TDriver> {

	public GclTransformationInjectSwitchMonitored(TDriver driver, boolean collectiveTransformation) {
		super(driver, collectiveTransformation);
	}

	@Override
	protected List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match) {
		ModelServerEditStatements.Node sw = GclTransformationUtils.getNode(((GclSwitchMonitoredInjectMatch) match).getSw());
		return List.of(GclTransformationUtils.getDeleteAllEdgesRequest(sw, "monitoredBy"));
	}
}
