package ui.custom;

import javafx.scene.Node;

public interface Viewable {

    // returns this node of the controller
    Node node();

    // registers all listeners
    void registerListners();

    // unregisters all listeners
    void unregisterListners();

}
