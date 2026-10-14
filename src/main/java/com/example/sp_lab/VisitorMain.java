package com.example.sp_lab;

public class VisitorMain {

    public static void main(String[] args) {
        Section cap1 = new Section("Capitolul 1");
        Paragraph p1 = new Paragraph("Paragraph 1");
        cap1.addElement(p1);
        Paragraph p2 = new Paragraph("Paragraph 2");
        cap1.addElement(p2);
        Paragraph p3 = new Paragraph("Paragraph 3");
        cap1.addElement(p3);
        Paragraph p4 = new Paragraph("Paragraph 4");
        cap1.addElement(p4);
        cap1.addElement(new ImageProxy("ImageOne"));
        cap1.addElement(new Image("ImageTwo"));
        cap1.addElement(new Paragraph("Some text"));
        cap1.addElement(new Table());

        BookStatistics stats = new BookStatistics();
        cap1.accept(stats);
        stats.printStatistics();

        Book book = new Book("Crime and Punishment");
        Section capitolul1 = book.addSection("Capitolul 1");
        for (int i = 1; i <= 10; i++)
            capitolul1.addElement(new Paragraph("Paragraph " + i));
        Section capitolul2 = book.addSection("Capitolul 2");
        capitolul2.addElement(new ImageProxy("ImageOne"));
        capitolul2.addElement(new Paragraph("Some text"));
        Section capitolul21 = new Section("Capitolul 2.1");
        capitolul2.addElement(capitolul21);
        capitolul21.addElement(new Table());
        capitolul21.addElement(new Paragraph("More text"));
        capitolul21.addElement(new Table());
        capitolul21.addElement(new Table());

        for (int i = 1; i <= 5; i++)
            capitolul21.addElement(new Paragraph("Paragraph " + i));
        Section capitolul22 = new Section("Capitolul 2.2");


        for (int i = 1; i < 9; i++)
            capitolul22.addElement(new Paragraph("Paragraph " + i));
        capitolul2.addElement(capitolul22);
        Section capitolul3 = book.addSection("Capitolul 3");
        capitolul3.addElement(new Paragraph("The end"));


        GenerateToC toc = new GenerateToC();
        book.accept(toc);
        toc.getToC().print();
    }
}