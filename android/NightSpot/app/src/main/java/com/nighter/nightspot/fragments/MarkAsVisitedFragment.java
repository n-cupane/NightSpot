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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.databinding.FragmentMarkAsVisitedBinding;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.models.Visit;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.retrofit.VisitApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class MarkAsVisitedFragment extends Fragment {


    private FragmentMarkAsVisitedBinding binding;

    private MarkAsVisitedFragmentArgs args;

    private User u = new User();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = MarkAsVisitedFragmentArgs.fromBundle(getArguments());







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMarkAsVisitedBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spot s = args.getSpot();
        Spinner dropdown = binding.spinner1;
        String[] items = new String[]{"mattina", "pomeriggio", "sera"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        VisitApi visitApi = retrofitService.getRetrofit().create(VisitApi.class);
        String token = SharedPref.loadToken(getContext());
        String username = SharedPref.loadCredentials(getContext()).getUsername();

        userApi.getUserByUsername(username,"Bearer "+token).enqueue(new retrofit2.Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(response.code());
                u = response.body();



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
            }
        });

        binding.save.setOnClickListener(v -> {
            int dayOfMonth = binding.calendarVisit.getDayOfMonth();
            int month = binding.calendarVisit.getMonth();
            int year = binding.calendarVisit.getYear();


            String visitDateString;
            if (month < 10) {
                String monthString = "0" + month;
                String dayOfMonthString = String.valueOf(dayOfMonth);
                String yearString = String.valueOf(year);
                visitDateString = yearString + "-" + monthString + "-" + dayOfMonthString;
            } else {
                String monthString = String.valueOf(month);
                String dayOfMonthString = String.valueOf(dayOfMonth);
                String yearString = String.valueOf(year);
                visitDateString = yearString + "-" + monthString + "-" + dayOfMonthString;
            }

            String selectedValue = (String) binding.spinner1.getSelectedItem();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate formattedDate = LocalDate.parse(visitDateString, formatter);

            LocalTime time = null;



            switch (selectedValue) {
                case "mattina":
                    time = LocalTime.of(9, 0, 10);
                    break;
                case "pomeriggio":
                    time = LocalTime.of(13, 0, 10);
                    break;
                case "sera":
                    time = LocalTime.of(21, 0, 10);
                    break;
            }

            Visit visit = new Visit();
            visit.setSpot(s);
            visit.setUser(u);
            visit.setVisitDate(formattedDate);
            visit.setVisitTime(time);

            visitApi.insertVisit(visit, "Bearer " + token).enqueue(new retrofit2.Callback<Void>() {

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getContext(), "insert failed", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                }
            });
        });







    }
}