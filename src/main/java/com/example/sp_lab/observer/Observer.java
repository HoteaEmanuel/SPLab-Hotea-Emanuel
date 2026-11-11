package com.example.sp_lab.observer;

// Observer participant: gets called by the subject whenever its state changes
public interface Observer<T> {
    void update(T value);
}
