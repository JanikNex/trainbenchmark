package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclRouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQueryRouteSensor<TDriver extends GclDriver> extends GclQuery<GclRouteSensorMatch, TDriver> {
	public GclQueryRouteSensor(final TDriver driver) {
		super(RailwayQuery.ROUTESENSOR, driver);
	}

	@Override
	public Collection<GclRouteSensorMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclRouteSensorMatch::new).toList();
	}
}
