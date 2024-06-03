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
import com.nighter.nightspot.R;
import com.nighter.nightspot.adapter.CategoryAdapter;
import com.nighter.nightspot.adapter.SpotAdapter;
import com.nighter.nightspot.databinding.FragmentHomeBinding;
import com.nighter.nightspot.models.AlertDialogCustom;
import com.nighter.nightspot.models.Category;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.CategoryApi;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Spot> spotList;

    private  List<Category> categoryList;

    private AlertDialogCustom alertDialogCustom;

    private HomeFragmentArgs args;

    private String username;
    private String token;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = HomeFragmentArgs.fromBundle(getArguments());
        username = SharedPref.loadCredentials(getContext()).getUsername();
        token = SharedPref.loadToken(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        alertDialogCustom = new AlertDialogCustom(getContext());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Long id = args.getId();
        RetrofitService retrofitService = new RetrofitService();
        SpotApi spotApi = retrofitService.getRetrofit().create(SpotApi.class);
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);

        userApi.getUserByUsername(username,"Bearer "+token).enqueue(new retrofit2.Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                Picasso.get()
                        .load("http://192.168.1.62:8080/"+ u.getPhoto()).placeholder(R.drawable.giphy)
                        .into(binding.immagineProfilo);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
            }
        });

        categoryApi.findAllCategory("Bearer "+SharedPref.loadToken(getContext())).enqueue((new retrofit2.Callback<List<Category>>(){

            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                System.out.println(response.code());
                if(response.code()==200) {
                    categoryList = response.body();
                    CategoryAdapter adapter = new CategoryAdapter(categoryList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true);
                    binding.categoryRecycler.setAdapter(adapter);
                    binding.categoryRecycler.setLayoutManager(linearLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        }));


        if(!alertDialogCustom.isShowing()) {
            alertDialogCustom.show();
        }

        spotApi.findSpotByCategory("Bearer "+SharedPref.loadToken(getContext()),id).enqueue(new retrofit2.Callback<List<Spot>>() {

            @Override
            public void onResponse(retrofit2.Call<List<Spot>> call, retrofit2.Response<List<Spot>> response) {

                System.out.println(response.code());

                if(alertDialogCustom.isShowing()) {
                    alertDialogCustom.dismiss();
                }
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