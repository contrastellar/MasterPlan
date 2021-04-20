package ui.custom;

import javafx.scene.Node;

public interface Viewable {

    // returns this node of the controller
    Node node();

    // registers all listeners
    void registerListeners();

    // unregisters all listeners
    void unregisterListeners();

}
