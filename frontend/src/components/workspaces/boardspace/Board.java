package components.workspaces.boardspace;

import components.Category;
import components.TodoElement;
import components.observable.IObservable;
import util.graph.ObservableGraph;
import util.graph.IVertex;
import util.graph.ObservableVertex;
import util.vector.ObservableVec2D;
import util.vector.Vec2D;

public class Board {
    // TODO: Change defaults
    public static final double X_DEFAULT = 0.0,
                               Y_DEFAULT = 0.0,
                               W_DEFAULT = 0.0,
                               H_DEFAULT = 0.0;

    public final ObservableVec2D pos = new ObservableVec2D(new Vec2D(X_DEFAULT, Y_DEFAULT));;
    public final ObservableVec2D dim = new ObservableVec2D(new Vec2D(W_DEFAULT, H_DEFAULT));;

    private final ObservableVertex<TodoElement> rootVertex;

    // TODO: Check with frontend to see how xPos, yPos, width, and height should be set
    protected Board(ObservableVertex<TodoElement> rootVertex) { // the
        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("Board() - rootVertex.getElement() must be of type Category");

        this.rootVertex = rootVertex;
    }

    public Board(ObservableVertex<TodoElement> rootVertex, double x, double y, double w, double h) {
        this.rootVertex = rootVertex;

        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("Board() - rootVertex.getElement() must be of type Category");

        pos.set(x, y);
        dim.set(w, h);
    }


    public Board(ObservableVertex<TodoElement> rootVertex, Vec2D pos, Vec2D dim) {
        this.rootVertex = rootVertex;

        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("Board() - rootVertex.getElement() must be of type Category");

        this.pos.set(pos);
        this.dim.set(dim);
    }

    public ObservableVertex<TodoElement> getRootVertex() {
        return rootVertex;
    }
}
