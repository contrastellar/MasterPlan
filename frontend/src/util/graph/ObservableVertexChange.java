package util.graph;

import java.util.ArrayList;
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

    public Iterable<ObservableVertex<T>> getAddedEdges() {
        if(addedEdges == null)
            addedEdges = new ArrayList<>();
        return addedEdges;
    }

    public int addedEdgesSize() { return addedEdges == null ? 0 : addedEdges.size(); }

    public Iterable<ObservableVertex<T>> getRemovedEdges() {
        if(removedEdges == null)
            removedEdges = new ArrayList<>();
        return removedEdges;
    }

    public int removedEdgesSize() { return removedEdges == null ? 0 : removedEdges.size(); }

}
