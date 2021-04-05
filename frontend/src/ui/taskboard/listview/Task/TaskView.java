package ui.taskboard.listview.Task;

public class TaskView extends GridPane{

    @FXML
    private final ListViewContainer subContainer;

    private ObservableGraph<? extends TodoElement> obsGraph;

    /**
     * Constructs Category component with loader
     */
    public TaskView(ObservableVertex<Task> rootVertex) {
        // Set fxml root and controller to this instance
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Attempt to load resource
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assume we are given ObservableVertex<Category> rootVertex;
        obsGraph = rootVertex.getGraph();
        rootVertex.startListen(this::updateCategoryContainer);
    }

    // pass in observable cateogry vertex. then register a listener called update category, that listens to
    // the vertex. when the vertex updates, we need to update our containers.

    @FXML
    private void initialize() {

    }

    private void updateCategoryContainer(ObservableVertexChange<? extends TodoElement> change) {

        for(var vertex : change.getAddedEdges()) {
            subContainer.addVertex(vertex);
        }

        for(var vertex : change.getRemovedEdges()) {
            subContainer.removeVertex(vertex);
        }

        if(change.getSorted()) {
            subContainer.sort(change.getSortingComparator());
        }

    }
}
