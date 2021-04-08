package models;

import components.Category;
import components.TodoElement;
import components.observable.Observable;
import components.task.Task;
import util.graph.Graph;
import util.graph.IVertex;
import util.graph.ObservableGraph;
import util.graph.ObservableVertex;

public class MainModel {

    public final ObservableGraph<TodoElement> obsGraph;
    public final Observable<ObservableVertex<TodoElement>> selectedVertex = new Observable<>();

    public MainModel() {
        // deserialize graph
        this.obsGraph = new ObservableGraph<>(new Graph<>());
        selectedVertex.setValue(obsGraph.addVertex(new Category("Main")));
        var vertex = obsGraph.addVertex(new Task("task of main"), selectedVertex.getValue());
        obsGraph.addVertex(new Task("task of task of main"), vertex);
        obsGraph.addVertex(new Task("task of main 2"), selectedVertex.getValue());
        obsGraph.addVertex(new Category("category of main 3"), selectedVertex.getValue());
    }

    public void addCategory(Category c, IVertex<TodoElement> root) {
        obsGraph.addVertex(c, root);
    }

    public void addTask(Task t, IVertex<TodoElement> root) {
        obsGraph.addVertex(t, root);
    }

    public void importGoogleCalendar() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public void exportGoogleCalendar() {
        throw new UnsupportedOperationException("not implemented yet");
    }




}
