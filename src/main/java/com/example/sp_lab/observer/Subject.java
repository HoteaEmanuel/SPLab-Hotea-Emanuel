package com.example.sp_lab.observer;

// Subject participant: keeps a collection of observers and notifies them
public interface Subject<T> {
    void attach(Observer<T> observer);

    void detach(Observer<T> observer);

    void notifyObservers(T value);
}
