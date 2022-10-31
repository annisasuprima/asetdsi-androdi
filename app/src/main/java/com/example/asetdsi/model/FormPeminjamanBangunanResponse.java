package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class FormPeminjamanBangunanResponse{

    @SerializedName("data")
    private FormPeminjamanBangunanItem formPeminjamanBangunanItem;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public FormPeminjamanBangunanItem getData(){
        return formPeminjamanBangunanItem;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}