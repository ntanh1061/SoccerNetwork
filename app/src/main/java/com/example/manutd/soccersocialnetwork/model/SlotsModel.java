package com.example.manutd.soccersocialnetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nta on 26/11/2016.
 */

public class SlotsModel implements Serializable {
    @SerializedName("matchId")
    int matchId;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("userId")
    int userId;
    @SerializedName("username")
    String username;
    @SerializedName("verificationCode")
    String verificationCode;
    @SerializedName("verified")
    boolean verified;

    public SlotsModel(int matchId, int quantity, int userId, String username, String verificationCode, boolean verified) {
        this.matchId = matchId;
        this.quantity = quantity;
        this.userId = userId;
        this.username = username;
        this.verificationCode = verificationCode;
        this.verified = verified;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "SlotsModel{" +
                "matchId=" + matchId +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", verified=" + verified +
                '}';
    }
}
