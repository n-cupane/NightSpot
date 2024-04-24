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
import com.nighter.nightspot.R;
import com.nighter.nightspot.databinding.FragmentModifyBinding;
import com.nighter.nightspot.databinding.FragmentUserBinding;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class ModifyFragment extends Fragment {

    FragmentModifyBinding binding;
    ModifyFragmentArgs args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = ModifyFragmentArgs.fromBundle(getArguments());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModifyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        User user = args.getUser();
        binding.email.setText(user.getEmail());
        binding.instagram.setText(user.getInstagramHandle());

        binding.save.setOnClickListener(v->{
            String email = binding.email.getText().toString().trim();
            String instagram = binding.instagram.getText().toString().trim();
            user.setEmail(email);
            user.setInstagramHandle(instagram);
            String token = SharedPref.loadToken(getContext());
            userApi.updateUser(user,"Bearer "+token).enqueue(new retrofit2.Callback<Void>(){

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    NavController navController = Navigation.findNavController(view);
                    NavDirections toUser = ModifyFragmentDirections.modifyToUser(user);
                    navController.navigate(toUser);

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