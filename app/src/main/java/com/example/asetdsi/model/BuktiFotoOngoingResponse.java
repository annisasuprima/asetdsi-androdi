package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BuktiFotoOngoingResponse{

    @SerializedName("data")
    private List<BuktiFotoOngoingItem> buktiFotoOngoingItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<BuktiFotoOngoingItem> getData(){
        return buktiFotoOngoingItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}