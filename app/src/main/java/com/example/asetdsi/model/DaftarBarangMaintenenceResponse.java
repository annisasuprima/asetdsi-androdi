package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DaftarBarangMaintenenceResponse{

    @SerializedName("data")
    private List<DaftarBarangMaintenenceItem> daftarBarangMaintenenceItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DaftarBarangMaintenenceItem> getData(){
        return daftarBarangMaintenenceItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}