package com.example.sp_lab.commands;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// Runs the command on the caller's thread; the returned future is already completed
@Component("syncCommandExecutor")
public class SyncCommandExecutor implements CommandExecutor {

    @Override
    public <T> CompletableFuture<T> execute(Command<T> command) {
        return CompletableFuture.completedFuture(command.execute());
    }
}