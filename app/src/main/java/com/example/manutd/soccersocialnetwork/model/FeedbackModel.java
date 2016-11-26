package com.example.manutd.soccersocialnetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nta on 27/11/2016.
 */

public class FeedbackModel implements Serializable {
    @SerializedName("matchId")
    int matchId;
    @SerializedName("userId")
    int userId;
    @SerializedName("feedback")
    String feedback;
    @SerializedName("username")
    String username;
    @SerializedName("createDate")
    String createDate;

    public FeedbackModel(int matchId, int userId, String feedback) {
        this.matchId = matchId;
        this.userId = userId;
        this.feedback = feedback;
    }

    public FeedbackModel(int matchId, int userId, String feedback, String username, String createDate) {
        this.matchId = matchId;
        this.userId = userId;
        this.feedback = feedback;
        this.username = username;
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

