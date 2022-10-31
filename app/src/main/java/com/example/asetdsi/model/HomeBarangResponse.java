package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HomeBarangResponse{

    @SerializedName("data")
    private List<HomeBarangItem> homeBarangItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<HomeBarangItem> getData(){
        return homeBarangItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}