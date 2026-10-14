package com.example.sp_lab;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    private String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return out.toString().replace("\r\n", "\n");
    }

    @Test
    void leavesRejectCompositeOperations() {
        List<Element> leaves = List.of(
                new Paragraph("text"),
                new ImageProxy("a.png"),
                new Table(),
                new TableOfContents());

        for (Element leaf : leaves) {
            String name = leaf.getClass().getSimpleName();
            assertThrows(UnsupportedOperationException.class, () -> leaf.addElement(new Paragraph("x")), name);
            assertThrows(UnsupportedOperationException.class, () -> leaf.getElement(0), name);
            assertThrows(UnsupportedOperationException.class, () -> leaf.removeElement(new Paragraph("x")), name);
        }
    }

    @Test
    void sectionRemovesChild() {
        Section section = new Section("s");
        Element p = new Paragraph("p");
        section.addElement(p);

        section.removeElement(p);

        assertThrows(IndexOutOfBoundsException.class, () -> section.getElement(0));
    }

    @Test
    void imagePrintsItsUrl() {
        assertEquals("Image: a.png\n", capture(() -> new ImageProxy("a.png").print()));
    }

    @Test
    void authorPrintsNameAndSurname() {
        assertEquals("Author: Ada Lovelace\n", capture(() -> new Author("Ada", "Lovelace").print()));
    }

    @Test
    void bookCreatesItsOwnElementsAndPrintsThemInOrder() {
        Book book = new Book("B");
        book.addAuthor(new Author("Ada", "Lovelace"));
        book.addTableOfContents();
        Section outer = book.addSection("Outer");
        outer.addElement(new Paragraph("first"));
        Section inner = new Section("Inner");
        outer.addElement(inner);
        inner.addElement(new ImageProxy("a.png"));
        outer.addElement(new Table());
        book.addParagraph("last");
        book.addImage("b.png");
        book.addTable();

        String expected = String.join("\n",
                "Book: B",
                "Author: Ada Lovelace",
                "Table of Contents",
                "Section: Outer",
                "Paragraph: first",
                "Section: Inner",
                "Image: a.png",
                "Table",
                "Paragraph: last",
                "Image: b.png",
                "Table") + "\n";
        assertEquals(expected, capture(book::print));
    }

    @Test
    void bookCreatorsReturnTheCreatedElement() {
        Book book = new Book("B");

        assertEquals("Intro", book.addSection("Intro").getTitle());
        assertEquals("text", book.addParagraph("text").getText());
        assertEquals("a.png", book.addImage("a.png").getUrl());
    }

    @Test
    void bookHoldsAuthorsByReference() {
        Author author = new Author("Ada", "Lovelace");
        Book first = new Book("First");
        Book second = new Book("Second");

        first.addAuthor(author);
        second.addAuthor(author);

        assertSame(author, first.getAuthors().get(0));
        assertSame(author, second.getAuthors().get(0));
    }

    @Test
    void bookStatisticsCountsEachElementTypeIncludingNestedOnes() {
        Book book = new Book("B");
        book.addTableOfContents();
        book.addParagraph("p1");
        book.addImage("a.png");
        Section outer = book.addSection("Outer");
        outer.addElement(new Paragraph("p2"));
        outer.addElement(new Paragraph("p3"));
        Section inner = new Section("Inner");
        outer.addElement(inner);
        inner.addElement(new Paragraph("p4"));
        inner.addElement(new ImageProxy("b.png"));
        inner.addElement(new Table());
        book.addParagraph("p5");

        BookStatistics statistics = new BookStatistics();
        book.accept(statistics);

        String expected = String.join("\n",
                "Book Statistics:",
                "*** Number of images: 2",
                "*** Number of tables: 1",
                "*** Number of paragraphs: 5") + "\n";
        assertEquals(expected, capture(statistics::printStatistics));
    }

    @Test
    void sectionStatisticsCountProxiedAndRealImagesAlike() {
        Section cap1 = new Section("Capitolul 1");
        cap1.addElement(new Paragraph("Paragraph 1"));
        cap1.addElement(new Paragraph("Paragraph 2"));
        cap1.addElement(new Paragraph("Paragraph 3"));
        cap1.addElement(new Paragraph("Paragraph 4"));
        cap1.addElement(new ImageProxy("ImageOne"));
        cap1.addElement(new Image("ImageTwo"));
        cap1.addElement(new Paragraph("Some text"));
        cap1.addElement(new Table());

        BookStatistics stats = new BookStatistics();
        cap1.accept(stats);

        String expected = String.join("\n",
                "Book Statistics:",
                "*** Number of images: 2",
                "*** Number of tables: 1",
                "*** Number of paragraphs: 5") + "\n";
        assertEquals(expected, capture(stats::printStatistics));
    }

    @Test
    void bookAcceptVisitsTheBookFirstAndThenEachOfItsSections() {
        Book book = new Book("B");
        book.addSection("Capitolul 1").addElement(new Paragraph("a"));
        Section cap2 = book.addSection("Capitolul 2");
        cap2.addElement(new Paragraph("b"));
        cap2.addElement(new Table());

        List<String> visited = new ArrayList<>();
        BookStatistics stats = new BookStatistics() {
            @Override
            public void visit(Book visitedBook) {
                visited.add("Book");
                super.visit(visitedBook);
            }

            @Override
            public void visit(Section section) {
                visited.add(section.getTitle());
                super.visit(section);
            }
        };

        book.accept(stats);

        assertEquals(List.of("Book", "Capitolul 1", "Capitolul 2"), visited);
        String expected = String.join("\n",
                "Book Statistics:",
                "*** Number of images: 0",
                "*** Number of tables: 1",
                "*** Number of paragraphs: 2") + "\n";
        assertEquals(expected, capture(stats::printStatistics));
    }
}