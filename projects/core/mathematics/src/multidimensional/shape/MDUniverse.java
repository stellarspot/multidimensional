/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.shape;

import multidimensional.datatype.CMDList;
import multidimensional.datatype.CMDObservableList;
import multidimensional.datatype.ICMDList;
import multidimensional.datatype.ICMDObservableList;
import multidimensional.datatype.IMDList;
import multidimensional.datatype.IMDStack;
import multidimensional.datatype.MDStack;
import multidimensional.mathematics.ICMDVector;
import multidimensional.mathematics.IMDTransform;
import multidimensional.mathematics.IMDVector;
import multidimensional.shape.IMDAnimation;

/**
 *
 * @author stellarspot
 */
public class MDUniverse implements IMDUniverse {

    protected IMDShape root;
    protected ICMDObservableList<IMDCamera> cameras;
    protected ICMDList<IMDCameraElem> cameraElems = new CMDList<IMDCameraElem>();

    public MDUniverse(IMDShape shape, IMDCamera... cameras) {
        this.cameras = new CMDObservableList<IMDCamera>(cameras);
        setShape(shape);
    }

    @Override
    public IMDShape getShape() {
        return root;
    }

    @Override
    public void setShape(IMDShape shape) {
        this.root = shape;
    }

    @Override
    public ICMDObservableList<IMDCamera> getCameras() {
        return cameras;
    }

    @Override
    public void evaluate() {

        cameraElems.clear();
        //shapeElems.clear();

        IMDStack<ICMDObservableList<IMDTransform>> transformsStack = new MDStack<ICMDObservableList<IMDTransform>>();
        parse(root, transformsStack);

        IMDList<IMDCameraElem> elems = cameraElems.getIMDList();

        for (IMDCamera camera : cameras) {
            camera.draw(elems);
        }
    }

    protected void parse(IMDShape shape, IMDStack<ICMDObservableList<IMDTransform>> transformsStack) {

        for (IMDAnimation animation : shape.getAnimations()) {
            animation.animate();
        }

        transformsStack.push(shape.getTransforms());
        for (IMDShapeElem elem : shape.getElems()) {
            cameraElems.addTail(new MDCameraElem(elem, transformsStack));
        }

        for (IMDShape s : shape.getShapes()) {
            parse(s, transformsStack);
        }

        transformsStack.pop();
    }

    static class MDCameraElem implements IMDCameraElem {

        IMDShapeElem elem;
        IMDVector[] vertices;
        IMDList<Segment> segments;

        public MDCameraElem(IMDShapeElem elem, IMDStack<ICMDObservableList<IMDTransform>> transformsStack) {
            this.elem = elem;

            ICMDVector[] vectors = elem.getVertices();
            vertices = new IMDVector[vectors.length];

            for (int i = 0; i < vectors.length; i++) {
                vertices[i] = parseVertex(vectors[i], transformsStack);
            }

            ICMDList<Segment> s = new CMDList<Segment>();

            for(IMDShapeElem.Segment segment: elem.getSegments()){
                s.addTail(new CameraSegment(segment));
            }

            segments = s.getIMDList();

        }

        protected IMDVector parseVertex(ICMDVector vertex, IMDStack<ICMDObservableList<IMDTransform>> transformsStack) {
            IMDVector coordinats = vertex.getIMDVector();
            for (ICMDObservableList<IMDTransform> transforms : transformsStack) {
                for (IMDTransform transform : transforms) {
                    coordinats = transform.transform(coordinats);
                }
            }
            return coordinats;
        }

        @Override
        public IMDVector[] getVertices() {
            return vertices;
        }

        @Override
        public IMDList<Segment> getSegments() {
            return segments;
        }

        static class CameraSegment implements Segment {

            int vertex1;
            int vertex2;

            public CameraSegment(IMDShapeElem.Segment segment) {
                vertex1 = segment.getVertex1();
                vertex2 = segment.getVertex2();
            }

            @Override
            public int getVertex1() {
                return vertex1;
            }

            @Override
            public int getVertex2() {
                return vertex2;
            }
        }
    }
}
