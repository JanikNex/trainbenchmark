/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ConnectedSegmentsInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.connectedSegmentsInject pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class ConnectedSegmentsInjectProcessor implements Consumer<ConnectedSegmentsInjectMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * @param pSegment1 the value of pattern parameter segment1 in the currently processed match
   * @param pSegment3 the value of pattern parameter segment3 in the currently processed match
   *
   */
  public abstract void process(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3);

  @Override
  public void accept(final ConnectedSegmentsInjectMatch match) {
    process(match.getSensor(), match.getSegment1(), match.getSegment3());
  }
}
