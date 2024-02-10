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

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;

public class SesameTransformationInjectConnectedSegments<TSesameDriver extends SesameDriver> extends SesameTransformation<SesameConnectedSegmentsInjectMatch, TSesameDriver> {

	public SesameTransformationInjectConnectedSegments(final TSesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameConnectedSegmentsInjectMatch> matches) throws Exception {
		final RepositoryConnection connection = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI length = vf.createIRI(BASE_PREFIX + LENGTH);
		final IRI connectsTo = vf.createIRI(BASE_PREFIX + CONNECTS_TO);
		final IRI monitoredBy = vf.createIRI(BASE_PREFIX + MONITORED_BY);
		final IRI segmentType = vf.createIRI(BASE_PREFIX + SEGMENT);
		final Literal lengthLiteral = vf.createLiteral(TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH);

		for (final SesameConnectedSegmentsInjectMatch match : matches) {
			// create (segment2) node
			final Long newVertexId = driver.generateNewVertexId();
			final IRI segment2 = vf.createIRI(BASE_PREFIX + ID_PREFIX + newVertexId);
			connection.add(segment2, RDF.TYPE, segmentType);
			connection.add(segment2, length, lengthLiteral);

			// (segment1)-[:connectsTo]->(segment2)
			connection.add(match.getSegment1(), connectsTo, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			connection.add(segment2, connectsTo, match.getSegment3());

			// (segment2)-[:monitoredBy]->(sensor)
			connection.add(segment2, monitoredBy, match.getSensor());

			// remove (segment1)-[:connectsTo]->(segment3)
			connection.remove(match.getSegment1(), connectsTo, match.getSegment3());
		}
	}

}
