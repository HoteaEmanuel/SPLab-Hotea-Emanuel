package com.example.sp_lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

import java.util.ArrayList;
import java.util.List;

// Hibernate proxies of Book carry extra internal properties that must not end up in the JSON
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private int noOfPages;


    @ManyToMany
    private List<Author> authors;
    // Composition: elements are saved/deleted together with the book; OrderColumn keeps their order
    @OneToMany(targetEntity = BaseElement.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Element> elements;


    void initializeLists() {
        this.authors = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    // Required by JPA
    protected Book() {
        initializeLists();
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

    public Integer getId() {
        return id;
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

    public ImageProxy addImage(String url) {
        return addElement(new ImageProxy(url));
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

    public List<Element> getElements() {
        return elements;
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

    public void accept(Visitor v) {
        v.visit(this);
        for (Element e : elements)
            e.accept(v);
    }


}
