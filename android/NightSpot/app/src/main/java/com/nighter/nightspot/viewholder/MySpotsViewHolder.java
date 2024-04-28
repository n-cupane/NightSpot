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

public class MySpotsViewHolder extends RecyclerView.ViewHolder {

    private Spot spot;
    private TextView spotName;
    private ImageView card_immagine;

    public MySpotsViewHolder(@NonNull View itemView) {
        super(itemView);
        spotName = itemView.findViewById(R.id.spotName);


    }

    public void setSpot(Spot spot) {
        this.spot = spot;
        spotName.setText(spot.getName());
        itemView.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            NavDirections destionation = MySpotsFragmentDirections.myspotsToSpot(spot);
            controller.navigate(destionation);
        });
    }

}
