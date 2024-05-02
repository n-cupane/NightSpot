package com.nighter.nightspot.retrofit;

import com.nighter.nightspot.models.Ticket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TicketApi {


    @POST("/auth/ticket/insert")
    Call<Void> insertTicket(@Body Ticket ticket, @Header("Authorization") String token);

}
