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
import com.nighter.nightspot.models.User;

public class VisitUsersViewHolder extends RecyclerView.ViewHolder {

    private User user;
    private TextView userUsername;
    private ImageView card_immagine;


    public VisitUsersViewHolder(@NonNull View itemView) {
        super(itemView);
        userUsername = itemView.findViewById(R.id.userUsername);


    }

    public void setUser(User user) {
        this.user = user;
        userUsername.setText(user.getUsername());
        itemView.setOnClickListener(v -> {
            //NavController controller = Navigation.findNavController(v);
            //NavDirections destionation = HomeFragmentDirections.homeToSpot(spot);
            //controller.navigate(destionation);
        });
    }
}
