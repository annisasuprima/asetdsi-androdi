package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DaftarBarangResponse{

    @SerializedName("data")
    private List<DaftarBarangItem> daftarBarangItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DaftarBarangItem> getData(){
        return daftarBarangItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}