package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.inject;

import de.nexus.modelserver.proto.ModelServerEditStatements;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver.GclDriver;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.matches.GclMatch;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformation;
import hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.transformation.GclTransformationUtils;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.util.List;

public class GclTransformationInjectConnectedSegments<TDriver extends GclDriver, TConnectedSegmentsInjectMatch extends GclConnectedSegmentsInjectMatch> extends GclTransformation<TConnectedSegmentsInjectMatch, TDriver> {

	public GclTransformationInjectConnectedSegments(TDriver driver, boolean collectiveTransformation) {
		super(driver, collectiveTransformation);
	}

	@Override
	protected List<ModelServerEditStatements.EditRequest> getTransformation(GclMatch match) {
		ModelServerEditStatements.Node segment1 = GclTransformationUtils.getNode(((GclConnectedSegmentsInjectMatch) match).getSegment1());
		ModelServerEditStatements.Node segment2 = GclTransformationUtils.getNode("seg2");
		ModelServerEditStatements.Node segment3 = GclTransformationUtils.getNode(((GclConnectedSegmentsInjectMatch) match).getSegment3());
		ModelServerEditStatements.Node sensor = GclTransformationUtils.getNode(((GclConnectedSegmentsInjectMatch) match).getSensor());
		ModelServerEditStatements.Node region = GclTransformationUtils.getNode(((GclConnectedSegmentsInjectMatch) match).getRegion());
		ModelServerEditStatements.EditRequest editRequest1 = GclTransformationUtils.getCreateNodeRequest("seg2", "railway.Segment", List.of(
			ModelServerEditStatements.EditCreateNodeAttributeAssignment.newBuilder()
				.setAttributeName(QueryConstants.VAR_LENGTH)
				.setAttributeValue(String.valueOf(TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH))
				.build()
		));
		ModelServerEditStatements.EditRequest editRequest2 = GclTransformationUtils.getDeleteEdgeRequest(segment1, segment3, "connectsTo");
		ModelServerEditStatements.EditRequest editRequest3 = GclTransformationUtils.getCreateEdgeRequest(segment1, segment2, "connectsTo");
		ModelServerEditStatements.EditRequest editRequest4 = GclTransformationUtils.getCreateEdgeRequest(segment2, segment3, "connectsTo");
		ModelServerEditStatements.EditRequest editRequest5 = GclTransformationUtils.getCreateEdgeRequest(segment2, sensor, "monitoredBy");
		ModelServerEditStatements.EditRequest editRequest6 = GclTransformationUtils.getCreateEdgeRequest(region, segment2, "elements");
		return List.of(editRequest1, editRequest2, editRequest3, editRequest4, editRequest5, editRequest6);
	}
}
