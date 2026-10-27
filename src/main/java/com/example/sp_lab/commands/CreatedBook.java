package com.example.sp_lab.commands;

// Result of CreateBookCommand: the id assigned to the book and the ISBN obtained for it
public record CreatedBook(int id, String isbn) {
}