package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("/all/user/show-all")
    Call<List<User>> getAllUsers();

    @POST("/all/user/insert")
    Call<Void> save(@Body User user);

    @POST("/all/user/login")
    Call<User> login(@Body User user);

}
