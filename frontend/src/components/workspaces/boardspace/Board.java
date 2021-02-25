package components.workspaces.boardspace;

import MVVM.Observable;
import MVVM.ObservableList;
import components.workspaces.listspace.List;

import java.util.ArrayList;

public class Board {
    public final List board;
    public final Observable<Double> xPos; // where the left-side of board sits on x-axis
    public final Observable<Double> yPos; // where the right-side of board sits on y-axis
    public final Observable<Double> width; // should depend on number of tasks in the board
    public final Observable<Double> height; // should depend on number of tasks in the board

    // TODO: Check with frontend to see how xPos, yPos, width, and height should be set
    public Board() {
        board = new List();
        xPos = new Observable<>((double) 0);
        yPos = new Observable<>((double) 0);
        width = new Observable<>((double) 300);
        height = new Observable<>((double) 300);
    }


}
