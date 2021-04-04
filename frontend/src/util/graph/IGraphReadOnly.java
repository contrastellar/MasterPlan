package util.graph;

import java.util.List;

public interface IGraphReadOnly<T> {

    List<? extends IVertexAdjList<T>> query(IQuery<T> queryFunc);

    Iterable<? extends IVertexAdjList<T>> getVertices();

}
