package com.nighter.nightspot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.models.Photo;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.viewholder.SpotPhotoViewHolder;
import com.nighter.nightspot.viewholder.SpotViewHolder;

import java.util.List;

public class SpotPhotoAdapter extends RecyclerView.Adapter<SpotPhotoViewHolder> {
    private List<Photo> spotImgList;


    public SpotPhotoAdapter(List<Photo> spotImgList) {
        this.spotImgList = spotImgList;
    }

    @NonNull
    @Override
    public SpotPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.spot_img_row, parent, false);
        return new SpotPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotPhotoViewHolder holder, int position) {
        holder.setImg(spotImgList.get(position));
    }

    @Override
    public int getItemCount() {
        return spotImgList.size();
    }


}
