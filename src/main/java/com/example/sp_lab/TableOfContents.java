package com.example.sp_lab;

import java.util.ArrayList;
import java.util.List;

public class TableOfContents implements Element {
    private final List<String> entries = new ArrayList<>();

    public void addEntry(String title) {
        entries.add(title);
    }

    @Override
    public void print() {
        System.out.println("Table of Contents");
        for (String entry : entries)
            System.out.println(entry);
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

    @Override
    public void accept(Visitor v) {
    v.visit(this);
    }
}