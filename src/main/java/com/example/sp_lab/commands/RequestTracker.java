package com.example.sp_lab.commands;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;

// Remembers accepted asynchronous requests so clients can poll their status by id
@Component
public class RequestTracker {
    private final Map<String, CompletableFuture<?>> requests = new ConcurrentHashMap<>();

    public String track(CompletableFuture<?> future) {
        String requestId = UUID.randomUUID().toString();
        requests.put(requestId, future);
        return requestId;
    }

    public Optional<RequestStatus> getStatus(String requestId) {
        CompletableFuture<?> future = requests.get(requestId);
        if (future == null)
            return Optional.empty();

        if (!future.isDone())
            return Optional.of(new RequestStatus(requestId, RequestStatus.State.PENDING, null, null));

        try {
            return Optional.of(new RequestStatus(requestId, RequestStatus.State.COMPLETED, future.join(), null));
        } catch (CompletionException e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            return Optional.of(new RequestStatus(requestId, RequestStatus.State.FAILED, null, cause.getMessage()));
        }
    }
}
