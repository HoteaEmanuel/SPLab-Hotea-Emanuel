package com.example.sp_lab.patterns.strategy;

public class Paragraph implements Element {
    private String text;
    private AlignStrategy alignStrategy;
    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        if (alignStrategy == null) {
            System.out.println("Paragraph: " + this.text);
        } else {
            alignStrategy.render(this);
        }
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
