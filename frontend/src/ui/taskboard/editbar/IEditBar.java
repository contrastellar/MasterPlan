package ui.taskboard.editbar;

import components.TodoElement;
import util.graph.ObservableVertex;
import ui.custom.Viewable;

public interface IEditBar extends Viewable {

    void setEditVertex(ObservableVertex<TodoElement> editVertex);

}
