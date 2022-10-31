package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailHistoryPeminjamanResponse{

    @SerializedName("data")
    private List<DetailHistoryPeminjamanItem> detailHistoryPeminjamanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DetailHistoryPeminjamanItem> getData(){
        return detailHistoryPeminjamanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}