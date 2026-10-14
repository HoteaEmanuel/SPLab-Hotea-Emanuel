package com.example.sp_lab;

public interface Element {
    void print();

    void addElement(Element e);

    Element getElement(int i);

    void removeElement(Element e);

    void accept(Visitor v);
}
