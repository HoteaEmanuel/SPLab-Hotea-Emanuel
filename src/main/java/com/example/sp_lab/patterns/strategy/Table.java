package com.example.sp_lab.patterns.strategy;

public class Table implements Element {
    @Override
    public void print() {
        System.out.println("Table");
    }

    @Override
    public void addElement(Element e) {
        throw new UnsupportedOperationException("Table cannot contain children");
    }

    @Override
    public Element getElement(int i) {
        throw new UnsupportedOperationException("Table cannot contain children");
    }

    @Override
    public void removeElement(Element e) {
        throw new UnsupportedOperationException("Table cannot contain children");
    }
}