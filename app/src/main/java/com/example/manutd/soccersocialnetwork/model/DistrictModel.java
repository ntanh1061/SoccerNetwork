package com.example.manutd.soccersocialnetwork.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nta on 25/11/2016.
 */

public class DistrictModel {
    @SerializedName("districtId")
    private int mDistrictId;
    @SerializedName("districtCode")
    String districtCode;
    @SerializedName("districtName")
    String districtName;
    @SerializedName("cityId")
    String cityId;
    @SerializedName("cityName")
    String cityName;

    public DistrictModel(int mDistrictId, String districtCode, String districtName, String cityId, String cityName) {
        this.mDistrictId = mDistrictId;
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public int getmDistrictId() {
        return mDistrictId;
    }

    public void setmDistrictId(int mDistrictId) {
        this.mDistrictId = mDistrictId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

