package com.example.manutd.soccersocialnetwork.mydatabases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nta on 26/11/2016.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public static String DB_NAME ="match";
    public static String DB_TABLE ="match";
    public static String COLUMN_;


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

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
