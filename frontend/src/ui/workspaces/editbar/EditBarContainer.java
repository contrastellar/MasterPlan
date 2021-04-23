package ui.workspaces.editbar;

import components.Category;
import components.TodoElement;
import observable.ObservableManager;
import components.task.Task;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import models.MainModel;
import ui.util.Viewable;
import util.graph.ObservableVertex;

public class EditBarContainer extends VBox implements Viewable {

    private final ObservableManager observableManager = new ObservableManager();

    private IEditBar activeEditBar = null;

    public EditBarContainer() {
        observableManager.addListener(MainModel.model.editVertex, this::onEditVertexChange);

        this.setMinWidth(200);
        this.setMaxWidth(250);
    }

    private void onEditVertexChange(ObservableVertex<TodoElement> editVertex) {

        System.out.println("EditVertex change");

        if(activeEditBar != null)
            activeEditBar.unregisterListeners();

        getChildren().clear();

        if(editVertex == null) {
            return;
        }else if(editVertex.getElement() instanceof Task) {
            activeEditBar = new TaskEditBar();
        }
        else if(editVertex.getElement() instanceof Category) {
            activeEditBar = new CategoryEditBar();
        }
        else {
            throw new IllegalArgumentException("editVertex.getElement() must either be of type Task or Category");
        }

        activeEditBar.setEditVertex(MainModel.model.editVertex.getValue());
        activeEditBar.registerListeners();
        getChildren().add(activeEditBar.node());
    }

    public Node node() {
        return this;
    }

    public void registerListeners() {
        observableManager.startListen();
        if(activeEditBar != null)
            activeEditBar.registerListeners();
    }

    public void unregisterListeners() {
        observableManager.stopListen();
        if(activeEditBar != null)
            activeEditBar.unregisterListeners();
    }




}
