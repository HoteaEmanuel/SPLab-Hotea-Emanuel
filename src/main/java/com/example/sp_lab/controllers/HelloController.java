package com.example.sp_lab.controllers;

import com.example.sp_lab.difexamples.ClientComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final ClientComponent clientComponent;

    public HelloController(ClientComponent clientComponent) {
        this.clientComponent = clientComponent;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from Spring Boot";
    }

    @GetMapping("/client")
    public String client() {
        return "Hello from ClientComponent = " + clientComponent;
    }
}
