package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SpotApi {

    @GET("/all/spot/findAllWithCategory")
    Call<List<Spot>> findAllWithCategory(@Header("username") String username, @Header("password") String password);



}
