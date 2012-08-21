/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.shape;

import java.util.Hashtable;
import java.util.Map;
import multidimensions.datatype.CMDList;
import multidimensions.datatype.ICMDList;
import multidimensions.datatype.IMDList;
import multidimensions.mathematics.IMDVector;
import multidimensions.shape.camera.IMDCameraElements;
import multidimensions.shape.camera.IMDCameraSegment;
import multidimensions.shape.camera.IMDCameraVertex;

/**
 *
 * @author stellarspot
 */
public class MDUniverseCarcas implements IMDShapeCarcass {

    IMDShape root;
    //ICMDList<VertexCarcass> vertices = new CMDList<VertexCarcass>();
    ICMDList<SegmentCarcass> segments = new CMDList<SegmentCarcass>();
    Map<IMDShapeVertex, VertexCarcass> verticesMap = new Hashtable<IMDShapeVertex, VertexCarcass>();

    public void setRoot(IMDShape shape) {
        root = shape;
    }

    public IMDShape getRoot() {
        return root;
    }

    public IMDCameraElements getCameraElements() {
        System.out.println("parse");
        parse();

        CameraElements elements = new CameraElements();
        System.out.println("handle segments");

        for (SegmentCarcass segment : segments) {
            System.out.println("fill segment: " + segment);
            elements.segments.addLast(segment.getCameraSegment());
        }

        return elements;
    }

    protected void parse() {
        parse(root);
    }

    protected void parse(IMDShape shape) {
        for (IMDShapeSegment segment : shape.getSegments()) {
            parseSegment(segment);
        }

        for (IMDShape s : shape.getShapes()) {
            parse(s);
        }
    }

    protected void parseVertex(IMDShapeVertex vertex) {
        System.out.println("parse vertex: " + vertex);

        VertexCarcass vertexCarcas = new VertexCarcass(vertex);
        verticesMap.put(vertex, vertexCarcas);
        //vertices.addLast(vertexCarcas);

    }

    protected void parseSegment(IMDShapeSegment segment) {
        System.out.println("parse segment: " + segment);
        parseVertex(segment.getVertex1());
        parseVertex(segment.getVertex2());

        VertexCarcass vertex1 = verticesMap.get(segment.getVertex1());
        VertexCarcass vertex2 = verticesMap.get(segment.getVertex2());

        SegmentCarcass segmentCarcass = new SegmentCarcass(segment, vertex1, vertex2);
        segments.addLast(segmentCarcass);


    }

    static class VertexCarcass {

        IMDShapeVertex source;

        public VertexCarcass(IMDShapeVertex source) {
            this.source = source;
        }

        CameraVertex getCameraVertex() {
            return new CameraVertex(source.getCoordinats());
        }

        static class CameraVertex implements IMDCameraVertex {

            IMDVector coordinats;

            public CameraVertex(IMDVector coordinats) {
                this.coordinats = coordinats;
            }

            public IMDVector getCordinats() {
                return coordinats;
            }

            @Override
            public String toString() {
                return "" + coordinats;
            }
            
            
        }
    }

    static class SegmentCarcass {

        VertexCarcass vertex1;
        VertexCarcass vertex2;
        IMDShapeSegment segment;

        public SegmentCarcass(IMDShapeSegment segment, VertexCarcass vertex1, VertexCarcass vertex2) {
            this.segment = segment;
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        IMDCameraSegment getCameraSegment() {
            return new CameraSegment(vertex1.getCameraVertex(), vertex2.getCameraVertex());
        }

        @Override
        public String toString() {
            return "" + segment;
        }

        static class CameraSegment implements IMDCameraSegment {

            IMDCameraVertex vertex1;
            IMDCameraVertex vertex2;

            public CameraSegment(IMDCameraVertex vertex1, IMDCameraVertex vertex2) {
                this.vertex1 = vertex1;
                this.vertex2 = vertex2;
            }

            public IMDCameraVertex getVertex1() {
                return vertex1;
            }

            public IMDCameraVertex getVertex2() {
                return vertex2;
            }

            @Override
            public String toString() {
                return "(" + vertex1 + ", " + vertex2 + ")";
            }
            
            
        }
    }

    class CameraElements implements IMDCameraElements {

        CMDList<IMDCameraSegment> segments = new CMDList<IMDCameraSegment>();

        public IMDList<IMDCameraSegment> getSegments() {
            return segments;
        }
    }
}
