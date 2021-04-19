package ui.custom.controls.tag;

import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import util.graph.ObservableVertex;

public class TagDisplayView {

    private final Observable<ObservableVertex<TodoElement>> _vertex = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> vertex = _vertex;


}
