package components.workspaces;

import MVVM.Observable;
import components.Archival;

public abstract class Workspace implements Archival {

    /**
     * User identifier for the workspace
     */
    public final Observable<String> name = new Observable<>("");

    /**
     * The archived state of the workspace.
     */
    public final Observable<Boolean> isArchived = new Observable<>(false);

    public Workspace() { }

    /**
     * Converts workspace to a WorkspaceData type
     * @return
     */
    public abstract WorkspaceData convertToWorkspaceData();

    public boolean isArchived() { return isArchived.getValue(); }
    public void setArchive(boolean isArchived) { this.isArchived.setValue(isArchived); }

}
