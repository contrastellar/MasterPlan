package util.graph;

import java.util.Comparator;

public interface IGraphWriteOnly<T> {

    IVertexAdjList<T> addVertex(T t);

    void removeVertex(IVertexAdjList<T> v);

    // sorts IGraphReadOnly.getVertices()
    void sort(Comparator<T> c);

}
