package com.nighter.nightspot.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.fragments.HomeFragmentDirections;
import com.nighter.nightspot.fragments.MySpotsFragmentDirections;
import com.nighter.nightspot.models.Spot;
import com.squareup.picasso.Picasso;

public class MySpotsViewHolder extends RecyclerView.ViewHolder {

    private Spot spot;
    private TextView spotName;
    private ImageView card_immagine;

    private ImageView spotImage;

    private ImageView spot_genre;

    public MySpotsViewHolder(@NonNull View itemView) {
        super(itemView);
        spotName = itemView.findViewById(R.id.spotName);
        spotImage = itemView.findViewById(R.id.spot_immagine);
        spot_genre = itemView.findViewById(R.id.spot_genre_icon);


    }

    public void setSpot(Spot spot) {
        this.spot = spot;
        spotName.setText(spot.getName());
        spot_genre.setImageResource(R.drawable.fire);
        String spotImgString = spot.getPhotos().get(0).getPath();
        Picasso.get()
                .load("http://192.168.1.62:8080/"+spotImgString)
                .into(spotImage);
        itemView.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            NavDirections destionation = MySpotsFragmentDirections.myspotsToSpot(spot);
            controller.navigate(destionation);
        });
    }

}
