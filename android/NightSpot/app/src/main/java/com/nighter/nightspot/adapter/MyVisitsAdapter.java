package com.nighter.nightspot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nighter.nightspot.R;
import com.nighter.nightspot.models.Visit;
import com.nighter.nightspot.viewholder.MyVisitsSpotsViewHolder;

import java.util.List;

public class MyVisitsAdapter extends RecyclerView.Adapter<MyVisitsSpotsViewHolder>{

    private List<Visit> spotList;


    public MyVisitsAdapter(List<Visit> spotList) {
        this.spotList = spotList;
    }
    @NonNull
    @Override
    public MyVisitsSpotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.myspots_row, parent, false);
        return new MyVisitsSpotsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVisitsSpotsViewHolder holder, int position) {
        holder.setSpot(spotList.get(position));
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }

}
