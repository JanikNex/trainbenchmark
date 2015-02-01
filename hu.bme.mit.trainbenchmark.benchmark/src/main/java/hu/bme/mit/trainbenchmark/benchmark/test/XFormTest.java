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

package hu.bme.mit.trainbenchmark.benchmark.test;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class XFormTest extends TransformationTest {

	@Test
	public void posLengthXForm() throws ParseException, IOException {
		testTransformation("PosLength", "XForm", 4, 3);
	}

	@Test
	public void routeSensorXForm() throws ParseException, IOException {
		testTransformation("RouteSensor", "XForm", 2, 1);
	}

	@Test
	public void signalNeighborXForm() throws ParseException, IOException {
		testTransformation("SignalNeighbor", "XForm", 1, 0);
	}

	@Test
	public void switchSensorXForm() throws ParseException, IOException {
		testTransformation("SwitchSensor", "XForm", 7, 6);
	}

}