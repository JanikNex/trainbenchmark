package hu.bme.mit.trainbenchmark.benchmark.gcl.operations;

import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.*;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.queries.*;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject.*;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.repair.GclTransformationProvidedRepair;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class GclModelOperationFactory<TDriver extends GclDriver> extends ModelOperationFactory<GclMatch, TDriver> {

	@Override
	public ModelOperation<? extends GclMatch, TDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
																	   final TDriver driver) throws Exception {

		switch (operationEnum) {
			// ConnectedSegments
			case CONNECTEDSEGMENTS: {
				final GclQuery<GclConnectedSegmentsMatch, TDriver> query = new GclQueryConnectedSegments<>(driver);
				final ModelOperation<GclConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}

			case CONNECTEDSEGMENTS_INJECT: {
				final GclQuery<GclConnectedSegmentsInjectMatch, TDriver> query = new GclQueryConnectedSegmentsInject<>(driver);
				final GclTransformation<GclConnectedSegmentsInjectMatch, TDriver> transformation = new GclTransformationInjectConnectedSegments<>(driver);
				final ModelOperation<GclConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;

			}
			case CONNECTEDSEGMENTS_REPAIR: {
				final GclQuery<GclConnectedSegmentsMatch, TDriver> query = new GclQueryConnectedSegments<>(driver);
				final GclTransformation<GclConnectedSegmentsMatch, TDriver> transformation = new GclTransformationProvidedRepair<>(driver);
				final ModelOperation<GclConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// PosLength
			case POSLENGTH: {
				final GclQuery<GclPosLengthMatch, TDriver> query = new GclQueryPosLength<>(driver);
				final ModelOperation<GclPosLengthMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}

			case POSLENGTH_INJECT: {
				final GclQuery<GclPosLengthInjectMatch, TDriver> query = new GclQueryPosLengthInject<>(driver);
				final GclTransformation<GclPosLengthInjectMatch, TDriver> transformation = new GclTransformationInjectPosLength<>(driver);
				final ModelOperation<GclPosLengthInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;

			}
			case POSLENGTH_REPAIR: {
				final GclQuery<GclPosLengthMatch, TDriver> query = new GclQueryPosLength<>(driver);
				final GclTransformation<GclPosLengthMatch, TDriver> transformation = new GclTransformationProvidedRepair<>(driver);
				final ModelOperation<GclPosLengthMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// RouteSensor
			case ROUTESENSOR: {
				final GclQuery<GclRouteSensorMatch, TDriver> query = new GclQueryRouteSensor<>(driver);
				final ModelOperation<GclRouteSensorMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}

			case ROUTESENSOR_INJECT: {
				final GclQuery<GclRouteSensorInjectMatch, TDriver> query = new GclQueryRouteSensorInject<>(driver);
				final GclTransformation<GclRouteSensorInjectMatch, TDriver> transformation = new GclTransformationInjectRouteSensor<>(driver);
				final ModelOperation<GclRouteSensorInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;

			}
			case ROUTESENSOR_REPAIR: {
				final GclQuery<GclRouteSensorMatch, TDriver> query = new GclQueryRouteSensor<>(driver);
				final GclTransformation<GclRouteSensorMatch, TDriver> transformation = new GclTransformationProvidedRepair<>(driver);
				final ModelOperation<GclRouteSensorMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// SemaphoreNeighbor
			case SEMAPHORENEIGHBOR: {
				final GclQuery<GclSemaphoreNeighborMatch, TDriver> query = new GclQuerySemaphoreNeighbor<>(driver);
				final ModelOperation<GclSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}

			case SEMAPHORENEIGHBOR_INJECT: {
				final GclQuery<GclSemaphoreNeighborInjectMatch, TDriver> query = new GclQuerySemaphoreNeighborInject<>(driver);
				final GclTransformation<GclSemaphoreNeighborInjectMatch, TDriver> transformation = new GclTransformationInjectSemaphoreNeighbor<>(driver);
				final ModelOperation<GclSemaphoreNeighborInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;

			}
			case SEMAPHORENEIGHBOR_REPAIR: {
				final GclQuery<GclSemaphoreNeighborMatch, TDriver> query = new GclQuerySemaphoreNeighbor<>(driver);
				final GclTransformation<GclSemaphoreNeighborMatch, TDriver> transformation = new GclTransformationProvidedRepair<>(driver);
				final ModelOperation<GclSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// SwitchMonitored
			case SWITCHMONITORED: {
				final GclQuery<GclSwitchMonitoredMatch, TDriver> query = new GclQuerySwitchMonitored<>(driver);
				final ModelOperation<GclSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}

			case SWITCHMONITORED_INJECT: {
				final GclQuery<GclSwitchMonitoredInjectMatch, TDriver> query = new GclQuerySwitchMonitoredInject<>(driver);
				final GclTransformation<GclSwitchMonitoredInjectMatch, TDriver> transformation = new GclTransformationInjectSwitchMonitored<>(driver);
				final ModelOperation<GclSwitchMonitoredInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;

			}
			case SWITCHMONITORED_REPAIR: {
				final GclQuery<GclSwitchMonitoredMatch, TDriver> query = new GclQuerySwitchMonitored<>(driver);
				final GclTransformation<GclSwitchMonitoredMatch, TDriver> transformation = new GclTransformationProvidedRepair<>(driver);
				final ModelOperation<GclSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// SwitchSet
			case SWITCHSET: {
				final GclQuery<GclSwitchSetMatch, TDriver> query = new GclQuerySwitchSet<>(driver);
				final ModelOperation<GclSwitchSetMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}

			case SWITCHSET_INJECT: {
				final GclQuery<GclSwitchSetInjectMatch, TDriver> query = new GclQuerySwitchSetInject<>(driver);
				final GclTransformation<GclSwitchSetInjectMatch, TDriver> transformation = new GclTransformationInjectSwitchSet<>(driver);
				final ModelOperation<GclSwitchSetInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;

			}
			case SWITCHSET_REPAIR: {
				final GclQuery<GclSwitchSetMatch, TDriver> query = new GclQuerySwitchSet<>(driver);
				final GclTransformation<GclSwitchSetMatch, TDriver> transformation = new GclTransformationProvidedRepair<>(driver);
				final ModelOperation<GclSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			default:
				break;
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
