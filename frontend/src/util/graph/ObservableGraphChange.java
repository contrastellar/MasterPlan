package util.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ObservableGraphChange<T> {

    protected boolean sorted = false;
    protected Comparator<T> sortingComparator = null;
    protected List<IVertex<T>> addedVertices = null;
    protected List<IVertex<T>> removedVertices = null;


    public ObservableGraphChange() { }


    public boolean getSorted() {
        return sorted;
    }

    public Comparator<T> getSortingComparator() {
        return sortingComparator;
    }

    public Iterable<? extends IVertex<T>> getAddedVertices() {
        if(addedVertices == null)
            addedVertices = new ArrayList<>();
        return addedVertices;
    }

    public int addedVerticesSize() {
        return addedVertices == null ? 0 : addedVertices.size();
    }

    public Iterable<? extends IVertex<T>> getRemovedVertices() {
        if(removedVertices == null)
            removedVertices = new ArrayList<>();
        return removedVertices;
    }

    public int removedVerticesSize() {
        return removedVertices == null ? 0 : removedVertices.size();
    }

}