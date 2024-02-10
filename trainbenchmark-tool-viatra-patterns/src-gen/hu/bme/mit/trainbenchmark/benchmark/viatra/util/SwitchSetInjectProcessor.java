/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchSetInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Switch;

import java.util.function.Consumer;


/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.switchSetInject pattern.
 *
 * Clients should derive an (anonymous) class that implements the abstract process().
 *
 */
@SuppressWarnings("all")
public abstract class SwitchSetInjectProcessor implements Consumer<SwitchSetInjectMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSw the value of pattern parameter sw in the currently processed match
   *
   */
  public abstract void process(final Switch pSw);

  @Override
  public void accept(final SwitchSetInjectMatch match) {
    process(match.getSw());
  }
}
