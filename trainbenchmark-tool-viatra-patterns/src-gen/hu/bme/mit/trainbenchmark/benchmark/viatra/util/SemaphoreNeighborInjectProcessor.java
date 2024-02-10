/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighborInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.semaphoreNeighborInject pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class SemaphoreNeighborInjectProcessor implements Consumer<SemaphoreNeighborInjectMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pRoute the value of pattern parameter route in the currently processed match
   * @param pSemaphore the value of pattern parameter semaphore in the currently processed match
   *
   */
  public abstract void process(final Route pRoute, final Semaphore pSemaphore);

  @Override
  public void accept(final SemaphoreNeighborInjectMatch match) {
    process(match.getRoute(), match.getSemaphore());
  }
}
