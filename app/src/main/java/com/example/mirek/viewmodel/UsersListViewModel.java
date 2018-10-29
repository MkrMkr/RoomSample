package com.example.mirek.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mirek.BasicApp;
import com.example.mirek.db.User;

import java.util.List;

public class UsersListViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<User>> observableUsers;

    public UsersListViewModel(@NonNull Application application) {
        super(application);

        observableUsers = new MediatorLiveData<>();
        observableUsers.setValue(null);
        LiveData<List<User>> users = ((BasicApp) application).getRepository().getUsers();
        Log.i("testDb", "UsersListViewModel users:"+users);
        observableUsers.addSource(users, observableUsers::setValue);
    }

    public LiveData<List<User>> getUsers() {
        return observableUsers;
    }
}
