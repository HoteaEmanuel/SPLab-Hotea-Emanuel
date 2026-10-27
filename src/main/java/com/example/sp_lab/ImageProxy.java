package com.example.sp_lab;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class ImageProxy extends BaseElement {
    private String url;
    // Loaded lazily at runtime, only the url is persisted
    @Transient
    private Image realImg;

    // Required by JPA
    protected ImageProxy() {
    }

    public ImageProxy(String url) {
        this.url = url;
    }

    private Image loadImage() {
        if (realImg == null) {
            realImg = new Image(url);
        }
        return realImg;
    }

    @Override
    public void print() {
        loadImage().print();
    }

    @Override
    public void addElement(Element e) {
        throw new UnsupportedOperationException("Image cannot contain children");
    }

    @Override
    public Element getElement(int i) {
        throw new UnsupportedOperationException("Image cannot contain children");
    }

    @Override
    public void removeElement(Element e) {
        throw new UnsupportedOperationException("Image cannot contain children");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String getUrl() {
        return url;
    }
}
