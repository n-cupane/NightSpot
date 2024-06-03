package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.UploadImageRequest;
import com.nighter.nightspot.models.User;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @POST("/auth/user/update")
    Call<Void> updateUser(@Body User user,@Header("Authorization") String token);


    @POST("/all/photo/upload/{username}")
    Call<Void> uploadPhoto(@Body UploadImageRequest uploadImageRequest, @Path("username") String username);

    @POST("auth/user/remove-favorite/{userId}")
    Call<Void> removeFavorite(@Path("userId") Long userId,@Header("Authorization") String token, @Body Spot spot);

}
