package com.example.sp_lab;

import jakarta.persistence.Entity;

import java.util.concurrent.TimeUnit;

@Entity
public class Image extends BaseElement {
    private String url;

    // Required by JPA; skips the simulated loading delay
    protected Image() {
    }

    public Image(String url) {
        this.url = url;
        // simulates the expensive loading of the image
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void print() {
        System.out.println("Image: " + this.url);
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

    public void setUrl(String url) {
        this.url = url;
    }
}
