package com.example.sp_lab.patterns.composite;

public interface Element {
    void print();

    void addElement(Element e);

    Element getElement(int i);

    void removeElement(Element e);
}
