package com.example.mirek.db;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mirek.AppExecutors;

import java.util.Arrays;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "mainDatabase";
    private static AppDatabase dbInstance;
    private final MutableLiveData<Boolean> databaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(Context context, AppExecutors executors) {
        if (dbInstance == null) {
            synchronized (AppDatabase.class) {
                if (dbInstance == null) {
                    dbInstance = buildDatabase(context, executors);
                }
            }
        }
        return dbInstance;
    }

    @NonNull
    private static AppDatabase buildDatabase(Context context, AppExecutors executors) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Log.i("testDb", "buildDatabase->onCreate");
                        executors.getDbIO().execute(() -> executors.getDbIO().execute(() -> {
                            dbInstance.runInTransaction(() -> {
                                        dbInstance.userDao().insertAll(Arrays.asList(new User(1, "mietek1", "mietkovsky"),
                                                new User(2, "mietek2", "mietkovsky2")));
                                        dbInstance.databaseCreated.postValue(true);
                                    }
                            );
                        }));
                    }
                })
                .build();
    }

    private void exposeDatabase(Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        Log.i("testDb", "buildDatabase->setDatabaseCreated()->postValue");
        // databaseCreated.postValue(true);
    }

    public abstract UserDao userDao();
}