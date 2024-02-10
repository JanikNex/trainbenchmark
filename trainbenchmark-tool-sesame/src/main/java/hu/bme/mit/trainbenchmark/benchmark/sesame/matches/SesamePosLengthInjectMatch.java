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
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_LENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;

import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch;

public class SesamePosLengthInjectMatch extends SesameMatch implements PosLengthMatch {

	public SesamePosLengthInjectMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public IRI getSegment() {
		return (IRI) bs.getValue(VAR_SEGMENT);
	}

	public Value getLength() {
		return bs.getValue(VAR_LENGTH);
	}

}
