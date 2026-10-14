package com.example.sp_lab.patterns.strategy;

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

    public Section addSection(String title) {
        return addElement(new Section(title));
    }

    public Paragraph addParagraph(String text) {
        return addElement(new Paragraph(text));
    }

    public Image addImage(String url) {
        return addElement(new Image(url));
    }

    public Table addTable() {
        return addElement(new Table());
    }

    public TableOfContents addTableOfContents() {
        return addElement(new TableOfContents());
    }

    private <T extends Element> T addElement(T element) {
        this.elements.add(element);
        return element;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void print() {
        System.out.println("Book: " + this.title);

        for (Author a : authors)
            a.print();

        for (Element e : elements)
            e.print();
    }
}
