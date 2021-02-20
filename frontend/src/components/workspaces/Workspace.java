package components.workspaces;

import MVVM.Observable;

public abstract class Workspace {
    public final Observable<String> name = new Observable<>("");

}
