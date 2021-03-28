package util.graph;

import components.Category;
import components.TodoElement;
import components.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
//TODO confirm that the sort test is working once sort is implemented
/**
 * This class performs an extensive testing of our util.Graph structure
 *
 * @author Daniel Henderson
 */
public class GraphUnitTest {
    Graph<TodoElement> graph;
    Task t1, t2;
    Graph<TodoElement>.Vertex v1, v2;


    @Before
    public void setUp() {
        graph = new Graph<>(new Category("Test"));
        t1 = new Task("1");
        t2 = new Task("2");
        v1 = graph.addVertex(t1);
        v2 = graph.addVertex(t2);
    }


    @Test
    /** Adds a task to the graph, and confirm that the returned vertex's element is the task  **/
    public void addVertexTest1() {
        var v = graph.addVertex(new Task("test"));
        assertEquals(v.getElement().getName(), "test");
    }

    @Test
    /** Adds a null vertex testing that the IllegalArgumentException is thrown **/
    public void addVertexTest2() {
        try{
            graph.addVertex(null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("element can not be null"));
            return;
        }
        fail("Exception never thrown");
    }

    @Test
    /** Adds v1 again to test that the IllegalArgumentException is thrown **/
    public void addVertexTest3() {
            var v = graph.addVertex(v1.getElement());
            assertEquals(v, v1);
    }

    @Test
    /** Adds a Dependency between vertices from task 1 to task 2 **/
    public void addDirectedEdgeTest1() {
        graph.addDirectedEdge(v1, v2); // from v1 to v2
        for (var v : v1.getOutVertices()) {
            assertEquals(v.getElement(),v2.getElement());
        }
    }

    @Test
    /** Test if the default root vertex "" is the root vertex of the graph. **/
    public void getRootVertexTest() {
        assertEquals(graph.getRootVertex().getElement().getName(), "Test");
    }

    @Test
    /** Ensures that there is a one-to-one mapping between added vertices and vertices in the graph **/
    public void getVerticesTest() {
        HashSet<Graph<TodoElement>.Vertex> vertices = new HashSet<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(graph.getRootVertex());
        int size = vertices.size();

        // Test
        int i = 0;
        for (var v : graph.getVertices()) {
            assertTrue(vertices.contains(v));
            i++;
        }
        assertTrue(size == i);
    }

    @Test
    /** Test if the directed edge added to v1, v2 is removed from graph. **/
    public void removeDirectedEdgeTest() {
        graph.addDirectedEdge(v1, v2); // from v1 to v2
        graph.removeDirectedEdge(v1, v2);
        for (var c : v1.getOutVertices()) { // shouldn't enter b/c v1's only child was v2
            fail("Failed to remove directed edge");
        }
    }

    @Test
    /** Removes v1 and v2 from the graph and confirms only the root vertex is left **/
    public void removeVertexTest1() {
        graph.removeVertex(v1);
        graph.removeVertex(v2);
        for (var v : graph.getVertices()) {
            assertEquals(v.getElement(), graph.getRootVertex().getElement());
        }
    }

    @Test
    /** Ensures that IllegalArgumentException is thrown when trying to remove root vertex  **/
    public void removeVertextTest2() {
        try{
            graph.removeVertex(graph.getRootVertex());
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("given vertex is root vertex; can not remove root vertex"));
            return;
        }
        fail("Removed root node");
    }

    /** Tests the sort of tasks by creation data **/
    @Test
    public void sortTest() {
        // comparator definition
        Comparator<TodoElement> c = (t1, t2) -> {
            return t1 == null ? -1 : t1.creationDate.compareTo(t2.creationDate);
        };

        // Test
        graph.sort(c);

        TodoElement prev = null;

        // Testing the sort within our root vertex adjacency list
        for(var v : graph.getRootVertex().getOutVertices()) {
            assertTrue(c.compare(v.getElement(), prev) > 0);
            prev = v.getElement();
        }

        // Testing the sort of our adjacency list collection
        prev = null;
        for(var v : graph.getVertices()) {
            assertTrue(c.compare(v.getElement(), prev) > 0);
            prev = v.getElement();
        }
    }
    /** Testing a query for a specific TodoElement t1 **/
    @Test
    public void queryTest() {
        IQuery<TodoElement> query = (element) -> { return element == t1; };

        List<Graph<TodoElement>.Vertex> queryRes = graph.query(query);

        assertTrue(queryRes.contains(v1));
        assertEquals(1, queryRes.size());
    }
}
