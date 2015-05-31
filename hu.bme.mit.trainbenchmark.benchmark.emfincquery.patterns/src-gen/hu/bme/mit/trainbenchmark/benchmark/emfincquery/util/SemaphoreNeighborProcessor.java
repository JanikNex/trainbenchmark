package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighbor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SemaphoreNeighborProcessor implements IMatchProcessor<SemaphoreNeighborMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pRoute1 the value of pattern parameter route1 in the currently processed match
   * 
   */
  public abstract void process(final Route pRoute1);
  
  @Override
  public void process(final SemaphoreNeighborMatch match) {
    process(match.getRoute1());
  }
}