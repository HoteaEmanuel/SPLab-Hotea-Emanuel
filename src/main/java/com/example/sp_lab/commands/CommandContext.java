package com.example.sp_lab.commands;

import com.example.sp_lab.services.BooksService;
import com.example.sp_lab.services.IsbnService;

// Holds the external dependencies the commands delegate to
public class CommandContext {
    private final BooksService booksService;
    private final IsbnService isbnService;

    public CommandContext(BooksService booksService, IsbnService isbnService) {
        this.booksService = booksService;
        this.isbnService = isbnService;
    }

    public BooksService getBooksService() {
        return booksService;
    }

    public IsbnService getIsbnService() {
        return isbnService;
    }
}