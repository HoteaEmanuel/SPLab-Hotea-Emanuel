package com.example.sp_lab.patterns.composite;

public class CompositeMain {

    public static void main(String[] args) {
        Book crimeAndPunishment = new Book("Crime and Punishment");
        Author Dostoevsky = new Author("Fyodor", "Dostoevsky");
        crimeAndPunishment.addAuthor(Dostoevsky);
        crimeAndPunishment.addParagraph("\"E o zi de Iulie cand...\"");
        Section s1 = crimeAndPunishment.addSection("Capitolul 1");
        Section s2 = crimeAndPunishment.addSection("Capitolul 2");

        Paragraph p1 = new Paragraph("Depresie...");
        Section s11 = new Section("Capitolul 1.1");
        Paragraph p11 = new Paragraph("Ce fac cu viata mea?");
        s11.addElement(p11);
        s1.addElement(p1);
        s1.addElement(s11);
        Paragraph p2 = new Paragraph("Raskolnikov isi plateste pedeapsa..");
        s2.addElement(p2);

        crimeAndPunishment.print();

        System.out.println(s1.getElement(1).toString());

        Image image = new Image("abc.url");

        try {
            new Paragraph("Un paragraf nu poate avea copii").addElement(image);
        } catch (UnsupportedOperationException e) {
            System.out.println("Paragraph: " + e.getMessage());
        }
    }
}
