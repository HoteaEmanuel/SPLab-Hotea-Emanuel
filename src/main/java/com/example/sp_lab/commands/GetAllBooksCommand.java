package com.example.sp_lab.commands;

import com.example.sp_lab.Book;

import java.util.Map;

public class GetAllBooksCommand implements Command<Map<Integer, Book>> {
    private final CommandContext context;

    public GetAllBooksCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public Map<Integer, Book> execute() {
        return context.getBooksService().getAll();
    }
}