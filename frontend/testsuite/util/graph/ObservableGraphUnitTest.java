package util.graph;

import components.Category;
import components.TodoElement;
import components.observable.IListener;
import components.task.Task;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObservableGraphUnitTest {

//    private ObservableGraph<TodoElement> obsGraph;
//    private ObservableGraphChange<TodoElement> graphChange = null;
//    private ObservableVertexChange<TodoElement> vertexChange = null;
//
//    private int numOnChangeCalls;
//    private Task t1, t2, t3, t4;
//
//
//
//    @Before
//    public void setup() {
//        numOnChangeCalls = 0;
//        t1 = new Task("t1");
//        t2 = new Task("t2");
//        t3 = new Task("t3");
//        t4 = new Task("t4");
//        obsGraph = new ObservableGraph<>(new Graph<>());
//    }
//
//    @Test
//    public void getVertices1() {
//        // register listener
//        var t1Vertex = obsGraph.addVertex(t1);
//        obsGraph.startListen(this::onChange);
//        numOnChangeCalls = 0;
//
//        // TEST
//        for (var v: obsGraph.getVertices())
//            assertEquals(v, t1V);
//    }
//
//    @Test
//    public void getOutVertices1() {
//        var t1Vertex obsGraph.addVertex(t1);
//
//        // register listener
//        obsGraph.startListen(this::onChange);
//
//        // TEST
//        for (var v: obsGraph.getOutVertices(rootV)) {
//            assertEquals(v.getElement(), t1);
//
//        }
//    }
//
//    @Test
//    public void getOutDegree1() {
//        // register listener
//        obsGraph.startListen(this::onChange);
//
//        obsGraph.addVertex(t1, rootV);
//        obsGraph.addVertex(t2, rootV);
//
//        // TEST
//        assertEquals(2, obsGraph.getOutDegree(rootV));
//    }
//
//    @Test
//    public void getInVertices() {
//        // register listener
//        obsGraph.startListen(this::onChange);
//
//        // TEST
//        for (var v: obsGraph.getOutVertices(rootV))
//            assertEquals(v.getElement(), rootV.getElement());
//    }
//
//    @Test
//    public void getInDegree() { //TODO
//        // register listener
//        obsGraph.startListen(this::onChange);
//
//        obsGraph.addVertex(t1, rootV);
//        obsGraph.addVertex(t2, rootV);
//
//        // TEST
//        assertEquals(0, obsGraph.getInDegree(rootV));
//    }
//
//    @Test
//    public void query1() {
//        //query 2 param
//        fail("Test not yet implemented");
//    }
//
//    @Test
//    public void query2() {
//        //query 3 param
//        fail("Test not yet implemented");
//    }
//
//    @Test
//    public void queryReachable1() {
//        fail("Test not yet implemented");
//    }
//
//    @Test
//    public void size() {
//        // Register listener
//        obsGraph.startListen(this::onChange);
//        obsGraph.addVertex(t1, rootV);
//        obsGraph.addVertex(t2, rootV);
//
//        //TEST
//        assertEquals(3, obsGraph.size());
//    }
//
//    @Test
//    public void addVertex1() {
//        // register listener
//        obsGraph.startListen(this::onChange);
//        numOnChangeCalls = 0;
//
//        // add vertex
//        var v = obsGraph.addVertex(t1);
//
//        // check the listener is called only once
//        assertEquals(1, numOnChangeCalls);
//
//        // check that the change variable is not null
//        assertNotNull(graphChange);
//
//        // check that change only has 1 vertex added
//        assertEquals(1, graphChange.addedVerticesSize());
//
//        // check that the added vertex in change is the actual vertex
//        assertEquals(graphChange.getAddedVertices().get(0), v);
//
//        // check that change has no removed vertices
//        assertEquals(0, graphChange.removedVerticesSize());
//
//        // check that no sort occurred
//        assertFalse(graphChange.getSorted());
//
//        // check that no sorting comparator was given
//        assertNull(graphChange.getSortingComparator());
//    }
//
//    @Test // Test ObservableGraphChange
//    public void removeVertex1() {
//
//        var t1V = obsGraph.addVertex(t1);
//
//        // register listener
//        obsGraph.startListen(this::onChange);
//        numOnChangeCalls = 0;
//
//        obsGraph.removeVertex(t1V);
//
//        // check the listener is called only once
//        assertTrue(numOnChangeCalls == 1);
//
//        // check that the change variable is not null
//        assertTrue(graphChange != null);
//
//        // check that change only has 1 vertex removed
//        assertTrue(graphChange.removedVerticesSize() == 1);
//
//        // check that the removed vertex in change is the actual vertex
//        assertEquals(graphChange.getRemovedVertices().get(0), t1V);
//
//        // check that change has no added vertices
//        assertTrue(graphChange.addedVerticesSize() == 0);
//
//        // check that no sort occurred
//        assertTrue(graphChange.getSorted() == false);
//
//        // check that no sorting comparator was given
//        assertTrue(graphChange.getSortingComparator() == null);
//    }
//
//    @Test // Test ObservableVertexChange
//    public void removeVertex2() {
//
//        var v = obsGraph.addVertex(t2);
//
//        var t1V = obsGraph.addVertex(t1, v);
//
//        // register listener
//        obsGraph.startListen(this::onChange);
//        numOnChangeCalls = 0;
//
//        obsGraph.removeVertex(t1V);
//
//        // check the listener is called only once
//        assertTrue(numOnChangeCalls == 1);
//
//        // check that the change variable is not null
//        assertTrue(graphChange != null);
//
//        // check that change only has 1 vertex removed
//        assertTrue(graphChange.removedVerticesSize() == 1);
//
//        // check that the removed vertex in change is the actual vertex
//        assertEquals(graphChange.getRemovedVertices().get(0), t1V);
//
//        // check that change has no added vertices
//        assertTrue(graphChange.addedVerticesSize() == 0);
//
//        // check that no sort occurred
//        assertTrue(graphChange.getSorted() == false);
//
//        // check that no sorting comparator was given
//        assertTrue(graphChange.getSortingComparator() == null);
//    }
//
//    @Test
//    public void removeVertexReachable1() {
//        fail("Note yet implemented in ObservableGraph.java");
//    }
//
//    @Test
//    public void addDirectedEdge1() {
//        // register listener
//        var obsV = obsGraph.addVertex(t1);
//        obsV.startListen(this::onVertexChange);
//
//
//        obsGraph.addDirectedEdge(obsV.vertex, rootV);
//        for (var u : vertexChange.getAddedEdges()) {
//            assertEquals(u.getElement(), obsV);
//        }
//    }
//
//    @Test
//    public void removeDirectedEdge1() {
//
//
//    }
//
//    @Test
//    public void sort1() {
//        // Tests sort with 1 param
//        fail("Test not yet implemented");
//    }
//
//    @Test
//    public void sort2() {
//        // Tests sort method that takes 2 params
//        fail("Test not yet implemented");
//    }
//
//    @Test
//    public void sortReachable1() {
//        fail("Test not yet implemented");
//    }
//
//
//    @Override
//    public void onChange(ObservableGraphChange<TodoElement> change) {
//        numOnChangeCalls++;
//       this.graphChange = change;
//    }
//
//
//    public void onVertexChange(ObservableVertexChange<TodoElement> change) {
//        this.vertexChange = change;
//    }
}
