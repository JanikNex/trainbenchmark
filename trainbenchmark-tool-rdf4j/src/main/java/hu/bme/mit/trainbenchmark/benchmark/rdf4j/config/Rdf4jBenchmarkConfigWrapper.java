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

package hu.bme.mit.trainbenchmark.benchmark.rdf4j.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigWrapper;

public class Rdf4jBenchmarkConfigWrapper extends RdfBenchmarkConfigWrapper {

	protected Rdf4jBenchmarkConfigWrapper() {
	}
	
	public Rdf4jBenchmarkConfigWrapper(final BenchmarkConfigCore bcc, final boolean inferencing) {
		super(bcc, inferencing);
	}
	
	@Override
	public String getToolName() {
		return "RDF4J" + getToolNamePostfix();
	}

	@Override
	public String getProjectName() {
		return "rdf4j";
	}
	
}