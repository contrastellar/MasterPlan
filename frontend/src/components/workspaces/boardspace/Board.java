package components.workspaces.boardspace;

import MVVM.Observable;
import MVVM.ObservableList;
import components.workspaces.listspace.List;

import java.util.ArrayList;

public class Board {

    /**
     *
     */
    public final ObservableList<List> lists = new ObservableList<>(new ArrayList<>());

    /**
     *
     */
    public final Observable<Double> x = new Observable<>();

    /**
     *
     */
    public final Observable<Double> y = new Observable<>();

    public Board() {

    }

}
