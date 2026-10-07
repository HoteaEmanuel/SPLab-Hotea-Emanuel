package com.example.sp_lab.patterns.proxy;

public class ProxyMain {

    public static void main(String[] args) {
        Book book = new Book("Crime and Punishment");
        Section cap1 = book.addSection("Capitolul 1");

        long start = System.nanoTime();
        cap1.addElement(new ImageProxy("a.png"));
        cap1.addElement(new ImageProxy("b.png"));
        System.out.println("Proxies created in " + millisSince(start) + " ms (no image loaded yet)");

        start = System.nanoTime();
        book.print();
        System.out.println("First print took " + millisSince(start) + " ms (images loaded)");

        start = System.nanoTime();
        book.print();
        System.out.println("Second print took " + millisSince(start) + " ms (images already loaded)");
    }

    private static long millisSince(long startNanos) {
        return (System.nanoTime() - startNanos) / 1_000_000;
    }
}
