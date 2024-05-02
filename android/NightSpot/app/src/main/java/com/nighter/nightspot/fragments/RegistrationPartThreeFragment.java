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
import com.nighter.nightspot.databinding.FragmentRegistrationPartThreeBinding;
import com.nighter.nightspot.databinding.FragmentSpotBinding;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class RegistrationPartThreeFragment extends Fragment {

    private FragmentRegistrationPartThreeBinding binding;

    private RegistrationPartThreeFragmentArgs args;

    private User u;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = RegistrationPartThreeFragmentArgs.fromBundle(getArguments());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationPartThreeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        u = args.getUser();

        binding.Next.setOnClickListener(v-> {

            String username = binding.insertUsername.getText().toString();
            String password = binding.insertPassword.getText().toString();
            String instagram = binding.insertInstagram.getText().toString();


            u.setPassword(password);
            u.setUsername(username);
            u.setInstagramHandle(instagram);


            NavController navController = Navigation.findNavController(view);
            NavDirections toLastPart = RegistrationPartThreeFragmentDirections.signUpLastPart(u);
            navController.navigate(toLastPart);

        });
    }
}