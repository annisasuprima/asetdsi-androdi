package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListPeminjamanBarangResponse{

    @SerializedName("data")
    private List<ListPeminjamanBarangItem> listPeminjamanBarangItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<ListPeminjamanBarangItem> getData(){
        return listPeminjamanBarangItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}