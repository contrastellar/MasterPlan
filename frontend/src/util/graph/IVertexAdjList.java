package util.graph;

import java.util.Comparator;
import java.util.List;

public interface IVertexAdjList<T> {

    T getElement();

    void addDirectedEdge(IVertexAdjList<T> vertex);

    void removeDirectedEdge(IVertexAdjList<T> vertex);

    void sort(Comparator<T> c);

    void sortReachable(Comparator<T> c);

    List<? extends IVertexAdjList<T>> query(IQuery<T> queryFunc);

    List<? extends IVertexAdjList<T>> queryReachable(IQuery<T> queryFunc);

    Iterable<? extends IVertexAdjList<T>> getOutVertices();

    Iterable<? extends IVertexAdjList<T>> getInVertices();

    boolean adjListContains(IVertexAdjList<T> v);

    IGraph<T> getGraph();
}
