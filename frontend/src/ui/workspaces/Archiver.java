package ui.workspaces;

import components.task.Task;
import util.graph.ObservableVertex;

/**
 * This is an object used to store
 * 1) Task information
 * 2) The vertex object in ListView
 * 3) The vertex object in the Archive
 */
public class Archiver {
    //Instance Variable Init
    ObservableVertex source;
    Task taskInfo;
    ObservableVertex destination;

    public Archiver(ObservableVertex src, Task task){
        source = src;
        taskInfo = task;
        destination = null;
    }

    public ObservableVertex getSource() {
        return source;
    }

    public void setSource(ObservableVertex source) {
        this.source = source;
    }

    public Task getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(Task taskInfo) {
        this.taskInfo = taskInfo;
    }

    public ObservableVertex getDestination() {
        return destination;
    }

    public void setDestination(ObservableVertex destination) {
        this.destination = destination;
    }
}
