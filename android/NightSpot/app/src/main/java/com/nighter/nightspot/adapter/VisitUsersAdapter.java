package com.nighter.nightspot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.viewholder.SpotViewHolder;
import com.nighter.nightspot.viewholder.VisitUsersViewHolder;

import java.util.List;

public class VisitUsersAdapter extends RecyclerView.Adapter<VisitUsersViewHolder> {

    private List<User> userList;


    public VisitUsersAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public VisitUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.visit_users_row, parent, false);
        return new VisitUsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitUsersViewHolder holder, int position) {
        holder.setUser(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
