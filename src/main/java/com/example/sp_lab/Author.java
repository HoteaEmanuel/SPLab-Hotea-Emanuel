package com.example.sp_lab;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;

    // Required by JPA
    protected Author() {
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void print() {
        System.out.println("Author: " + this.name + " " + this.surname);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
