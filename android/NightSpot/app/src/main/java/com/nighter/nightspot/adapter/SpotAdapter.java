package com.nighter.nightspot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.viewholder.SpotViewHolder;

import java.util.List;

public class SpotAdapter extends RecyclerView.Adapter<SpotViewHolder>{



        private List<Spot> spotList;


        public SpotAdapter(List<Spot> spotList) {
            this.spotList = spotList;
        }

        @NonNull
        @Override
        public SpotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.spot_row, parent, false);
            return new SpotViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SpotViewHolder holder, int position) {
            holder.setSpot(spotList.get(position));
        }

        @Override
        public int getItemCount() {
            return spotList.size();
        }


}
