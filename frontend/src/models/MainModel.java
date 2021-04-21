package models;

import components.Category;
import components.Tag;
import components.TodoElement;
import components.observable.IReadOnlyObservableList;
import components.observable.Observable;
import components.observable.ObservableList;
import util.graph.Graph;
import util.graph.IVertex;
import util.graph.ObservableGraph;
import util.graph.ObservableVertex;

import java.util.ArrayList;
import java.util.Comparator;

public class MainModel {

    public static final MainModel model = new MainModel();

    public final ObservableGraph<TodoElement> obsGraph;
    public final Observable<ObservableVertex<TodoElement>> selectedVertex = new Observable<>();
    public final Observable<ObservableVertex<TodoElement>> editVertex = new Observable<>();

    public final ObservableList<Tag> _tags = new ObservableList<>(new ArrayList<>());
    public final IReadOnlyObservableList<Tag> tags = _tags;

    private MainModel() {
        // deserialize graph
        this.obsGraph = new ObservableGraph<>(new Graph<>());
        selectedVertex.setValue(obsGraph.addVertex(new Category("Main")));
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
