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

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesamePosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class SesameTransformationInjectPosLength<TSesameDriver extends SesameDriver> extends SesameTransformation<SesamePosLengthInjectMatch, TSesameDriver> {

	public SesameTransformationInjectPosLength(final TSesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesamePosLengthInjectMatch> matches) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI typeURI = vf.createIRI(BASE_PREFIX + ModelConstants.LENGTH);
		final Literal zeroLiteral = vf.createLiteral(0);

		for (final SesamePosLengthInjectMatch match : matches) {
			final IRI segment = match.getSegment();

			final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, typeURI, null, true);
			con.remove(statementsToRemove);

			con.add(segment, typeURI, zeroLiteral);
		}
	}

}
