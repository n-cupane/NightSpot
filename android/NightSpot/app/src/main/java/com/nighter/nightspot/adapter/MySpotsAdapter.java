package com.nighter.nightspot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.viewholder.MySpotsViewHolder;
import com.nighter.nightspot.viewholder.SpotViewHolder;

import java.util.List;

public class MySpotsAdapter extends RecyclerView.Adapter<MySpotsViewHolder>{

    private List<Spot> spotList;


    public MySpotsAdapter(List<Spot> spotList) {
        this.spotList = spotList;
    }
    @NonNull
    @Override
    public MySpotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.myspots_row, parent, false);
        return new MySpotsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySpotsViewHolder holder, int position) {
        holder.setSpot(spotList.get(position));
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }
}
