package com.example.manutd.soccersocialnetwork.rest;

import com.example.manutd.soccersocialnetwork.model.DistrictModel;
import com.example.manutd.soccersocialnetwork.model.FieldModel;
import com.example.manutd.soccersocialnetwork.model.UserModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by nta on 24/11/2016.
 */

public interface ApiInterface {
    @GET("users")
    Call<List<UserModel>> getUser();

    @GET("districts")
    Call<List<DistrictModel>> getDistricts();

    @POST("users")
    Call<UserModel> createUser(@Body UserModel userModel);

    @PUT("users")
    Call<UserModel> updateUser(@Body UserModel userModel);

    @GET("fields")
    Call<List<FieldModel>> getField();
}
