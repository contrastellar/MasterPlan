package util.graph;

import java.util.Comparator;
import java.util.List;

public class ObservableVertexChange<T> {

    protected boolean sorted = false;
    protected Comparator<T> sortingComparator = null;
    protected List<ObservableVertex<T>> addedEdges = null;
    protected List<ObservableVertex<T>> removedEdges = null;

    public ObservableVertexChange() { }

    public boolean getSorted() { return sorted; }
    public Comparator<T> getSortingComparator() { return sortingComparator; }

    public Iterable<ObservableVertex<T>> getAddedEdges() { return addedEdges; }
    public int addedEdgesSize() { return addedEdges.size(); }

    public Iterable<ObservableVertex<T>> getRemovedEdges() { return removedEdges; }
    public int removedEdgesSize() { return removedEdges.size(); }

}
