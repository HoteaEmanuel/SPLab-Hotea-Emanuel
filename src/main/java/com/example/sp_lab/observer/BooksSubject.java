package com.example.sp_lab.observer;

import com.example.sp_lab.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BooksSubject implements Subject<Book> {
    private final List<Book> books = new ArrayList<>();
    private final List<Observer<Book>> observers = new ArrayList<>();

    @Override
    public synchronized void attach(Observer<Book> observer) {
        observers.add(observer);
    }

    @Override
    public synchronized void detach(Observer<Book> observer) {
        observers.remove(observer);
    }

    // Loops over a copy: a failed update detaches its observer, which must not modify the list being iterated
    @Override
    public void notifyObservers(Book book) {
        List<Observer<Book>> snapshot;
        synchronized (this) {
            snapshot = new ArrayList<>(observers);
        }
        for (Observer<Book> observer : snapshot)
            observer.update(book);
    }

    // State change: store the book, then push it to the observers
    public void add(Book book) {
        synchronized (this) {
            books.add(book);
        }
        notifyObservers(book);
    }

    public synchronized List<Book> getBooks() {
        return List.copyOf(books);
    }
}
