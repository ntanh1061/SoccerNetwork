package com.example.manutd.soccersocialnetwork.rest;

import com.example.manutd.soccersocialnetwork.model.DistrictModel;
import com.example.manutd.soccersocialnetwork.model.FieldModel;

import com.example.manutd.soccersocialnetwork.model.MatchsDetailModel;
import com.example.manutd.soccersocialnetwork.model.SlotsModel;
import com.example.manutd.soccersocialnetwork.model.UserModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("matches")
    Call<List<MatchsDetailModel>> getMatchs();

    @DELETE("matches/{id}")
    Call<List<MatchsDetailModel>> deleteMatch(@Path("id") int id);

    @GET("fields/{id}")
    Call<FieldModel> getFieldByID(@Path("id") int id);

    @POST("matches")
    Call<List<MatchsDetailModel>> createMatch(@Body MatchsDetailModel matchsDetailModel);

    @GET("slots")
    Call<List<SlotsModel>> getSlots();

    @POST("slots")
    Call<List<SlotsModel>> createSlots(@Body SlotsModel slotsModel);
}
