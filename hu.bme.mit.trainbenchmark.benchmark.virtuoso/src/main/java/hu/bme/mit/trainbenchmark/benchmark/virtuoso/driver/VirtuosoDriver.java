/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.virtuoso.driver;

import java.io.File;
import java.io.IOException;

import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.TupleQuery;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.OpenRDFException;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;

import virtuoso.sesame2.driver.VirtuosoRepository;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

public class VirtuosoDriver extends SesameDriver{

	private final String VIRTUOSO_INSTANCE = "localhost";
	private final String VIRTUOSO_PORT = "1111";
	private final String VIRTUOSO_USERNAME = "dba";
	private final String VIRTUOSO_PASSWORD = "dba";
	
	protected VirtuosoRepository virtuosoRepository;
	
	public VirtuosoDriver(String queryPath) throws IOException {
		super(queryPath);
	}

	@Override
	public void beginTransaction() {
		f = virtuosoRepository.getValueFactory();
	}
	
	@Override
	public void read(String modelPath) throws IOException {
		virtuosoRepository = new VirtuosoRepository("jdbc:virtuoso://" + VIRTUOSO_INSTANCE + ":" + VIRTUOSO_PORT, VIRTUOSO_USERNAME, VIRTUOSO_PASSWORD);
		final File modelFile = new File(modelPath);
		try {
			virtuosoRepository.initialize();
			con = virtuosoRepository.getConnection();
			con.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);
		}
		catch (OpenRDFException e) {
			throw new IOException(e);
		}
	}
	
}