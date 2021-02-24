package components.workspaces;

import java.util.ArrayList;
import java.util.HashMap;

import components.Task.Task;


/**
 *
 */
public class WorkspaceData {

    /**
     *
     */
    protected final ArrayList<HashMap<Task, WorkspaceData>> data;

    /**
     *
     */
    protected final String name;


    public WorkspaceData(ArrayList<HashMap<Task, WorkspaceData>> data, String name)
    {
        // TODO: verify input
        // if( not valid ) throw new IllegalArgumentException();

        this.data = data;
        this.name = name;
    }
}
