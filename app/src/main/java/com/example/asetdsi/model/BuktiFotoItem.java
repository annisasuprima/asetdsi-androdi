package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class BuktiFotoItem{

    @SerializedName("photo_name")
    private String photoName;

    @SerializedName("req_maintenence_id")
    private int reqMaintenenceId;

    public String getPhotoName(){
        return photoName;
    }

    public int getReqMaintenenceId(){
        return reqMaintenenceId;
    }
}