package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TarifaService {

    @GET("tarifa/NORMAL")
    Call<Object> getTarifa();

   // @GET("/users/{username}")
    //public Call<User> getUser(@Path("username") String username);
}