package com.nighter.nightspot.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.databinding.FragmentLogInBinding;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.UserApi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LogInFragment extends Fragment {

    private FragmentLogInBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        binding.logIn.setOnClickListener(b -> {

            String username = binding.logInEmail.getText().toString();
            String password = binding.logInPassword.getText().toString();

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            System.out.println(username);
            System.out.println(password);

            userApi.login(user)
                    .enqueue(new retrofit2.Callback<User>() {
                        @Override
                        public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
                            Toast.makeText(getContext(), "Save successful", Toast.LENGTH_SHORT).show();
                            System.out.println(response.code());
                            System.out.println(response.body().toString());
                            NavController navController = Navigation.findNavController(view);
                            NavDirections toHome = LogInFragmentDirections.loginToHome();
                            navController.navigate(toHome);
                        }

                        @Override
                        public void onFailure(retrofit2.Call<User> call, Throwable t) {
                            Toast.makeText(getContext(), "Save failed", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                        }
                    });

        });
    }


}
