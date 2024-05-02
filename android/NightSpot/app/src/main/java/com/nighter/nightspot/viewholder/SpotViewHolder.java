package com.nighter.nightspot.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.fragments.HomeFragmentDirections;
import com.nighter.nightspot.models.Spot;
import com.squareup.picasso.Picasso;

public class SpotViewHolder extends RecyclerView.ViewHolder {

    private Spot spot;
    private TextView spotName;

    private ImageView spotImage;
    private ImageView card_immagine;


    public SpotViewHolder(@NonNull View itemView) {
        super(itemView);
        spotName = itemView.findViewById(R.id.spotName);
        spotImage = itemView.findViewById(R.id.spot_immagine);


    }

    public void setSpot(Spot spot) {
        this.spot = spot;
        spotName.setText(spot.getName());
        String spotImgString = spot.getPhotos().get(0).getPath();
        Picasso.get()
                .load("http://192.168.1.62:8080/"+spotImgString)
                .into(spotImage);
        itemView.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            NavDirections destionation = HomeFragmentDirections.homeToSpot(spot);
            controller.navigate(destionation);
        });
    }

}
