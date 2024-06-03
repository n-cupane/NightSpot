package com.nighter.nightspot.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.R;
import com.nighter.nightspot.adapter.MenuAdapter;
import com.nighter.nightspot.adapter.SpotAdapter;
import com.nighter.nightspot.databinding.FragmentMenuBinding;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.Spot_Product;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.Spot_ProductApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class MenuFragment extends Fragment {

    FragmentMenuBinding binding;

    MenuFragmentArgs args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = MenuFragmentArgs.fromBundle(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spot s = args.getSpot();
        System.out.println(s.getId());
        RetrofitService retrofitService = new RetrofitService();
        Spot_ProductApi spotProductApi = retrofitService.getRetrofit().create(Spot_ProductApi.class);
        spotProductApi.findBySpotId("Bearer "+SharedPref.loadToken(getContext()),s.getId()).enqueue(new retrofit2.Callback<List<Spot_Product>>(){

            @Override
            public void onResponse(Call<List<Spot_Product>> call, Response<List<Spot_Product>> response) {
                System.out.println(response.code());
                System.out.println(response.body());
                MenuAdapter adapter = new MenuAdapter(response.body());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

                binding.menuRecycler.setAdapter(adapter);
                binding.menuRecycler.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Spot_Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
            }
        });



    }
}