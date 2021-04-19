package models;

import components.Category;
import components.Tag;
import components.TodoElement;
import components.observable.IReadOnlyObservableList;
import components.observable.Observable;
import components.observable.ObservableList;
import components.task.Task;
import util.graph.*;

import java.util.ArrayList;
import java.util.Comparator;

public class MainModel {

    public final ObservableGraph<TodoElement> obsGraph;
    public final Observable<ObservableVertex<TodoElement>> selectedVertex = new Observable<>();

    public final ObservableList<Tag> _tags = new ObservableList<>(new ArrayList<>());
    public final IReadOnlyObservableList<Tag> tags = _tags;

    public MainModel() {
        // deserialize graph
        this.obsGraph = new ObservableGraph<>(new Graph<>());
        selectedVertex.setValue(obsGraph.addVertex(new Category("Main")));
        var vertex = obsGraph.addVertex(new Task("task of main"), selectedVertex.getValue());
        obsGraph.addVertex(new Task("task of task of main"), vertex);
        obsGraph.addVertex(new Task("task of main 2"), selectedVertex.getValue());
        obsGraph.addVertex(new Category("category of main 3"), selectedVertex.getValue());
    }

    public void importGoogleCalendar(IVertex<TodoElement> rootVertex) {

    }

    public void addTag(Tag tag) {
        _tags.add(tag);
    }

    public void removeTag(Tag tag) {
        _tags.remove(tag);
    }

    public void sortTags(Comparator<Tag> c) {
        _tags.sort(c);
    }

    public void exportGoogleCalendar() {
        throw new UnsupportedOperationException("not implemented yet");
    }




}
