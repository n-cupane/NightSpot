package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Spot_Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Spot_ProductApi {

    @GET("/all/spot_product/findBySpotId/{spotId}")
    Call<List<Spot_Product>> findBySpotId(@Header ("Authorization") String token,@Path("spotId") Long id);

}
