package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListPeminjamanBangunanResponse{

    @SerializedName("data")
    private List<ListPeminjamanBangunanItem> listPeminjamanBangunanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<ListPeminjamanBangunanItem> getData(){
        return listPeminjamanBangunanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}