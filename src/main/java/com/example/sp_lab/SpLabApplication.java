package com.example.sp_lab;

import com.example.sp_lab.difexamples.ClientComponent;
import com.example.sp_lab.difexamples.SingletonComponent;
import com.example.sp_lab.difexamples.TransientComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.naming.Context;

@SpringBootApplication
public class SpLabApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpLabApplication.class, args);

        // Prototype scope: the context creates a new instance on every request
        TransientComponent transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();
        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        // Singleton scope: created at startup, the same instance is always returned
        SingletonComponent singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();
        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        // ClientComponent is a singleton, so it holds one TransientComponent forever
        ClientComponent c = context.getBean(ClientComponent.class);
        c.operation();

        // Requesting a bean by name
        c = (ClientComponent) context.getBean("clientComponent");
        c.operation();



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


        Element image = new ImageProxy("abc.url");

        try {
            new Paragraph("Un paragraf nu poate avea copii").addElement(image);
        } catch (UnsupportedOperationException e) {
            System.out.println("Paragraph: " + e.getMessage());
        }

        crimeAndPunishment.addImage("a.png");
        crimeAndPunishment.addImage("b.png");
        crimeAndPunishment.addTable();
        crimeAndPunishment.addParagraph("Epilog");

        BookStatistics statistics = new BookStatistics();
        crimeAndPunishment.accept(statistics);
        statistics.printStatistics();


        TransientComponent ts= context.getBean(TransientComponent.class);

        System.out.println(ts);


        TransientComponent ts1= context.getBean(TransientComponent.class);

        System.out.println(ts1);

    }

}
