package ui.taskboard;

import components.Category;
import components.TodoElement;
import components.observable.ObservableManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import models.MainModel;
import ui.custom.Viewable;
import ui.taskboard.listview.ListSpaceView;
import util.graph.ObservableVertex;

import java.io.IOException;

/**
 *
 */
public class WorkSpaceView extends SplitPane implements Viewable {

    public final ListSpaceView listSpaceView;

    private final MainModel mainModel;

    private final ObservableManager observableManager = new ObservableManager();



    public WorkSpaceView(MainModel mainModel) {
        this.mainModel = mainModel;

        loadFXML();

        listSpaceView = new ListSpaceView(mainModel);

        getItems().add(0, listSpaceView);
    }


    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WorkSpaceView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);



        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Node node() {
        return this;
    }

    @Override
    public void registerListeners() {
        listSpaceView.registerListners();
        observableManager.startListen();
    }

    @Override
    public void unregisterListeners() {
        listSpaceView.unregisterListners();
        observableManager.startListen();
    }
}
