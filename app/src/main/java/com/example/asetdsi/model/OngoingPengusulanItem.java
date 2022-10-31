package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class OngoingPengusulanItem{

    @SerializedName("nama_mahasiswa")
    private String namaMahasiswa;

    @SerializedName("type_id")
    private int typeId;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("id")
    private int id;

    @SerializedName("mahasiswa_id")
    private int mahasiswaId;

    @SerializedName("statuspr")
    private String statuspr;

    public String getNamaMahasiswa(){
        return namaMahasiswa;
    }

    public int getTypeId(){
        return typeId;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public int getId(){
        return id;
    }

    public int getMahasiswaId(){
        return mahasiswaId;
    }

    public String getStatuspr(){
        return statuspr;
    }
}