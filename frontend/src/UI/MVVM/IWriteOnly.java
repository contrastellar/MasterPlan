package com.company.MVVM;

public interface IWriteOnly<T> extends IObservable
{
    public void setValue(T newValue);
}
