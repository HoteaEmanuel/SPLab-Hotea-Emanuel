package com.example.sp_lab.services;

import com.example.sp_lab.Book;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

// Receiver: keeps the books in memory until it gets connected to the persistence layer
@Service
public class BooksService {
    private final Map<Integer, Book> books = new LinkedHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public synchronized Map<Integer, Book> getAll() {
        return new LinkedHashMap<>(books);
    }

    public synchronized Optional<Book> getById(int id) {
        return Optional.ofNullable(books.get(id));
    }

    public synchronized int create(Book book) {
        int id = nextId.getAndIncrement();
        books.put(id, book);
        return id;
    }

    public synchronized Optional<Book> replace(int id, Book book) {
        if (!books.containsKey(id))
            return Optional.empty();

        books.put(id, book);
        return Optional.of(book);
    }

    public synchronized boolean delete(int id) {
        return books.remove(id) != null;
    }
}
