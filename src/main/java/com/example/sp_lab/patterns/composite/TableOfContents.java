package com.example.sp_lab.patterns.composite;

public class TableOfContents implements Element {
    @Override
    public void print() {
        System.out.println("Table of Contents");
    }

    @Override
    public void addElement(Element e) {

    }

    @Override
    public Element getElement(int i) {
        return null;
    }

    @Override
    public void removeElement(Element e) {

    }
}
