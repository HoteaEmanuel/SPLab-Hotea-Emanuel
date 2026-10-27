package com.example.sp_lab.commands;

// What the client sees when polling an accepted request
public record RequestStatus(String requestId, State state, Object result, String error) {

    public enum State { PENDING, COMPLETED, FAILED }
}