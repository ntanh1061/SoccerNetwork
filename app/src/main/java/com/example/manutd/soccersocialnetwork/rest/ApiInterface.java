package com.example.manutd.soccersocialnetwork.rest;

import com.example.manutd.soccersocialnetwork.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nta on 24/11/2016.
 */

public interface ApiInterface {
    @GET("users")
    Call<List<UserModel>> getUser();
}
