package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailOngoingPengusulanMaintenenceResponse{

    @SerializedName("data")
    private List<DetailOngoingPengusulanMaintenenceItem> detailOngoingPengusulanMaintenenceItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DetailOngoingPengusulanMaintenenceItem> getData(){
        return detailOngoingPengusulanMaintenenceItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}