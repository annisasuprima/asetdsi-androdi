package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailOngoingPeminjamanResponse{

    @SerializedName("data")
    private List<DetailOngoingPeminjamanItem> detailOngoingPeminjamanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DetailOngoingPeminjamanItem> getData(){
        return detailOngoingPeminjamanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}