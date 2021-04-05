package ui.taskboard.listview;

import components.TodoElement;
import util.graph.ObservableGraph;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

public class TodoElementView<T extends TodoElement> {

    private final ListViewContainer subContainer;

    private final ObservableGraph<? extends TodoElement> obsGraph;
    private final ObservableVertex<T> rootVertex;

    /**
     * Constructs Category component with loader
     */
    public TodoElementView(ObservableVertex<T> rootVertex) {
        this.rootVertex = rootVertex;
        this.obsGraph = rootVertex.getGraph();
        subContainer = new ListViewContainer();

        rootVertex.startListen(this::updateCategoryContainer);
    }
    // pass in observable cateogry vertex. then register a listener called update category, that listens to
    // the vertex. when the vertex updates, we need to update our containers.

    private void updateCategoryContainer(ObservableVertexChange<? extends TodoElement> change) {

        for(var vertex : change.getAddedEdges())
            subContainer.addVertex(vertex);

        for(var vertex : change.getRemovedEdges())
            subContainer.removeVertex(vertex);

        if(change.getSorted())
            subContainer.sort(change.getSortingComparator()); // currently throws exception

    }

}
