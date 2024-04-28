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
import com.nighter.nightspot.adapter.SpotAdapter;
import com.nighter.nightspot.adapter.VisitUsersAdapter;
import com.nighter.nightspot.databinding.FragmentVisitUsersBinding;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.models.Visit;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.retrofit.VisitApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class VisitUsersFragment extends Fragment {

    private FragmentVisitUsersBinding binding;

    private VisitUsersFragmentArgs args;

    private List<Visit> visits = null;
    private List<User> userList = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = VisitUsersFragmentArgs.fromBundle(getArguments());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentVisitUsersBinding.inflate(inflater,container,false);
            return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userList = new ArrayList();
        RetrofitService retrofitService = new RetrofitService();
        VisitApi visitApi = retrofitService.getRetrofit().create(VisitApi.class);
        Long spotId = args.getVisit().getSpot().getId();
        LocalTime visitTime = args.getVisit().getVisitTime();
        int hour = visitTime.getHour();
        int minutes = visitTime.getMinute();
        int secondsString = visitTime.getSecond();
        if(hour == 9) {
            visitTime = LocalTime.of(9,0,10);
        }
        if(hour == 13) {
            visitTime = LocalTime.of(13,0,10);
        }
        if(hour == 21) {
            visitTime = LocalTime.of(21,0,10);
        }
        String token = SharedPref.loadToken(getContext());

        visitApi.findVisitFromSpotIDandTime(spotId, visitTime, "Bearer " + token).enqueue(new retrofit2.Callback<List<Visit>>(){

            @Override
            public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {

                visits = response.body();
                System.out.println(visits);
                for(Visit visit : visits) {
                    User u = visit.getUser();
                    userList.add(u);
                }
                System.out.println(userList);
                VisitUsersAdapter adapter = new VisitUsersAdapter(userList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

                binding.usersRecycler.setAdapter(adapter);
                binding.usersRecycler.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Visit>> call, Throwable t) {

            }
        });
    }

}