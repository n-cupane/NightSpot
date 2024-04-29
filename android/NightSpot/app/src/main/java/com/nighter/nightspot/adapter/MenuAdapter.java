package com.nighter.nightspot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.Spot_Product;
import com.nighter.nightspot.viewholder.MenuViewHolder;
import com.nighter.nightspot.viewholder.MySpotsViewHolder;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>{
    private List<Spot_Product> spotProducts;


    public MenuAdapter(List<Spot_Product> spotProducts) {
        this.spotProducts = spotProducts;
    }
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.menu_row, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.setProduct(spotProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return spotProducts.size();
    }
}
