package components.workspaces;

import java.util.ArrayList;
import java.util.HashMap;

import components.Task;


public class WorkspaceData {
    // does not allow duplicates
    protected final ArrayList<HashMap<Task, WorkspaceData>> data;

    // nullable (we should get the google library to add the @Nullable annotation)
    protected final String name;

    public WorkspaceData(ArrayList<HashMap<Task, WorkspaceData>> data, String name)
    {
        // TODO: verify input
        // if( not valid ) throw new IllegalArgumentException();

        this.data = data;
        this.name = name;
    }
}
