package com.example.mirek;

import android.app.Application;

import com.example.mirek.db.AppDatabase;
import com.example.mirek.db.DataRepository;


public class BasicApp extends Application {

    private AppExecutors executors;

    @Override
    public void onCreate() {
        super.onCreate();

        executors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this,executors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }

}
