package models;

import components.Category;
import components.Tag;
import components.TodoElement;
import observable.IReadOnlyObservableList;
import observable.Observable;
import observable.ObservableList;
import ui.workspaces.Archiver;
import util.graph.*;

import java.util.ArrayList;
import java.util.Comparator;

public class MainModel {

    public static final MainModel model = new MainModel();

    public final ObservableGraph<TodoElement> obsGraph;
    public final Observable<ObservableVertex<TodoElement>> selectedVertex = new Observable<>();
    public final Observable<ObservableVertex<TodoElement>> editVertex = new Observable<>();

    public final ObservableList<Tag> _tags = new ObservableList<>(new ArrayList<>());
    public final IReadOnlyObservableList<Tag> tags = _tags;
    public ArrayList<Archiver> archiverList = new ArrayList<>();

    private MainModel() {
        // deserialize graph
        this.obsGraph = new ObservableGraph<>(new Graph<>());
        selectedVertex.setValue(obsGraph.addVertex(new Category("Main")));

        obsGraph.startListen(this::onEditVertexRemoved);
        obsGraph.startListen(this::onSelectedVertexRemoved);
    }

    private void onEditVertexRemoved(ObservableGraphChange<TodoElement> change) {
        if(change.getRemovedVertices().contains(editVertex.getValue()))
            editVertex.setValue(null);
    }

    private void onSelectedVertexRemoved(ObservableGraphChange<TodoElement> change) {
        if(change.getRemovedVertices().contains(selectedVertex.getValue()))
            selectedVertex.setValue(null);
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

    public void importGoogleCalendar(IVertex<TodoElement> rootVertex) {

    }

    public void exportGoogleCalendar() {
        throw new UnsupportedOperationException("not implemented yet");
    }




}
