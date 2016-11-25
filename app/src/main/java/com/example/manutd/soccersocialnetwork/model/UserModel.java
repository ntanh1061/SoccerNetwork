package com.example.manutd.soccersocialnetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nta on 24/11/2016.
 */

public class UserModel implements Serializable {
    @SerializedName("id")
    private int mId;
    @SerializedName("fullname")
    private String mFullName;
    @SerializedName("username")
    private String mUserName;
    @SerializedName("password")
    private String mPassword;

    public UserModel(int mId, String mFullName, String mUserName, String mPassword) {
        this.mId = mId;
        this.mFullName = mFullName;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
