package com.example.sp_lab.commands;

import com.example.sp_lab.Book;

// Data received in the body of POST / PUT requests
public record BookRequest(String title, int noOfPages) {

    public Book toBook() {
        return new Book(title, noOfPages);
    }
}