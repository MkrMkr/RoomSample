package com.example.mirek.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.Handler;
import android.util.Log;

import java.util.Arrays;
import java.util.List;


public class DataRepository {

    private static DataRepository instance;
    private final AppDatabase repDatabase;
    private MediatorLiveData<List<User>> observableUsers;

    public DataRepository(AppDatabase database) {
        repDatabase = database;

        observableUsers = new MediatorLiveData<>();
        observableUsers.addSource(repDatabase.userDao().getAll(), users -> {
            Log.i("testDb", "DataRepository constructor-> users:"+users);
            observableUsers.postValue(users);
        });

    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository(database);
                }
            }
        }
        return instance;
    }

    public LiveData<List<User>> getUsers() {
        return observableUsers;
    }
}
