package components.workspaces.boardspace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import components.Category;
import components.observable.ObservableList;
import components.TodoElement;
import components.observable.ObservableVertexAdjList;

public final class Boardspace {

    public final ObservableList<Board> boards = new ObservableList<>(new ArrayList<>());
    public Map<ObservableVertexAdjList<TodoElement>, Board> categoryToBoard = new HashMap<>();
    public final ObservableVertexAdjList<TodoElement> rootVertex;


    public Boardspace(ObservableVertexAdjList<TodoElement> rootVertex) { // The root of the boardspace
        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("BoardSpace() - rootVertex must have an element of type Category");
        this.rootVertex = rootVertex;
        rootVertex.addListener(this::updateBoards);
    }

    private void updateBoards() {

        // check for any adds
        for (var vertex : rootVertex.getOutVertices()) {

            if(!(vertex.getElement() instanceof Category))
                continue;

            if(!categoryToBoard.containsValue(vertex)) {
                Board newBoard = new Board(vertex);
                boards.add(newBoard);
                categoryToBoard.put(vertex, newBoard);
            }
            // continue jumps here
        }

        // check for any removals
        for(var board : boards) {
            if(!rootVertex.adjListContains(board.rootVertex)) {
                boards.remove(board);
                categoryToBoard.remove(rootVertex.getElement());
            }
        }
    }


}
