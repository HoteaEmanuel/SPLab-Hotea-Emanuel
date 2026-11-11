package com.example.sp_lab.controllers;

import com.example.sp_lab.observer.BooksSubject;
import com.example.sp_lab.observer.SseObserver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
public class BookSseController {
    private final BooksSubject allBooksSubject;

    public BookSseController(BooksSubject allBooksSubject) {
        this.allBooksSubject = allBooksSubject;
    }

    @GetMapping("/books-sse")
    public SseEmitter getBooksSse() throws IOException {
        final SseEmitter emitter = new SseEmitter(0L);

        emitter.send(SseEmitter.event().comment("connected"));
        allBooksSubject.attach(new SseObserver(emitter, allBooksSubject));
        return emitter;
    }
}
