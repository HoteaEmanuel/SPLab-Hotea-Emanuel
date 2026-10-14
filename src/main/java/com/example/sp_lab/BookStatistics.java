package com.example.sp_lab;

import java.util.HashMap;
import java.util.Map;

public class BookStatistics implements Visitor {
    private final Map<Class<?>, Integer> counts = new HashMap<>();

    void printStatistics() {
        System.out.println("Book Statistics:");
        System.out.println("*** Number of images: " + (countOf(ImageProxy.class) + countOf(Image.class)));
        System.out.println("*** Number of tables: " + countOf(Table.class));
        System.out.println("*** Number of paragraphs: " + countOf(Paragraph.class));
    }

    private int countOf(Class<?> type) {
        return counts.getOrDefault(type, 0);
    }

    private void count(Element element) {
        counts.merge(element.getClass(), 1, Integer::sum);
    }

    @Override
    public void visit(Book book) {
    }

    @Override
    public void visit(Section section) {
        count(section);
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
        count(tableOfContents);
    }

    @Override
    public void visit(Paragraph paragraph) {
        count(paragraph);
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        count(imageProxy);
    }

    @Override
    public void visit(Image image) {
        count(image);
    }

    @Override
    public void visit(Table table) {
        count(table);
    }
}