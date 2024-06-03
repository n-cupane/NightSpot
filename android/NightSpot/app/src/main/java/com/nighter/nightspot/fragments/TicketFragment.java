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
import android.widget.Toast;

import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.models.Ticket;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.R;
import com.nighter.nightspot.databinding.FragmentTicketBinding;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.retrofit.TicketApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketFragment extends Fragment {

    FragmentTicketBinding binding;
    TicketFragmentArgs args;

    User u = new User();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            args = TicketFragmentArgs.fromBundle(getArguments());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentTicketBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spot s = args.getSpot();
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        TicketApi ticketApi = retrofitService.getRetrofit().create(TicketApi.class);

        userApi.getUserByUsername(SharedPref.loadCredentials(getContext()).getUsername(),"Bearer "+SharedPref.loadToken(getContext())).enqueue(new retrofit2.Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                 u = response.body();



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
            }
        });

        binding.send.setOnClickListener(v->{
            Ticket t = new Ticket();
            t.setSpot(s);
            t.setUser(u);
            String ticketDescription = binding.ticketText.getText().toString().trim();
            t.setDescription(ticketDescription);

            ticketApi.insertTicket(t,"Bearer " + SharedPref.loadToken(getContext())).enqueue(new Callback<Void>(

            ) {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getContext(), "Ticket Emitted", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                }
            });
        });
    }
}