package com.example.sp_lab;

import java.util.LinkedHashMap;
import java.util.Map;

public class TableOfContents implements Element {
    private final Map<Section, Integer> entries = new LinkedHashMap<>();

    public void addEntry(Section section, int page) {
        entries.put(section, page);
    }

    @Override
    public void print() {
        System.out.println("Table of Contents");
        entries.forEach((section, page) -> System.out.println(section.getTitle() + " - page " + page));
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