package com.example.sp_lab.controllers;

import com.example.sp_lab.commands.RequestStatus;
import com.example.sp_lab.commands.RequestTracker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Clients poll here to find out whether an accepted (202) request has finished
@RestController
@RequestMapping("/requests")
public class RequestsController {

    private final RequestTracker requestTracker;

    public RequestsController(RequestTracker requestTracker) {
        this.requestTracker = requestTracker;
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<RequestStatus> getStatus(@PathVariable String requestId) {
        return ResponseEntity.of(requestTracker.getStatus(requestId));
    }
}