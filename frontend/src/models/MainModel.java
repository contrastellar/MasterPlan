package models;

import components.Category;
import components.Tag;
import components.TodoElement;
import observable.IReadOnlyObservableList;
import observable.Observable;
import observable.ObservableList;
import observable.ObservableSet;
import util.graph.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class MainModel {

    public static final MainModel model = new MainModel();

    public final ObservableGraph<TodoElement> obsGraph;
    public final Observable<ObservableVertex<TodoElement>> selectedVertex = new Observable<>();
    public final Observable<ObservableVertex<TodoElement>> editVertex = new Observable<>();

    public final ObservableSet<Tag> tags = new ObservableSet<>(new HashSet<>());

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

    public void importGoogleCalendar(IVertex<TodoElement> rootVertex) {

    }

    public void exportGoogleCalendar() {
        throw new UnsupportedOperationException("not implemented yet");
    }




}
