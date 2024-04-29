package com.nighter.nightspot.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.fragments.HomeFragmentDirections;
import com.nighter.nightspot.models.Photo;
import com.nighter.nightspot.models.Spot;
import com.squareup.picasso.Picasso;

public class SpotPhotoViewHolder extends RecyclerView.ViewHolder {

    private Photo spotImgString;
    private ImageView spotImgRow;

    public SpotPhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        spotImgRow = itemView.findViewById(R.id.spotImgRow);
    }

    public void setImg(Photo spotImgString) {
        this.spotImgString = spotImgString;
        Picasso.get()
                .load("http://192.168.1.62:8080/"+spotImgString.getPath())
                .into(spotImgRow);;
    }

}
