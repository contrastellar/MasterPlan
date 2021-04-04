package models;

import components.TodoElement;
import util.graph.Graph;
import util.graph.ObservableGraph;

public class MainModel {

    public final ObservableGraph<TodoElement> graph;

    public MainModel() {
        // deserialize graph
        Graph<TodoElement> _graph = new Graph<>();

        this.graph = new ObservableGraph<>(_graph);
    }

    public void importGoogleCalendar() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public void exportGoogleCalendar() {
        throw new UnsupportedOperationException("not implemented yet");
    }




}
