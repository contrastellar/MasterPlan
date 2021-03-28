package util.graph;

import java.util.Comparator;
import java.util.List;

public interface IVertex<T> {

    T getElement();

    void sort(Comparator<T> c);

    void sortRecursive(Comparator<T> c);

    List<? extends IVertex<T>> query(IQuery<T> queryFunc);

    List<? extends IVertex<T>> queryRecursive(IQuery<T> queryFunc);

    Iterable<? extends IVertex<T>> getOutVertices();

    Iterable<? extends IVertex<T>> getInVertices();

    IGraph<T> getGraph();
}
