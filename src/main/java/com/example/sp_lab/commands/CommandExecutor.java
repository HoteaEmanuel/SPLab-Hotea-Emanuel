package com.example.sp_lab.commands;

import java.util.concurrent.CompletableFuture;

// Invoker: decides how a command is run, so the same command can be processed synchronously or asynchronously
public interface CommandExecutor {
    <T> CompletableFuture<T> execute(Command<T> command);
}