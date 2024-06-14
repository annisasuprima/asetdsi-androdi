package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class OngoingPeminjamanItem{

    @SerializedName("nama_mahasiswa")
    private String namaMahasiswa;

    @SerializedName("type_id")
    private int typeId;

    @SerializedName("waktu")
    private String waktu;

    @SerializedName("waktu_akhir")
    private String waktuAkhir;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("id")
    private int id;

    @SerializedName("mahasiswa_id")
    private int mahasiswaId;

    @SerializedName("status")
    private String status;

    public String getNamaMahasiswa(){
        return namaMahasiswa;
    }

    public int getTypeId(){
        return typeId;
    }

    public String getWaktu(){
        return waktu;
    }

    public String getWaktuAkhir(){
        return waktuAkhir;
    }

    public String getTanggal(){
        return tanggal;
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

    public String getStatus(){
        return status;
    }
}