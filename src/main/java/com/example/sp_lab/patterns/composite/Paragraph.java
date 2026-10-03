package com.example.sp_lab.patterns.composite;

public class Paragraph implements Element {
    private String text;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        System.out.println("Paragraph: " + this.text);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
