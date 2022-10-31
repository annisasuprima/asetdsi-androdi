package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PJPengusulanResponse{

    @SerializedName("data")
    private List<PJPengusulanItem> pjPengusulanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<PJPengusulanItem> getData(){
        return pjPengusulanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}