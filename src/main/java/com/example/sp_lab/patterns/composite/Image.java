package com.example.sp_lab.patterns.composite;

public class Image implements Element {
    private String url;

    @Override
    public void print() {
        System.out.println("Image: " + this.url);
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
