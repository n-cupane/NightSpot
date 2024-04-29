package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Spot_Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Spot_ProductApi {

    @GET("/all/spot_product/findBySpotId/{id}")
    Call<List<Spot_Product>> findBySpotId(@Path("id") Long id);

}
