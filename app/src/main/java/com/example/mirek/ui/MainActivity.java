package com.example.mirek.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mirek.roomsample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            UsersListFragment fragment = new UsersListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment,UsersListFragment.TAG).commit();
        }
    }
}
