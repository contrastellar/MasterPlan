package util.graph;

import util.collections.IReadOnlyList;
import util.collections.ReadOnlyList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ObservableGraphChange<T> {

    protected boolean sorted = false;
    protected Comparator<T> sortingComparator = null;
    protected List<ObservableVertex<T>> addedVertices = null;
    protected List<ObservableVertex<T>> removedVertices = null;



    public ObservableGraphChange() { }


    public boolean getSorted() {
        return sorted;
    }

    public Comparator<T> getSortingComparator() {
        return sortingComparator;
    }

    public IReadOnlyList<? extends IVertex<T>> getAddedVertices() {
        if (addedVertices == null)
            addedVertices = new ArrayList<>();
        return new ReadOnlyList<>(addedVertices);
    }

    public int addedVerticesSize() {
        return addedVertices == null ? 0 : addedVertices.size();
    }

    public IReadOnlyList<? extends IVertex<T>> getRemovedVertices() {
        if(removedVertices == null)
            removedVertices = new ArrayList<>();
        return new ReadOnlyList<>(removedVertices);
    }

    public int removedVerticesSize() {
        return removedVertices == null ? 0 : removedVertices.size();
    }

}