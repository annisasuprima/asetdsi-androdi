package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailHistoryPengusulanMaintenenceResponse{

    @SerializedName("data")
    private List<DetailHistoryPengusulanMaintenenceItem> detailHistoryPengusulanMaintenenceItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DetailHistoryPengusulanMaintenenceItem> getData(){
        return detailHistoryPengusulanMaintenenceItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}