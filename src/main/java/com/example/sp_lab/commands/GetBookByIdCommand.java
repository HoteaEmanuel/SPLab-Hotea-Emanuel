package com.example.sp_lab.commands;

import com.example.sp_lab.Book;

import java.util.Optional;

public class GetBookByIdCommand implements Command<Optional<Book>> {
    private final CommandContext context;
    private final int id;

    public GetBookByIdCommand(CommandContext context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public Optional<Book> execute() {
        return context.getBooksService().getById(id);
    }
}