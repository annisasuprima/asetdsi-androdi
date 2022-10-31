package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HistoryPengusulanResponse{

    @SerializedName("data")
    private List<HistoryPengusulanItem> historyPengusulanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<HistoryPengusulanItem> getData(){
        return historyPengusulanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}