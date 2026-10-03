package com.example.sp_lab.patterns.composite;

import java.awt.*;
import java.util.ArrayList;

public class Section implements Element {
    private ArrayList<Element> elements;
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

    public void addElement(Element e) {
        elements.add(e);
    }

    @Override
    public Element getElement(int i) {
        if (i < 0 || i >= elements.size()) return null;

        return elements.get(i);
    }

    @Override
    public void removeElement(Element e) {

    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public String getTitle() {
        return this.title;
    }
}
