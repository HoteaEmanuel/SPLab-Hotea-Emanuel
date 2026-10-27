package com.example.sp_lab.commands;

import com.example.sp_lab.Book;

import java.util.Optional;

// Replaces the book with the given id; empty if no such book exists
public class UpdateBookCommand implements Command<Optional<Book>> {
    private final CommandContext context;
    private final int id;
    private final BookRequest request;

    public UpdateBookCommand(CommandContext context, int id, BookRequest request) {
        this.context = context;
        this.id = id;
        this.request = request;
    }

    @Override
    public Optional<Book> execute() {
        return context.getBooksService().replace(id, request.toBook());
    }
}