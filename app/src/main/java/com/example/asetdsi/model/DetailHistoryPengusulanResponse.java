package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailHistoryPengusulanResponse{

    @SerializedName("data")
    private List<DetailHistoryPengusulanItem> detailHistoryPengusulanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DetailHistoryPengusulanItem> getData(){
        return detailHistoryPengusulanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}