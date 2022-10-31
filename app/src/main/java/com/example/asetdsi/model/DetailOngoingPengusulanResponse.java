package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailOngoingPengusulanResponse{

    @SerializedName("data")
    private List<DetailOngoingPengusulanItem> detailOngoingPengusulanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DetailOngoingPengusulanItem> getData(){
        return detailOngoingPengusulanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}