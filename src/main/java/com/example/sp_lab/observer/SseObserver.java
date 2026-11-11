package com.example.sp_lab.observer;

import com.example.sp_lab.Book;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

// ConcreteObserver: pushes every book as a JSON event to one connected browser
public class SseObserver implements Observer<Book> {
    private final SseEmitter emitter;

    public SseObserver(SseEmitter emitter, BooksSubject subject) {
        this.emitter = emitter;
        // Drop the observer when the client goes away, otherwise the subject keeps dead emitters forever
        Runnable detach = () -> subject.detach(this);
        emitter.onCompletion(detach);
        emitter.onTimeout(detach);
        emitter.onError(e -> detach.run());
    }

    // Emitters are not safe for concurrent sends, and books can be created from several threads
    @Override
    public synchronized void update(Book book) {
        try {
            emitter.send(book, MediaType.APPLICATION_JSON);
        } catch (IOException | IllegalStateException e) {
            emitter.completeWithError(e);
        }
    }
}
