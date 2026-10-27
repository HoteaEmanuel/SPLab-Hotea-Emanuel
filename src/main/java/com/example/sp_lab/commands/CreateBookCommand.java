package com.example.sp_lab.commands;

// Orchestrates two services: obtains an ISBN from the (slow) external service, then stores the book
public class CreateBookCommand implements Command<CreatedBook> {
    private final CommandContext context;
    private final BookRequest request;

    public CreateBookCommand(CommandContext context, BookRequest request) {
        this.context = context;
        this.request = request;
    }

    @Override
    public CreatedBook execute() {
        String isbn = context.getIsbnService().generateIsbn();
        int id = context.getBooksService().create(request.toBook());
        return new CreatedBook(id, isbn);
    }
}