package com.nighter.nightspot.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nighter.nightspot.databinding.FragmentUserBinding;
import com.nighter.nightspot.models.User;
import com.squareup.picasso.Picasso;


public class UserFragment extends Fragment {


    FragmentUserBinding binding;
    UserFragmentArgs args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = UserFragmentArgs.fromBundle(getArguments());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = args.getUser();
        System.out.println(user);
        System.out.println(user.getPhoto());
        ImageView profileImage = binding.profileImage;
        binding.name.setText("Name: " + user.getFirstName());
        binding.instagram.setText("Instagram: " + user.getInstagramHandle());
        binding.username.setText(user.getUsername());
        Picasso.get()
                .load("http://192.168.1.62:8080/"+ user.getPhoto())
                .into(profileImage);


        binding.Modify.setOnClickListener(v->{
            NavController navController = Navigation.findNavController(view);
            NavDirections toModify = UserFragmentDirections.userToModify(user);
            navController.navigate(toModify);
        });

        binding.MySpots.setOnClickListener(v->{
            NavController navController = Navigation.findNavController(view);
            NavDirections toMySpots = UserFragmentDirections.userToMySpots();
            navController.navigate(toMySpots);
        });

        binding.VisitedSpots.setOnClickListener(v->{
            NavController navController = Navigation.findNavController(view);
            NavDirections toMyVisits = UserFragmentDirections.userToMyVisits();
            navController.navigate(toMyVisits);
        });

    }
}