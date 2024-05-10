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

    private TextView spotAddress;

    private ImageView spot_genre;


    public SpotViewHolder(@NonNull View itemView) {
        super(itemView);
        spotName = itemView.findViewById(R.id.spotName);
        spotImage = itemView.findViewById(R.id.spot_immagine);
        spotAddress = itemView.findViewById(R.id.address);
        spot_genre = itemView.findViewById(R.id.spot_genre_icon);


    }

    public void setSpot(Spot spot) {
        this.spot = spot;
        spotName.setText(spot.getName());
        spotAddress.setText(spot.getPosition());
        spot_genre.setImageResource(R.drawable.disco);
        /*if(spot.getCategory().getName().equalsIgnoreCase("azienda")){
            spot_genre.setImageResource(R.drawable.disco);
        } else if (spot.getCategory().getName().equalsIgnoreCase("bar")) {
            spot_genre.setImageResource(R.drawable.bar);
        } else if (spot.getCategory().getName().equalsIgnoreCase("pub")) {
            spot_genre.setImageResource(R.drawable.pub);
        }*/


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
