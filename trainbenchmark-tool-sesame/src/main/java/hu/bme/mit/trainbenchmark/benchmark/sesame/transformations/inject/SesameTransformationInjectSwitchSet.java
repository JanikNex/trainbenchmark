/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.rdf.RdfHelper;

public class SesameTransformationInjectSwitchSet<TSesameDriver extends SesameDriver> extends SesameTransformation<SesameSwitchSetInjectMatch, TSesameDriver> {

	public SesameTransformationInjectSwitchSet(final TSesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameSwitchSetInjectMatch> matches) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI currentPositionProperty = vf.createIRI(BASE_PREFIX + CURRENTPOSITION);

		for (final SesameSwitchSetInjectMatch match : matches) {
			final IRI sw = match.getSw();
			final RepositoryResult<Statement> statements = con.getStatements(sw, currentPositionProperty, null, true);
			if (!statements.hasNext()) {
				continue;
			}

			final Statement oldStatement = statements.next();

			// delete old statement
			con.remove(oldStatement);

			// get next enum value
			final IRI currentPositionIRI = (IRI) oldStatement.getObject();
			final String currentPositionRDFString = currentPositionIRI.getLocalName();
			final String currentPositionString = RdfHelper.removePrefix(Position.class, currentPositionRDFString);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];
			final String newCurrentPositionString = RdfHelper.addEnumPrefix(newCurrentPosition);
			final IRI newCurrentPositionUri = vf.createIRI(BASE_PREFIX + newCurrentPositionString);

			// set new value
			con.add(sw, currentPositionProperty, newCurrentPositionUri);
		}
	}

}
