package components.workspaces.boardspace;

import java.util.ArrayList;

import MVVM.ObservableCollection;
import MVVM.ObservableList;
import components.Category;
import components.TodoElement;
import components.task.Task;
import components.workspaces.Workspace;
import components.workspaces.WorkspaceData;
import util.graph.Graph;

public final class Boardspace {

    public final ObservableList<Board> boards = new ObservableList<>(new ArrayList<>());

    public Boardspace(Graph graph) {

        // TODO: ?
    }

    public Graph.Vertex addVertex(TodoElement e){

        if(e instanceof Category) {
            // create new board
        }
        if(e instanceof Task) {
            // add e to dangling tasks
        }

        throw new UnsupportedOperationException("not implemented yet");
    }

    public void addDirectedEdge(Graph.Vertex v1, Graph.Vertex v2) {
        if(v1.getElement() instanceof Category) {
            // add
        }
    }

}
