package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class OngoingPengusulanItem{

    @SerializedName("type_id")
    private int typeId;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("id")
    private int id;

    @SerializedName("statuspr")
    private String statuspr;

    @SerializedName("status_confirm_faculty")
    private String statusconfirmfaculty;

    public int getTypeId(){
        return typeId;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public int getId(){
        return id;
    }

    public String getStatuspr(){
        return statuspr;
    }
    public String getStatusconfirmfaculty(){
        return statusconfirmfaculty;
    }
}