package models;

import components.Category;
import components.Tag;
import components.TodoElement;
import components.task.Task;
import observable.Observable;
import observable.ObservableSet;
import util.graph.*;

import observable.Observable;
import observable.ObservableSet;
import util.graph.*;


import java.util.HashSet;

public class MainModel {

    public static final MainModel model = new MainModel();

    public final ObservableGraph<TodoElement> obsGraph;
    public final Observable<ObservableVertex<TodoElement>> selectedVertex = new Observable<>();
    public final Observable<ObservableVertex<TodoElement>> editVertex = new Observable<>();

    public final ObservableSet<Tag> tags = new ObservableSet<>(new HashSet<>());

    public final JSONWriteRead json = new JSONWriteRead();

    private MainModel() {
        // deserialize graph
        this.obsGraph = new ObservableGraph<>(new Graph<>());
        selectedVertex.setValue(obsGraph.addVertex(new Category("Main")));

        obsGraph.startListen(this::onEditVertexRemoved);
        obsGraph.startListen(this::onSelectedVertexRemoved);

        importGoogleCalendar(selectedVertex.getValue());
    }

    private void onEditVertexRemoved(ObservableGraphChange<TodoElement> change) {
        if(change.getRemovedVertices().contains(editVertex.getValue()))
            editVertex.setValue(null);
    }

    private void onSelectedVertexRemoved(ObservableGraphChange<TodoElement> change) {
        if(change.getRemovedVertices().contains(selectedVertex.getValue()))
            selectedVertex.setValue(null);
    }

    public void importGoogleCalendar(ObservableVertex<TodoElement> rootVertex) {
        try{
            json.JSONWrite();
            json.JSONRead();
        } catch(Exception e) {
            e.printStackTrace();
        }

        String arrayC[] = json.getArrayC();
        String arrayT[] = json.getArrayT();

        Category notCompleted = new Category("NOT COMPLETED");
        var notCompletedVertex = obsGraph.addVertex(notCompleted, rootVertex);

        Category classes = new Category("Class");
        var classesVertex = obsGraph.addVertex(classes, notCompletedVertex);

        for(int i = 0; i < arrayC.length; i++) {
            if(arrayC[i].equals("Class")) {
                Task task = new Task(arrayT[i]);
                obsGraph.addVertex(task, classesVertex);
            }
        }
        Category meetings = new Category("Meetings");
        var meetingsVertex = obsGraph.addVertex(meetings, notCompletedVertex);

        for(int i = 0; i < arrayC.length; i++) {
            if(arrayC[i].equals("Meeting")) {
                Task task = new Task(arrayT[i]);
                obsGraph.addVertex(task, meetingsVertex);
            }
        }
    }

}
