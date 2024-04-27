package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries;

import de.nexus.modelserver.proto.ModelServerConstraints;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclPosLengthMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Collection;
import java.util.List;

public class GclQueryPosLength<TDriver extends GclDriver> extends GclQuery<GclPosLengthMatch, TDriver> {
	public GclQueryPosLength(final TDriver driver) {
		super(RailwayQuery.POSLENGTH, driver);
	}

	@Override
	public Collection<GclPosLengthMatch> evaluate() throws Exception {
		List<ModelServerConstraints.FixMatch> matches = this.driver.getConstraintServiceClient().getConstraintMatches(this.query);
		return matches.stream().map(GclPosLengthMatch::new).toList();
	}
}
