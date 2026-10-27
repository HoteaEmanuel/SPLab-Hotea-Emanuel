package com.example.sp_lab.commands;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Thread pool pattern: commands are queued and picked up by a fixed number of worker threads
@Component("asyncCommandExecutor")
public class AsyncCommandExecutor implements CommandExecutor {
    private static final int POOL_SIZE = 4;

    private final ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    @Override
    public <T> CompletableFuture<T> execute(Command<T> command) {
        return CompletableFuture.supplyAsync(command::execute, pool);
    }

    @PreDestroy
    public void shutdown() {
        pool.shutdown();
    }
}