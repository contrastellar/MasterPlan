package com.company.MVVM;

public interface IReadOnly<T> extends IObservable
{
    public T getValue();
}
