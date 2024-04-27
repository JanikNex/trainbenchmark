package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.comparators;

import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.BaseMatchComparator;

public class GclMatchComparator extends BaseMatchComparator<GclMatch, Integer> {
	public GclMatchComparator() {
		super(Integer::compareTo);
	}
}
