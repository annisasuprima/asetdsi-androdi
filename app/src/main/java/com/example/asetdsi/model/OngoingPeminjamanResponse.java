package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OngoingPeminjamanResponse{

    @SerializedName("data")
    private List<OngoingPeminjamanItem> ongoingPeminjamanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<OngoingPeminjamanItem> getData(){
        return ongoingPeminjamanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}