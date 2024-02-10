/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLengthInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.posLengthInject pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class PosLengthInjectProcessor implements Consumer<PosLengthInjectMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSegment the value of pattern parameter segment in the currently processed match
   *
   */
  public abstract void process(final Segment pSegment);

  @Override
  public void accept(final PosLengthInjectMatch match) {
    process(match.getSegment());
  }
}
