package com.example.manutd.soccersocialnetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nta on 26/11/2016.
 */

public class MatchsDetailModel implements Serializable {
    @SerializedName("availableSlots")
    int availableSlots;
    @SerializedName("endTime")
    String endTime;
    @SerializedName("fieldId")
    int fieldId;
    @SerializedName("fieldName")
    String fieldName;
    @SerializedName("hostId")
    int hostId;
    @SerializedName("hostName")
    String hostName;
    @SerializedName("matchId")
    int matchId;
    @SerializedName("maxPlayers")
    int maxPlayers;
    @SerializedName("price")
    int price;
    @SerializedName("startTime")
    String startTime;
    @SerializedName("status")
    int status;
    @SerializedName("verificationCode")
    String verificationCode;
    @SerializedName("verified")
    boolean verified;

    public MatchsDetailModel(int availableSlots, String endTime, int fieldId, String fieldName, int hostId, String hostName, int matchId, int maxPlayers, int price, String startTime, int status, String verificationCode, boolean verified) {
        this.availableSlots = availableSlots;
        this.endTime = endTime;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.hostId = hostId;
        this.hostName = hostName;
        this.matchId = matchId;
        this.maxPlayers = maxPlayers;
        this.price = price;
        this.startTime = startTime;
        this.status = status;
        this.verificationCode = verificationCode;
        this.verified = verified;
    }

    public MatchsDetailModel(int availableSlots, String endTime, int fieldId, String fieldName, int hostId, String hostName, int maxPlayers, int price, String startTime, int status, String verificationCode, boolean verified) {
        this.availableSlots = availableSlots;
        this.endTime = endTime;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.hostId = hostId;
        this.hostName = hostName;
        this.matchId = matchId;
        this.maxPlayers = maxPlayers;
        this.price = price;
        this.startTime = startTime;
        this.status = status;
        this.verificationCode = verificationCode;
        this.verified = verified;
    }

    public MatchsDetailModel(int fieldId, int hostId, int maxPlayers, int price, String startTime, String endTime, String verificationCode, boolean verified) {
        this.fieldId = fieldId;
        this.hostId = hostId;
        this.maxPlayers = maxPlayers;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.verificationCode = verificationCode;
        this.verified = verified;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        return "MatchsDetailModel{" +
                "availableSlots=" + availableSlots +
                ", endTime='" + endTime + '\'' +
                ", fieldId=" + fieldId +
                ", fieldName='" + fieldName + '\'' +
                ", hostId=" + hostId +
                ", hostName='" + hostName + '\'' +
                ", matchId=" + matchId +
                ", maxPlayers=" + maxPlayers +
                ", price=" + price +
                ", startTime='" + startTime + '\'' +
                ", status=" + status +
                ", verificationCode='" + verificationCode + '\'' +
                ", verified=" + verified +
                '}';
    }
}
