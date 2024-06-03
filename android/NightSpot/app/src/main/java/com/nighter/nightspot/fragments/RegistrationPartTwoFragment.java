package com.nighter.nightspot.fragments;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.Toast;

import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.R;
import com.nighter.nightspot.databinding.FragmentRegistrationBinding;
import com.nighter.nightspot.databinding.FragmentRegistrationPartTwoBinding;
import com.nighter.nightspot.databinding.FragmentSpotBinding;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class RegistrationPartTwoFragment extends Fragment {

    private FragmentRegistrationPartTwoBinding binding;

    private RegistrationPartTwoFragmentArgs args;

    private User u;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = RegistrationPartTwoFragmentArgs.fromBundle(getArguments());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationPartTwoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        u = args.getUser();
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);



        binding.Next.setOnClickListener(v-> {

            int dayOfMonth = binding.calendarDOB.getDayOfMonth();
            int month = binding.calendarDOB.getMonth();
            int year = binding.calendarDOB.getYear();


            String DOBDateString;
            if (month < 10 && dayOfMonth >= 10) {
                String monthString = "0" + month;
                String dayOfMonthString = String.valueOf(dayOfMonth);
                String yearString = String.valueOf(year);
                DOBDateString = yearString + "-" + monthString + "-" + dayOfMonthString;
            } else if (month < 10 && dayOfMonth<10){
                String monthString = "0" + month;
                String dayOfMonthString = String.valueOf(dayOfMonth);
                String yearString = String.valueOf(year);
                DOBDateString = yearString + "-" + monthString + "-" + "0" +  dayOfMonthString;
            } else {
                String monthString = String.valueOf(month);
                String dayOfMonthString = String.valueOf(dayOfMonth);
                String yearString = String.valueOf(year);
                DOBDateString = yearString + "-" + monthString + "-" + dayOfMonthString;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate formattedDate = LocalDate.parse(DOBDateString, formatter);

            u.setDateOfBirth(formattedDate);

            NavController navController = Navigation.findNavController(view);
            NavDirections toThirdPart = RegistrationPartTwoFragmentDirections.signUpThirdPart(u);
            navController.navigate(toThirdPart);

        });
    }
}