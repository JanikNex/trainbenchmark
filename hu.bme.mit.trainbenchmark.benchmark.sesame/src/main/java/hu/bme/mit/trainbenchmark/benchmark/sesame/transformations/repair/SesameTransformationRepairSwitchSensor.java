/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchSensorMatch;

import java.util.Collection;

public class SesameTransformationRepairSwitchSensor extends SesameTransformationRepair<SesameSwitchSensorMatch> {

	public SesameTransformationRepairSwitchSensor(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<SesameSwitchSensorMatch> matches) {
		for (final SesameSwitchSensorMatch ssm : matches) {
			final Node sw = ssm.getSw();
			final Node sensor = neoDriver.getGraphDb().createNode();
			sensor.addLabel(labelSensor);
			sw.createRelationshipTo(sensor, relationshipTypeSensor);
		}
	}

}
