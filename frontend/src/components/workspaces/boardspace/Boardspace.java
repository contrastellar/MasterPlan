package components.workspaces.boardspace;

import java.util.ArrayList;

import components.observable.ObservableList;
import components.Category;
import components.TodoElement;
import components.task.Task;
import util.graph.IVertex;

public final class Boardspace {

    public final ObservableList<Board> boards = new ObservableList<>(new ArrayList<>());

    public Boardspace(IVertex<Category> vertex) {

        // TODO: ?
    }


}
