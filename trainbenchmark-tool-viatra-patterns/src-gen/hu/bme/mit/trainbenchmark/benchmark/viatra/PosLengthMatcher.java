/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLength.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.PosLengthQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.posLength pattern,
 * providing pattern-specific query methods.
 *
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 *
 * <p>Matches of the pattern will be represented as {@link PosLengthMatch}.
 *
 * <p>Original source:
 * <code><pre>
 * pattern posLength(segment)
 * {
 * 	Segment.length(segment, length);
 * 	check(length {@literal <}= 0);
 * }
 * </pre></code>
 *
 * @see PosLengthMatch
 * @see PosLengthProcessor
 * @see PosLengthQuerySpecification
 *
 */
@SuppressWarnings("all")
public class PosLengthMatcher extends BaseMatcher<PosLengthMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   *
   */
  public static PosLengthMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    PosLengthMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (PosLengthMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }

  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   *
   */
  public static PosLengthMatcher create() throws ViatraQueryException {
    return new PosLengthMatcher();
  }

  private final static int POSITION_SEGMENT = 0;

  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(PosLengthMatcher.class);

  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   *
   */
  private PosLengthMatcher() throws ViatraQueryException {
    super(querySpecification());
  }

  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return matches represented as a PosLengthMatch object.
   *
   */
  public Collection<PosLengthMatch> getAllMatches(final Segment pSegment) {
    return rawStreamAllMatches(new Object[]{pSegment}).collect(Collectors.toList());
  }

  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return a match represented as a PosLengthMatch object, or null if no match is found.
   *
   */
  public PosLengthMatch getOneArbitraryMatch(final Segment pSegment) {
    return rawGetOneArbitraryMatch(new Object[]{pSegment}).get();
  }

  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   *
   */
  public boolean hasMatch(final Segment pSegment) {
    return rawHasMatch(new Object[]{pSegment});
  }

  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return the number of pattern matches found.
   *
   */
  public int countMatches(final Segment pSegment) {
    return rawCountMatches(new Object[]{pSegment});
  }

  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @param processor the action that will process each pattern match.
   *
   */
  public void forEachMatch(final Segment pSegment, final Consumer<? super PosLengthMatch> processor) {
    rawForEachMatch(new Object[]{pSegment}, processor);
  }

  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   *
   */
  public boolean forOneArbitraryMatch(final Segment pSegment, final Consumer<? super PosLengthMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSegment}, processor);
  }

  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return the (partial) match object.
   *
   */
  public PosLengthMatch newMatch(final Segment pSegment) {
    return PosLengthMatch.newMatch(pSegment);
  }

  /**
   * Retrieve the set of values that occur in matches for segment.
   * @return the Set of all values or empty set if there are no matches
   *
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT, parameters, results);
    return results;
  }

  /**
   * Retrieve the set of values that occur in matches for segment.
   * @return the Set of all values or empty set if there are no matches
   *
   */
  public Set<Segment> getAllValuesOfsegment() {
    return rawAccumulateAllValuesOfsegment(emptyArray());
  }

  @Override
  protected PosLengthMatch tupleToMatch(final Tuple t) {
    try {
        return PosLengthMatch.newMatch((Segment) t.get(POSITION_SEGMENT));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }

  @Override
  protected PosLengthMatch arrayToMatch(final Object[] match) {
    try {
        return PosLengthMatch.newMatch((Segment) match[POSITION_SEGMENT]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }

  @Override
  protected PosLengthMatch arrayToMatchMutable(final Object[] match) {
    try {
        return PosLengthMatch.newMutableMatch((Segment) match[POSITION_SEGMENT]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }

  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws ViatraQueryException if the pattern definition could not be loaded
   *
   */
  public static IQuerySpecification<PosLengthMatcher> querySpecification() throws ViatraQueryException {
    return PosLengthQuerySpecification.instance();
  }
}
