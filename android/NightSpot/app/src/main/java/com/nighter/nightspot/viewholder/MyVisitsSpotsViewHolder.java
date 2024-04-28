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

public class MyVisitsSpotsViewHolder extends RecyclerView.ViewHolder {

    private Visit visit;
    private TextView spotName;
    private ImageView card_immagine;

    public MyVisitsSpotsViewHolder(@NonNull View itemView) {
        super(itemView);
        spotName = itemView.findViewById(R.id.spotName);


    }

    public void setSpot(Visit visit) {
        this.visit = visit;
        spotName.setText(visit.getSpot().getName());
        itemView.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            NavDirections toUsers = MyVisitsFragmentDirections.myVisitsToUsers(visit);
            controller.navigate(toUsers);
        });
    }

}
