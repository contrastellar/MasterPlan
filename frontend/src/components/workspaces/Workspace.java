package components.workspaces;

import components.Archival;

public abstract class Workspace implements Archival {

    public final WorkspaceData data;

    public Workspace(WorkspaceData data) {
        if(data == null)
            throw new IllegalArgumentException("WorkspaceData cannot be null");

        this.data = data;
    }

}
