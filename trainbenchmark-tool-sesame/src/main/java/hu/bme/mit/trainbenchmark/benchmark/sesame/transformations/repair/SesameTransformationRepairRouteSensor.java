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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REQUIRES;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;

public class SesameTransformationRepairRouteSensor<TSesameDriver extends SesameDriver> extends SesameTransformation<SesameRouteSensorMatch, TSesameDriver> {

	public SesameTransformationRepairRouteSensor(final TSesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameRouteSensorMatch> matches) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI requires = vf.createIRI(BASE_PREFIX + REQUIRES);

		for (final SesameRouteSensorMatch match : matches) {
			final Resource route = match.getRoute();
			final Resource sensor = match.getSensor();

			con.add(route, requires, sensor);
		}
	}

}
