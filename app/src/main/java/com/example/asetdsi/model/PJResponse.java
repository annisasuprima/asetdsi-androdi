package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PJResponse{

    @SerializedName("data")
    private List<PJItem> pjItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<PJItem> getData(){
        return pjItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}