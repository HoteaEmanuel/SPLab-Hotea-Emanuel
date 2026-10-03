package com.example.sp_lab;

import com.example.sp_lab.patterns.composite.Author;
import com.example.sp_lab.patterns.composite.Book;
import com.example.sp_lab.patterns.composite.Paragraph;
import com.example.sp_lab.patterns.composite.Section;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpLabApplication {

    public static void main(String[] args) {
        Book crimeAndPunishment = new Book("Crime and Punishment");
        Author Dostoevsky = new Author("Fyodor", "Dostoevsky");
        crimeAndPunishment.addAuthor(Dostoevsky);
        Paragraph p = new Paragraph("\"E o zi de Iulie cand...\"");
        crimeAndPunishment.addContent(p);
        Section s1 = new Section("Capitolul 1");
        Paragraph p1 = new Paragraph("Depresie...");
        Section s11 = new Section("Capitolul 1.1");
        Paragraph p11 = new Paragraph("Ce fac cu viata mea?");
        s11.addElement(p11);
        s1.addElement(p1);
        s1.addElement(s11);
        Section s2 = new Section("Capitolul 2");
        Paragraph p2 = new Paragraph("E permis orice pentru unii, pana si crima e onoare");
        s2.addElement(p2);

        crimeAndPunishment.addContent(s1);
        crimeAndPunishment.addContent(s2);

        crimeAndPunishment.print();

        System.out.println(s1.getElement(1).toString());
    }

}
