package com.nighter.nightspot.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nighter.nightspot.R;
import com.nighter.nightspot.adapter.MySpotsAdapter;
import com.nighter.nightspot.adapter.SpotAdapter;
import com.nighter.nightspot.databinding.FragmentModifyBinding;
import com.nighter.nightspot.databinding.FragmentMySpotsBinding;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class MySpotsFragment extends Fragment {

    private FragmentMySpotsBinding binding;

    private User u;

    private List<Spot> MySpots;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMySpotsBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        String username = SharedPref.loadCredentials(getContext()).getUsername();
        String token = SharedPref.loadToken(getContext());

        userApi.getUserByUsername(username, "Bearer " + token).enqueue(new retrofit2.Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                u= response.body();
                MySpots = u.getSpots();
                MySpotsAdapter adapter = new MySpotsAdapter(MySpots);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
                binding.homeRecycler.setAdapter(adapter);
                binding.homeRecycler.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }
}