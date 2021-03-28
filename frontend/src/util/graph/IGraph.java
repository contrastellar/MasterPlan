package util.graph;

import java.util.Comparator;
import java.util.List;

public interface IGraph<T> {

    IVertex<T> addVertex(T t);

    void removeVertex(IVertex<T> v);

    void addDirectedEdge(IVertex<T> v1, IVertex<T> v2);

    void removeDirectedEdge(IVertex<T> v1, IVertex<T> v2);

    void sort(Comparator<T> c);

    void sort(Comparator<T> c, IVertex<T> v);

    void sortRecursive(Comparator<T> c, IVertex<T> v);

    List<? extends IVertex<T>> query(IQuery<T> queryFunc);

    List<? extends IVertex<T>> query(IQuery<T> queryFunc, IVertex<T> v);

    List<? extends IVertex<T>> queryRecursive(IQuery<T> queryFunc, IVertex<T> v);

    Iterable<? extends IVertex<T>> getVertices();

}
