package com.example.sp_lab.patterns.proxy;

public class ImageProxy implements Element {
    private final String url;
    private Image realImg;

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

    public String getUrl() {
        return url;
    }
}
