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
import com.nighter.nightspot.fragments.MySpotsFragmentDirections;
import com.nighter.nightspot.fragments.MyVisitsFragmentDirections;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.Visit;
import com.squareup.picasso.Picasso;

public class MyVisitsSpotsViewHolder extends RecyclerView.ViewHolder {

    private Visit visit;
    private TextView spotName;
    private ImageView card_immagine;

    private ImageView spotImage;

    private ImageView spot_genre;

    private TextView spotAddress;




    public MyVisitsSpotsViewHolder(@NonNull View itemView) {
        super(itemView);
        spotName = itemView.findViewById(R.id.spotName);
        spotImage = itemView.findViewById(R.id.spot_immagine);
        spot_genre = itemView.findViewById(R.id.spot_genre_icon);
        spotAddress = itemView.findViewById(R.id.address);

    }

    public void setSpot(Visit visit) {
        this.visit = visit;
        spotName.setText(visit.getSpot().getName());
        spot_genre.setImageResource(R.drawable.glasses);
        spotAddress.setText(visit.getVisitDate().toString());
        String spotImgString = visit.getSpot().getPhotos().get(0).getPath();
        Picasso.get()
                .load("http://192.168.1.62:8080/"+spotImgString)
                .into(spotImage);
        itemView.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            NavDirections toUsers = MyVisitsFragmentDirections.myVisitsToUsers(visit);
            controller.navigate(toUsers);
        });
    }

}
