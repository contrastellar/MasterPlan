package components.util.graph;

import java.util.HashMap;
import java.util.HashSet;

import components.Task.Task;

public class Graph {

    private final HashMap<Vertex, HashSet< HashSet<Vertex> >> graph;
    private final HashMap<Task, TaskVertex> ALL_TASKS = new HashMap<>();
    private final HashMap<Category, CategoryVertex> ALL_CATEGORIES = new HashMap<>();

    public final CategoryVertex rootVertex;

    public Graph(Graph graph, CategoryVertex rootVertex) {

        if(!graph.graph.containsKey(rootVertex))
            throw new IllegalArgumentException("Cannot set rootVertex to a vertex that isn't in provided graph");

        this.graph = graph.graph;
        this.rootVertex = rootVertex;
    }

    public Graph() {
        graph = new HashMap<>();
        rootVertex = new CategoryVertex(new Category("Main"));
    }

    // Allows for additional category attributes
    public class Category {
        String name;

        public Category(String name) {
            this.name = name;
        }
    }

    // Allows us to store multiple vertex types in our graph
    public abstract class Vertex {
        public Vertex() {
        }
    }


    public class TaskVertex extends Vertex {
        Task task;
        public TaskVertex(Task task) {
            super();
            this.task = task;
        }
    }

    public class CategoryVertex extends Vertex {
        Category c;

        public CategoryVertex(Category c) {
            super();
        }
    }

    // confirms that the added task is not already held in a VertexTask
    private TaskVertex getTaskVertex(Task task) {

        if (ALL_TASKS.containsKey(task))
            return ALL_TASKS.get(task);

        return new TaskVertex(task);

    }

    // confirms that the added task is not already held in a VertexTask
    private CategoryVertex getCategoryVertex(Category category) {

        if (ALL_CATEGORIES.containsKey(category))
            return ALL_CATEGORIES.get(category);

        return new CategoryVertex(category);
    }


    // A a new category to the graph
    public CategoryVertex addCategory(Vertex v1, Category category) throws GraphCircularException {
        CategoryVertex vCategory = getCategoryVertex(category);

        addEdge(v1, vCategory);

        ALL_CATEGORIES.put(category, vCategory);
        graph.put(vCategory, new HashSet< HashSet<Vertex> >());

        return vCategory;
    }

    // Add a new TaskVertex to the graph
    public Vertex addTaskVertex(Vertex v1, Task task) throws GraphCircularException {
        TaskVertex vTask = getTaskVertex(task);

        addEdge(v1, vTask);

        ALL_TASKS.put(task, vTask);
        graph.put(vTask, new HashSet< HashSet<Vertex> >());

        return vTask;
    }


    // check will take O(2v) = O(v)
    private void checkForCircularity() {
        // is there an induction proof for proving circularity, if so, this can possibly be reduced down to O(1)
    }

    // Add a new relationship from v1 -> v2
    public void addEdge(Vertex v1, Vertex v2) throws GraphCircularException {
        // confirm no circularity relationship is created
        // i.e. perform a dps on v2, and ensure it does not lead to v1

    }

    // TODO: Figure
    public static class GraphCircularException extends Exception { }


}
