package com.example.mirek;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private final Executor dbIO;

    public AppExecutors(Executor dbIO) {
        this.dbIO = dbIO;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor());
    }

    public Executor getDbIO() {
        return dbIO;
    }
}
