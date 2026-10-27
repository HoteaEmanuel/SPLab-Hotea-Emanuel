package com.example.sp_lab.controllers;

import com.example.sp_lab.Book;
import com.example.sp_lab.commands.BookRequest;
import com.example.sp_lab.commands.CommandContext;
import com.example.sp_lab.commands.CommandExecutor;
import com.example.sp_lab.commands.CreateBookCommand;
import com.example.sp_lab.commands.CreatedBook;
import com.example.sp_lab.commands.DeleteBookCommand;
import com.example.sp_lab.commands.GetAllBooksCommand;
import com.example.sp_lab.commands.GetBookByIdCommand;
import com.example.sp_lab.commands.RequestTracker;
import com.example.sp_lab.commands.UpdateBookCommand;
import com.example.sp_lab.services.BooksService;
import com.example.sp_lab.services.IsbnService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

// Presentation layer for /books: acts as client, creating a command per request and handing it to an executor
@RestController
@RequestMapping("/books")
public class BooksController {

    private final CommandContext context;
    private final CommandExecutor syncExecutor;
    private final CommandExecutor asyncExecutor;
    private final RequestTracker requestTracker;

    public BooksController(BooksService booksService,
                           IsbnService isbnService,
                           @Qualifier("syncCommandExecutor") CommandExecutor syncExecutor,
                           @Qualifier("asyncCommandExecutor") CommandExecutor asyncExecutor,
                           RequestTracker requestTracker) {
        this.context = new CommandContext(booksService, isbnService);
        this.syncExecutor = syncExecutor;
        this.asyncExecutor = asyncExecutor;
        this.requestTracker = requestTracker;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Book>> getAllBooks() {
        return ResponseEntity.ok(syncExecutor.execute(new GetAllBooksCommand(context)).join());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.of(syncExecutor.execute(new GetBookByIdCommand(context, id)).join());
    }

    /* Queued request, async, client gets 202 with an url to check the request status */
    @PostMapping
    public ResponseEntity<Map<String, String>> createBook(@RequestBody BookRequest request) {
        CompletableFuture<CreatedBook> result = asyncExecutor.execute(new CreateBookCommand(context, request));
        String requestId = requestTracker.track(result);
        String statusUrl = "/requests/" + requestId;

        return ResponseEntity.accepted()
                .location(URI.create(statusUrl))
                .body(Map.of("requestId", requestId, "statusUrl", statusUrl));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody BookRequest request) {
        return ResponseEntity.of(syncExecutor.execute(new UpdateBookCommand(context, id, request)).join());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        boolean deleted = syncExecutor.execute(new DeleteBookCommand(context, id)).join();
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}