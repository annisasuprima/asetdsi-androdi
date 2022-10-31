package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OngoingPengusulanResponse{

    @SerializedName("data")
    private List<OngoingPengusulanItem> ongoingPengusulanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<OngoingPengusulanItem> getData(){
        return ongoingPengusulanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}