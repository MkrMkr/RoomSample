package com.example.mirek.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mirek.db.User;
import com.example.mirek.roomsample.R;
import com.example.mirek.viewmodel.UsersListViewModel;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment {


    public static final String TAG = UsersListFragment.class.getSimpleName();
    private UsersAdapter usersAdapter;
    private RecyclerView usersList;

    public UsersListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //TODO: databinding
        View rootView = inflater.inflate(R.layout.fragment_users_list, container, false);
        usersAdapter = new UsersAdapter();
        usersList = rootView.findViewById(R.id.usersList);
        usersList.setAdapter(usersAdapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final UsersListViewModel userViewModel = ViewModelProviders.of(this).get(UsersListViewModel.class);
        userViewModel.getUsers().observe(this, users -> {
            Log.i("testDb", "usersListFragment users: "+users);
            if(users!=null) {
                usersAdapter.setUsers(users);
            }
        });

    }
}
