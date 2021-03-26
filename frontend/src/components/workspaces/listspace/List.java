package components.workspaces.listspace;

import components.util.observable.Observable;
import components.util.observable.ObservableList;
import components.Task.Task;

import java.util.ArrayList;

public class List {

    public final Observable<String> name = new Observable<>("");

    public final ObservableList<Task> tasks = new ObservableList<>( new ArrayList<>() );

    public List() {

    }
}
