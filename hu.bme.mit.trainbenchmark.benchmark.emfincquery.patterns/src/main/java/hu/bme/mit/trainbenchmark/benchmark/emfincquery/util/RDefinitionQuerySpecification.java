package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RDefinitionMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RDefinitionMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;

/**
 * A pattern-specific query specification that can instantiate RDefinitionMatcher in a type-safe way.
 * 
 * @see RDefinitionMatcher
 * @see RDefinitionMatch
 * 
 */
@SuppressWarnings("all")
public final class RDefinitionQuerySpecification extends BaseGeneratedQuerySpecification<RDefinitionMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static RDefinitionQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected RDefinitionMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return RDefinitionMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.rDefinition";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("R","Sen");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("R", "Concept.Route"),new PParameter("Sen", "Concept.Sensor"));
  }
  
  @Override
  public RDefinitionMatch newEmptyMatch() {
    return RDefinitionMatch.newEmptyMatch();
  }
  
  @Override
  public RDefinitionMatch newMatch(final Object... parameters) {
    return RDefinitionMatch.newMatch((Concept.Route) parameters[0], (Concept.Sensor) parameters[1]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_R = body.getOrCreateVariableByName("R");
      PVariable var_Sen = body.getOrCreateVariableByName("Sen");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_R, "R"), 
        new ExportedParameter(body, var_Sen, "Sen")
      ));
      
      
      new TypeBinary(body, CONTEXT, var_R, var_Sen, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Route", "Route_routeDefinition"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Route.Route_routeDefinition");
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static RDefinitionQuerySpecification INSTANCE = make();
    
    public static RDefinitionQuerySpecification make() {
      return new RDefinitionQuerySpecification();					
      
    }
  }
}