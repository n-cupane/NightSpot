package com.nighter.nightspot.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.adapter.SpotAdapter;
import com.nighter.nightspot.databinding.FragmentHomeBinding;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Spot> spotList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RetrofitService retrofitService = new RetrofitService();
        SpotApi spotApi = retrofitService.getRetrofit().create(SpotApi.class);
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        spotApi.findAllWithCategory(SharedPref.loadCredentials(getContext()).getUsername(),SharedPref.loadCredentials(getContext()).getPassword()).enqueue(new retrofit2.Callback<List<Spot>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Spot>> call, retrofit2.Response<List<Spot>> response) {
                System.out.println(response.code());
                spotList = response.body();
                SpotAdapter adapter = new SpotAdapter(spotList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

                binding.homeRecycler.setAdapter(adapter);
                binding.homeRecycler.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Spot>> call, Throwable t) {
                Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
            }

        });


        binding.MyProfile.setOnClickListener(v -> {
            String username = SharedPref.loadCredentials(getContext()).getUsername();
            String token = SharedPref.loadToken(getContext());
            System.out.println(username);
            userApi.getUserByUsername(username,"Bearer "+token).enqueue(new retrofit2.Callback<User>(){

                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User u = response.body();
                    System.out.println(response.code());
                    NavController navController = Navigation.findNavController(view);
                    NavDirections toUser = HomeFragmentDirections.homeToUser(u);
                    navController.navigate(toUser);

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                }
            });

        });


    }
}