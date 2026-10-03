package com.example.sp_lab.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private int noOfPages;
    private List<Author> authors;
    private List<Element> elements;


    void initializeLists() {
        this.authors = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    public Book(String title) {
        this.title = title;
        initializeLists();
    }

    public Book(String title, int noOfPages) {
        this.title = title;
        this.noOfPages = noOfPages;
        initializeLists();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public void addAuthor(Author a) {
        this.authors.add(a);
    }

    public void addContent(Element e) {
        this.elements.add(e);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void print() {
        System.out.println("Book: " + this.title + ", authors: " + authors.toString());


        for (Element e : elements)
            e.print();
    }
}
