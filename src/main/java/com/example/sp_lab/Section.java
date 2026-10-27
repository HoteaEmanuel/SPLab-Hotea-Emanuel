package com.example.sp_lab;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Section extends BaseElement {
    // Composition: children are saved/deleted together with the section; OrderColumn keeps their order
    @OneToMany(targetEntity = BaseElement.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Element> elements;
    private String title;

    // Required by JPA
    protected Section() {
        elements = new ArrayList<>();
    }

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

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(Element element : elements)
            element.accept(v);
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
