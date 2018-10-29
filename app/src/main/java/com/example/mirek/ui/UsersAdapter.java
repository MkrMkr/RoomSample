package com.example.mirek.ui;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mirek.db.User;
import com.example.mirek.roomsample.R;

import java.util.List;

class UsersAdapter extends ListAdapter<User, UsersAdapter.UserViewHolder> {

    private List<User> users;

    public UsersAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldUser, @NonNull User newUser) {
                return oldUser.getUid() == newUser.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldUser, @NonNull User newUser) {
                return oldUser.equals(newUser);
            }
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        userViewHolder.textView.setText(super.getItem(position).getFirstName());
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        final TextView textView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
