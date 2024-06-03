package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CategoryApi {

    @GET("/auth/category/findAllWithoutSpots")
    Call<List<Category>> findAllCategory(@Header("Authorization") String token);

}
