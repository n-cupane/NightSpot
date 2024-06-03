package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Visit;

import java.time.LocalTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VisitApi {

    @POST("/auth/visit/insert")
    Call<Void> insertVisit(@Body Visit visit, @Header("Authorization") String token);

    @GET("/auth/visit/findVisitFromSpotIDandTime/")
    Call<List<Visit>> findVisitFromSpotIDandTime(@Query("sId") Long sId, @Query("visitTime")LocalTime visitTime, @Header("Authorization") String token);
}
