package com.example.sp_lab;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Paragraph extends BaseElement {
    private String text;
    // Behaviour, not data: not persisted
    @Transient
    private AlignStrategy alignStrategy;

    // Required by JPA
    protected Paragraph() {
    }

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        System.out.println("Paragraph: " + this.text);
    }

    @Override
    public void addElement(Element e) {
        throw new UnsupportedOperationException("Paragraph cannot contain children");
    }

    @Override
    public Element getElement(int i) {
        throw new UnsupportedOperationException("Paragraph cannot contain children");
    }

    @Override
    public void removeElement(Element e) {
        throw new UnsupportedOperationException("Paragraph cannot contain children");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
