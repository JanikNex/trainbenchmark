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
package hu.bme.mit.trainbenchmark.benchmark.sesame.comparators;

import org.eclipse.rdf4j.model.IRI;

import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.BaseMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;

public class SesameMatchComparator extends BaseMatchComparator<SesameMatch, IRI> {

	public SesameMatchComparator() {
		super(new IriComparator());
	}

}
