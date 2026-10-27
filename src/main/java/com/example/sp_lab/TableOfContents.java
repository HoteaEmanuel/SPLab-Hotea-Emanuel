package com.example.sp_lab;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class TableOfContents extends BaseElement {
    // Derived data, rebuilt by the GenerateToC visitor: not persisted
    @Transient
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