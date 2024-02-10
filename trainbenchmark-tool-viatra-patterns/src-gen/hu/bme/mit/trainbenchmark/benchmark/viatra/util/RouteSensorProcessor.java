/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.routeSensor pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class RouteSensorProcessor implements Consumer<RouteSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pRoute the value of pattern parameter route in the currently processed match
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * @param pSwP the value of pattern parameter swP in the currently processed match
   * @param pSw the value of pattern parameter sw in the currently processed match
   *
   */
  public abstract void process(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw);

  @Override
  public void accept(final RouteSensorMatch match) {
    process(match.getRoute(), match.getSensor(), match.getSwP(), match.getSw());
  }
}
