/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighbor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.semaphoreNeighbor pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class SemaphoreNeighborProcessor implements Consumer<SemaphoreNeighborMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSemaphore the value of pattern parameter semaphore in the currently processed match
   * @param pRoute1 the value of pattern parameter route1 in the currently processed match
   * @param pRoute2 the value of pattern parameter route2 in the currently processed match
   * @param pSensor1 the value of pattern parameter sensor1 in the currently processed match
   * @param pSensor2 the value of pattern parameter sensor2 in the currently processed match
   * @param pTe1 the value of pattern parameter te1 in the currently processed match
   * @param pTe2 the value of pattern parameter te2 in the currently processed match
   *
   */
  public abstract void process(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2);

  @Override
  public void accept(final SemaphoreNeighborMatch match) {
    process(match.getSemaphore(), match.getRoute1(), match.getRoute2(), match.getSensor1(), match.getSensor2(), match.getTe1(), match.getTe2());
  }
}
