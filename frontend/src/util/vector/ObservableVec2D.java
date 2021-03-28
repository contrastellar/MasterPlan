package util.vector;

import MVVM.IListener;
import MVVM.IObservable;

import java.util.HashSet;

public class ObservableVec2D implements IVec2D, IObservable {

    private final HashSet<IListener> listeners = new HashSet<>();
    private final IVec2D vec;

    public ObservableVec2D(IVec2D vec) {
        this.vec = vec;
    }

    @Override
    public void addListener(IListener listener) {
        listeners.add(listener);
        listener.onChange();
    }

    @Override
    public void removeListener(IListener listener) {
        listeners.remove(listener);
    }

    private void updateListeners() {
        for(IListener listener : listeners)
            listener.onChange();
    }

    @Override
    public double getX() {
        return vec.getX();
    }

    @Override
    public double getY() {
        return vec.getY();
    }

    @Override
    public void add(Vec2D vec) {
        this.vec.add(vec);
        updateListeners();
    }

    @Override
    public void add(double dX, double dY) {
        this.vec.add(dX, dY);
        updateListeners();
    }

    @Override
    public void sub(Vec2D vec) {
        this.vec.sub(vec);
        updateListeners();
    }

    @Override
    public void sub(double dX, double dY) {
        this.vec.sub(dX, dY);
        updateListeners();
    }

    @Override
    public void mul(double c) {
        this.vec.mul(c);
        updateListeners();
    }

    @Override
    public void div(double c) {
        this.vec.div(c);
        updateListeners();
    }

    @Override
    public double dot(Vec2D v) {
        return this.vec.dot(v);
    }

    @Override
    public double mag() {
        return this.vec.mag();
    }


}
