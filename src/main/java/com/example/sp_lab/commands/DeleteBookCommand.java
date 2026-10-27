package com.example.sp_lab.commands;

// Returns true if a book with the given id existed and was deleted
public class DeleteBookCommand implements Command<Boolean> {
    private final CommandContext context;
    private final int id;

    public DeleteBookCommand(CommandContext context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public Boolean execute() {
        return context.getBooksService().delete(id);
    }
}