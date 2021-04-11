package util.graph;

import components.Category;
import components.TodoElement;
import components.observable.IListener;
import components.task.Task;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObservableGraphUnitTest implements IListener<ObservableGraphChange<TodoElement>> {

    private ObservableGraph<TodoElement> obsGraph;
    private ObservableGraphChange<TodoElement> graphChange = null;
    private ObservableVertexChange<TodoElement> vertexChange = null;
    private Graph<TodoElement> graph;

    private int numOnChangeCalls;
    private Task t1, t2, t3, t4;
    private Category  root;
    private IVertex<TodoElement> rootV;



    @Before
    public void setup() {
        numOnChangeCalls = 0;
        t1 = new Task("t1");
        t2 = new Task("t2");
        t3 = new Task("t3");
        t4 = new Task("t4");
        root = new Category("Root");

        graph = new Graph();
        rootV = graph.addVertex(root);

        obsGraph = new ObservableGraph<>(graph);
    }

    @Test
    public void getVertices1() {
        // register listener
        obsGraph.startListen(this::onChange);
        numOnChangeCalls = 0;

        // TEST
        for (var v: obsGraph.getVertices())
            assertEquals(v.getElement(), rootV.getElement());

    }

    @Test
    public void getOutVertices1() {
        // register listener
        obsGraph.startListen(this::onChange);
        obsGraph.addVertex(t1, rootV);

        // TEST
        for (var v: obsGraph.getOutVertices(rootV)) {
            assertEquals(v.getElement(), t1);

        }
    }

    @Test
    public void getOutDegree1() {
        // register listener
        obsGraph.startListen(this::onChange);

        obsGraph.addVertex(t1, rootV);
        obsGraph.addVertex(t2, rootV);

        // TEST
        assertEquals(2, obsGraph.getOutDegree(rootV));
    }

    @Test
    public void getInVertices() {
        // register listener
        obsGraph.startListen(this::onChange);

        // TEST
        for (var v: obsGraph.getOutVertices(rootV))
            assertEquals(v.getElement(), rootV.getElement());
    }

    @Test
    public void getInDegree() { //TODO
        // register listener
        obsGraph.startListen(this::onChange);

        obsGraph.addVertex(t1, rootV);
        obsGraph.addVertex(t2, rootV);

        // TEST
        assertEquals(0, obsGraph.getInDegree(rootV));
    }

    @Test
    public void query1() {
        //query 2 param
        fail("Test not yet implemented");
    }

    @Test
    public void query2() {
        //query 3 param
        fail("Test not yet implemented");
    }

    @Test
    public void queryReachable1() {
        fail("Test not yet implemented");
    }

    @Test
    public void size() {
        // Register listener
        obsGraph.startListen(this::onChange);
        obsGraph.addVertex(t1, rootV);
        obsGraph.addVertex(t2, rootV);

        //TEST
        assertEquals(3, obsGraph.size());
    }

    @Test
    public void addVertex1() {
        // register listener
        obsGraph.startListen(this::onChange);
        numOnChangeCalls = 0;

        // add vertex
        var v = obsGraph.addVertex(t1);

        // check the listener is called only once
       assertTrue(numOnChangeCalls == 1);

        // check that the change variable is not null
        assertTrue(graphChange != null);

        // check that change only has 1 vertex added
        assertTrue(graphChange.addedVerticesSize() == 1);

        // check that the added vertex in change is the actual vertex
        for (var c : graphChange.getAddedVertices()) {
            assertEquals(c.getElement(), v.getElement());
        }

        // check that change has no removed vertices
        assertTrue(graphChange.removedVerticesSize() == 0);

        // check that no sort occurred
        assertTrue(graphChange.getSorted() == false);

        // check that no sorting comparator was given
        assertTrue(graphChange.getSortingComparator() == null);
    }

    @Test
    public void removeVertex1() {

        // register listener
        obsGraph.startListen(this::onChange);
        numOnChangeCalls = 0;

        // test the removal
        obsGraph.removeVertex(rootV);

        // check the listener is called only once
        System.out.println(numOnChangeCalls);
        assertTrue(numOnChangeCalls == 1);

        // check that the change variable is not null
        assertTrue(graphChange != null);

        // check that change only has 1 vertex removed
        assertTrue(graphChange.removedVerticesSize() == 1);

        // check that the removed vertex in change is the actual vertex
        for (var c : graphChange.getRemovedVertices()) {
            assertEquals(c.getElement(), rootV.getElement());
        }

        // check that change has no added vertices
        assertTrue(graphChange.addedVerticesSize() == 0);

        // check that no sort occurred
        assertTrue(graphChange.getSorted() == false);

        // check that no sorting comparator was given
        assertTrue(graphChange.getSortingComparator() == null);
    }

    @Test
    public void removeVertexReachable1() {
        fail("Note yet implemented in ObservableGraph.java");
    }

    @Test
    public void addDirectedEdge1() {
        // register listener
        var obsV = obsGraph.addVertex(t1);
        obsV.startListen(this::onVertexChange);


        obsGraph.addDirectedEdge(obsV.vertex, rootV);
        for (var u : vertexChange.getAddedEdges()) {
            assertEquals(u.getElement(), obsV);
        }
    }

    @Test
    public void removeDirectedEdge1() {


    }

    @Test
    public void sort1() {
        // Tests sort with 1 param
        fail("Test not yet implemented");
    }

    @Test
    public void sort2() {
        // Tests sort method that takes 2 params
        fail("Test not yet implemented");
    }

    @Test
    public void sortReachable1() {
        fail("Test not yet implemented");
    }


    @Override
    public void onChange(ObservableGraphChange<TodoElement> change) {
        numOnChangeCalls++;
       this.graphChange = change;
    }


    public void onVertexChange(ObservableVertexChange<TodoElement> change) {
        this.vertexChange = change;
    }
}
