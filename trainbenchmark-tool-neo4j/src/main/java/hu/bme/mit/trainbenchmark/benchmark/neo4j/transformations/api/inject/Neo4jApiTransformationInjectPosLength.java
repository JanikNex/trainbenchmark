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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.util.Collection;

public class Neo4jApiTransformationInjectPosLength extends Neo4jApiTransformation<Neo4jPosLengthInjectMatch> {

	public Neo4jApiTransformationInjectPosLength(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jPosLengthInjectMatch> matches) {
		Transaction tx = Neo4jDriver.getTmpTransaction();
		for (final Neo4jPosLengthInjectMatch match : matches) {
			Node segment = tx.getNodeByElementId(match.getSegment());
			segment.setProperty(ModelConstants.LENGTH, 0);
		}
	}

}
