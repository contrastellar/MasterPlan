package components.workspaces.boardspace;

import components.util.observable.Observable;
import components.workspaces.listspace.List;

public class Board {
    public static final double X_DEFAULT = 0.0,
                               Y_DEFAULT = 0.0,
                               W_DEFAULT = 0.0,
                               H_DEFAULT = 0.0;

    public final List board;
    public final Observable<Double> xPos; // where the left-side of board sits on x-axis
    public final Observable<Double> yPos; // where the right-side of board sits on y-axis
    public final Observable<Double> width; // should depend on number of tasks in the board
    public final Observable<Double> height; // should depend on number of tasks in the board

    // TODO: Check with frontend to see how xPos, yPos, width, and height should be set
    public Board() {
        board = new List();
        xPos = new Observable<>(X_DEFAULT);
        yPos = new Observable<>(Y_DEFAULT);
        width = new Observable<>(W_DEFAULT);
        height = new Observable<>(H_DEFAULT);
    }


}
