package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SpotApi {

    @GET("/auth/spot/findAllWithCategory")
    Call<List<Spot>> findAllWithCategory(@Header("Authorization") String token);

    @POST("auth/user/add-favorite/{spotId}")
    Call<Void> addSpotToFavourites(@Path("spotId") Long spotId,@Header("Authorization") String token,@Body String userToken);

}

