package com.nighter.nightspot.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.R;
import com.nighter.nightspot.adapter.MySpotsAdapter;
import com.nighter.nightspot.adapter.SpotAdapter;
import com.nighter.nightspot.adapter.SpotPhotoAdapter;
import com.nighter.nightspot.databinding.FragmentSpotBinding;
import com.nighter.nightspot.models.Photo;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;
import com.squareup.picasso.Picasso;



public class SpotFragment extends Fragment {

    private User u;
    private SpotFragmentArgs args;
    private FragmentSpotBinding binding;
    private List<Spot> MySpots;

    private boolean favorite = false;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            args = SpotFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpotBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //prelevo lo spot
        Spot s = args.getSpot();
        Long id = s.getId();
        final String phoneNumber = args.getSpot().getContact();
        final String message = "Salve vorrei prenotare un tavolo...";

        //Setto l'immagine

        //binding.spotImg.setImageResource();
        binding.spotName.setText(args.getSpot().getName());
        binding.spotContact.setText(args.getSpot().getContact());
        binding.spotPosition.setText(args.getSpot().getPosition());
        binding.spotTimetables.setText(args.getSpot().getTimetables());


        // Ricavo i dati dell'utente e dallo spot
        RetrofitService retrofitService = new RetrofitService();
        SpotApi spotApi = retrofitService.getRetrofit().create(SpotApi.class);
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        String username = SharedPref.loadCredentials(getContext()).getUsername();
        String token = SharedPref.loadToken(getContext());

        userApi.getUserByUsername(username, "Bearer " + token).enqueue(new retrofit2.Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                u= response.body();
                MySpots = u.getSpots();
                System.out.println(MySpots);
                boolean favorite = false;

                for(Spot mySpots : MySpots) {
                    if(mySpots.getId() == id) {
                        favorite =true;
                    }
                }

                if(!favorite) {
                    binding.AddFavorites.setOnClickListener(v->{
                        spotApi.addSpotToFavourites(id,"Bearer "+token,"Bearer "+token).enqueue(new retrofit2.Callback<Void>(){

                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(getContext(), "added to favourites ", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getContext(), "added to favourites failed", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                            }
                        });


                    });


                } else {
                    binding.AddFavorites.setText("Rimuovi");

                    binding.AddFavorites.setOnClickListener(v->{
                        userApi.removeFavorite(u.getId(),"Bearer "+token,s).enqueue(new retrofit2.Callback<Void>(){

                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(getContext(), "removed from favorites ", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getContext(), "error removing from favorites", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                            }
                        });


                    });
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        //setto le immagini
        List<Photo> spotImgs = s.getPhotos();
        SpotPhotoAdapter adapter = new SpotPhotoAdapter(spotImgs);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);


        binding.spotImg.setAdapter(adapter);
        binding.spotImg.setLayoutManager(gridLayoutManager);

        binding.spotMenu.setOnClickListener(v->{
            NavController navController = Navigation.findNavController(view);
            NavDirections toMarkVisit = SpotFragmentDirections.spotToMenu(s);
            navController.navigate(toMarkVisit);
        });









        //Prelevo l'utente dall'username


        binding.spotContact.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,message);
            intent.putExtra("jid", phoneNumber + "@s.whatsapp.net");
            intent.setType("text/plain");
            intent.setPackage("com.whatsapp");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Intent chooser = new Intent(Intent.ACTION_SENDTO);
                chooser.setData(Uri.parse("smsto:" + Uri.encode(phoneNumber)));
                chooser.putExtra("sms_body", message);
                startActivity(chooser);
            }

        });



        // Add spot to favourites







        binding.setVisit.setOnClickListener(v->{
            System.out.println(s);
            NavController navController = Navigation.findNavController(view);
            NavDirections toMarkVisit = SpotFragmentDirections.spotToVisited(s);
            navController.navigate(toMarkVisit);
        });

        binding.ticket.setOnClickListener(v->{
            NavController navController = Navigation.findNavController(view);
            NavDirections toTicket = SpotFragmentDirections.spotToTicket(s);
            navController.navigate(toTicket);
        });



    }
}