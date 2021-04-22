package ui.workspaces.editbar;

import components.TodoElement;
import util.graph.ObservableVertex;
import ui.util.Viewable;

public interface IEditBar extends Viewable {

    void setEditVertex(ObservableVertex<TodoElement> editVertex);

}
