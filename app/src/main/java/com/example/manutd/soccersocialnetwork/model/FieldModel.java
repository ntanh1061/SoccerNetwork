package com.example.manutd.soccersocialnetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nta on 25/11/2016.
 */

public class FieldModel implements Serializable {
    @SerializedName("address")
    String address;
    @SerializedName("districtId")
    int districtId;
    @SerializedName("districtName")
    String districtName;
    @SerializedName("fieldId")
    int fieldId;
    @SerializedName("fieldName")
    String fieldName;
    @SerializedName("latitude")
    double latitude;
    @SerializedName("longitude")
    double longitude;
    @SerializedName("phoneNumber")
    String phoneNumber;

    public FieldModel(String address, int districtId, String districtName, int fieldId, String fieldName, double latitude, double longitude, String phoneNumber) {
        this.address = address;
        this.districtId = districtId;
        this.districtName = districtName;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
