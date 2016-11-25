package com.example.manutd.soccersocialnetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nta on 24/11/2016.
 */

public class UserModel implements Serializable {
    @SerializedName("districtId")
    int districtId;
    @SerializedName("userId")
    int userId;
    @SerializedName("districtName")
    String districtName;
    @SerializedName("phoneNumber")
    String phoneNumber;
    @SerializedName("status")
    int status;
    @SerializedName("username")
    String username;
    @SerializedName("userType")
    int userType;
    @SerializedName("verificationCode")
    String verificationCode;
    @SerializedName("verified")
    boolean verified;
    @SerializedName("email")
    String email;
    @SerializedName("lastLogin")
    String lastLogin;
    @SerializedName("password")
    String password;

    public UserModel(int districtId, int userId, String districtName, String phoneNumber, int status, String username, int userType, String verificationCode, boolean verified, String email, String lastLogin, String password) {
        this.districtId = districtId;
        this.userId = userId;
        this.districtName = districtName;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.username = username;
        this.userType = userType;
        this.verificationCode = verificationCode;
        this.verified = verified;
        this.email = email;
        this.lastLogin = lastLogin;
        this.password = password;
    }

    public UserModel(int districtId, String districtName, String phoneNumber, int status, String username, int userType, String verificationCode, boolean verified, String email, String lastLogin, String password) {
        this.districtName = districtName;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.username = username;
        this.userType = userType;
        this.verificationCode = verificationCode;
        this.verified = verified;
        this.email = email;
        this.lastLogin = lastLogin;
        this.password = password;
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "districtId=" + districtId +
                ", userId=" + userId +
                ", districtName='" + districtName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", userType=" + userType +
                ", verificationCode='" + verificationCode + '\'' +
                ", verified=" + verified +
                ", email='" + email + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
