/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchMonitored.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.HasSensorMatch;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.hasSensor pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class HasSensorProcessor implements Consumer<HasSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSw the value of pattern parameter sw in the currently processed match
   *
   */
  public abstract void process(final TrackElement pSw);

  @Override
  public void accept(final HasSensorMatch match) {
    process(match.getSw());
  }
}
