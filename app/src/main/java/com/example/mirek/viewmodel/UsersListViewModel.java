package com.example.mirek.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.mirek.BasicApp;
import com.example.mirek.db.User;

import java.util.List;

public class UsersListViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<User>> observableUsers;

    public UsersListViewModel(@NonNull Application application) {
        super(application);
        Log.i("testDb", "UserListViewModel created");
        observableUsers = new MediatorLiveData<>();
       // observableUsers.setValue(null);
        LiveData<List<User>> users = ((BasicApp) application).getRepository().getUsers();

        observableUsers.addSource(users, users1 -> {
            observableUsers.setValue(users1);
            Log.i("testDb", "UserListViewModel-> onChanged:" + users1);
        });
    }

    public LiveData<List<User>> getUsers() {
        return observableUsers;
    }
}
