package components.workspaces;

import MVVM.Observable;
import components.Archivable;

public abstract class Workspace implements Archivable {

    public final Observable<String> name = new Observable<>("");
    public final Observable<Boolean> isArchived = new Observable<>(false);

    public Workspace() { }

    public boolean isArchived() { return isArchived.getValue(); }
    public void setArchive(boolean isArchived) { this.isArchived.setValue(isArchived); }

}
