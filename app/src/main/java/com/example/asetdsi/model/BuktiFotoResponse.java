package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BuktiFotoResponse{

    @SerializedName("data")
    private List<BuktiFotoItem> buktiFotoItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<BuktiFotoItem> getData(){
        return buktiFotoItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}