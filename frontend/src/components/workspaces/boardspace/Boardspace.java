package components.workspaces.boardspace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import components.Category;
import components.TodoElement;
import components.observable.ObservableList;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;
import util.vector.Vec2D;

public final class Boardspace {

    public final ObservableList<Board> boards = new ObservableList<>(new ArrayList<>());
    private final Map<ObservableVertex<TodoElement>, Board> vertexToBoard = new HashMap<>();

    private final ObservableVertex<TodoElement> rootVertex;

    public Boardspace(ObservableVertex<TodoElement> rootVertex) {
        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("BoardSpace() - rootVertex must have an element of type Category");

        this.rootVertex = rootVertex;

        rootVertex.startListen(this::updateBoards);
    }

    private void updateBoards(ObservableVertexChange<TodoElement> change) {

        for(ObservableVertex<TodoElement> v : change.getAddedEdges()) {
            Board board = new Board(v);
            boards.add(board);
            vertexToBoard.put(v, board);
        }

        for(ObservableVertex<TodoElement> v : change.getRemovedEdges()) {
            boards.remove(vertexToBoard.get(v));
        }

        if(change.getSorted()) {
            Comparator<Board> boardComparator =
                    (b1, b2) -> change.getSortingComparator().compare(b1.getRootVertex().getElement(), b2.getRootVertex().getElement());
        }

    }

    public void addBoard(Category category, Vec2D pos, Vec2D dim) {
        var vertex = rootVertex.getGraph().addVertex(category, rootVertex);
        Board board = new Board(vertex, pos, dim);
        boards.add(board);
        vertexToBoard.put(vertex, board);
    }

    public void addBoard(Category category, double x, double y, double w, double h) {
        var vertex = rootVertex.getGraph().addVertex(category, rootVertex);
        Board board = new Board(vertex, x, y, w, h);
        boards.add(board);
        vertexToBoard.put(vertex, board);
    }

    public void removeBoard(Board board) {
        boards.remove(board);
        vertexToBoard.remove(board.getRootVertex());
        rootVertex.getGraph().removeDirectedEdge(rootVertex, board.getRootVertex());
    }

    public ObservableVertex<TodoElement> getRootVertex() {
        return rootVertex;
    }

}
