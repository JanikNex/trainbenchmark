/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLength.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.posLength pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class PosLengthProcessor implements Consumer<PosLengthMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSegment the value of pattern parameter segment in the currently processed match
   *
   */
  public abstract void process(final Segment pSegment);

  @Override
  public void accept(final PosLengthMatch match) {
    process(match.getSegment());
  }
}
