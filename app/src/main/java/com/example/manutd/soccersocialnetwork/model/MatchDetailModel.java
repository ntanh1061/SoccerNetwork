package com.example.manutd.soccersocialnetwork.model;

/**
 * Created by manutd on 20/10/2016.
 */

public class MatchDetailModel {
    String field;
    String price;
    String date;
    int space;
    String status;

    public MatchDetailModel(String field, String price, String date, int space, String status) {
        this.field = field;
        this.price = price;
        this.date = date;
        this.space = space;
        this.status = status;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

