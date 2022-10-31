package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class PJPengusulanItem{

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("pic_name")
    private String picName;

    @SerializedName("username")
    private String username;

    public String getUpdatedAt(){
        return updatedAt;
    }

    public int getJumlah(){
        return jumlah;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getPicName(){
        return picName;
    }

    public String getUsername(){
        return username;
    }
}