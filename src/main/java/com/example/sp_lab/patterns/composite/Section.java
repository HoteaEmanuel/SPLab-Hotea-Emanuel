package com.example.sp_lab.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
    private List<Element> elements;
    private String title;

    public Section(String title) {
        elements = new ArrayList<>();
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Section: " + this.title);
        for (Element element : elements)
            element.print();

    }

    @Override
    public void addElement(Element e) {
        elements.add(e);
    }

    @Override
    public Element getElement(int i) {
        if (i < 0 || i >= elements.size()) throw new IndexOutOfBoundsException();

        return elements.get(i);
    }

    @Override
    public void removeElement(Element e) {
        elements.remove(e);
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public String getTitle() {
        return this.title;
    }
}
