package com.example.sp_lab.services;

import com.example.sp_lab.Book;
import com.example.sp_lab.persistence.DataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Receiver: business operations on books, persisted through our DataRepository abstraction
@Service
public class BooksService {
    private final DataRepository<Book, Integer> bookRepository;

    public BooksService(DataRepository<Book, Integer> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(int id) {
        return bookRepository.findById(id);
    }

    public int create(Book book) {
        return bookRepository.save(book).getId();
    }

    // Replaces the fields the API exposes; elements and authors of the stored book are kept
    @Transactional
    public Optional<Book> replace(int id, Book book) {
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(book.getTitle());
            existing.setNoOfPages(book.getNoOfPages());
            return bookRepository.save(existing);
        });
    }

    @Transactional
    public boolean delete(int id) {
        if (!bookRepository.existsById(id))
            return false;

        bookRepository.deleteById(id);
        return true;
    }
}