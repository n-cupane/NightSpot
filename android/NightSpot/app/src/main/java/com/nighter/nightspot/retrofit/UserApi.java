package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET("/all/user/show-all")
    Call<List<User>> getAllUsers();

    @POST("/all/user/insert")
    Call<Void> save(@Body User user);

    @POST("/all/user/login")
    Call<Void> login(@Body User user);

    @GET("/auth/user/show/username/{username}")
    Call<User> getUserByUsername(@Path("username") String Username, @Header("Authorization") String token);

}
