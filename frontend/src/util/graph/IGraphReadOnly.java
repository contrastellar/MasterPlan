package util.graph;

import java.util.Comparator;
import java.util.List;

public interface IGraphReadOnly<T> {

    List<? extends IVertex<T>> query(IQuery<T> queryFunc);

    List<? extends IVertex<T>> query(IQuery<T> queryFunc, IVertex<T> v);

    List<? extends IVertex<T>> queryRecursive(IQuery<T> queryFunc, IVertex<T> v);

    Iterable<? extends IVertex<T>> getVertices();

}
