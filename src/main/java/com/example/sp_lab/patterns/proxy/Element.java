package com.example.sp_lab.patterns.proxy;

public interface Element {
    void print();

    void addElement(Element e);

    Element getElement(int i);

    void removeElement(Element e);
}
