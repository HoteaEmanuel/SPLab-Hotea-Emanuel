package com.example.sp_lab.patterns.composite;

public class TableOfContents implements Element {
    @Override
    public void print() {
        System.out.println("Table of Contents");
    }

    @Override
    public void addElement(Element e) {
        throw new UnsupportedOperationException("TableOfContents cannot contain children");
    }

    @Override
    public Element getElement(int i) {
        throw new UnsupportedOperationException("TableOfContents cannot contain children");
    }

    @Override
    public void removeElement(Element e) {
        throw new UnsupportedOperationException("TableOfContents cannot contain children");
    }
}