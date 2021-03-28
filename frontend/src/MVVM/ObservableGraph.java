package MVVM;

import components.TodoElement;
import util.graph.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

// TODO
public class ObservableGraph<T> implements IObservable, IGraphReadOnly<T>, IGraphWriteOnly<T> {


    @Override
    public void addListener(IListener listener) {

    }

    @Override
    public void removeListener(IListener listener) {

    }

    @Override
    public List<? extends IVertex<T>> query(IQuery<T> queryFunc) {
        return null;
    }

    @Override
    public List<? extends IVertex<T>> query(IQuery<T> queryFunc, IVertex<T> v) {
        return null;
    }

    @Override
    public List<? extends IVertex<T>> queryRecursive(IQuery<T> queryFunc, IVertex<T> v) {
        return null;
    }

    @Override
    public Iterable<? extends IVertex<T>> getVertices() {
        return null;
    }

    @Override
    public IVertex<T> addVertex(T t) {
        return null;
    }

    @Override
    public void removeVertex(IVertex<T> v) {

    }

    @Override
    public void addDirectedEdge(IVertex<T> v1, IVertex<T> v2) {

    }

    @Override
    public void removeDirectedEdge(IVertex<T> v1, IVertex<T> v2) {

    }

    @Override
    public void sort(Comparator<T> c) {

    }

    @Override
    public void sort(Comparator<T> c, IVertex<T> v) {

    }

    @Override
    public void sortRecursive(Comparator<T> c, IVertex<T> v) {

    }
}
