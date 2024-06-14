package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class DetailHistoryPeminjamanItem{

    @SerializedName("kondisi")
    private String kondisi;

    @SerializedName("nama_mahasiswa")
    private String namaMahasiswa;

    @SerializedName("type_id")
    private int typeId;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("statuspj")
    private String statuspj;

    @SerializedName("status_pj")
    private String status_pj;

    @SerializedName("nama_aset")
    private String namaAset;

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

    @SerializedName("merk_barang")
    private String merkBarang;

    @SerializedName("loan_id")
    private int loanId;

    public String getKondisi(){
        return kondisi;
    }

    public String getNamaMahasiswa(){
        return namaMahasiswa;
    }

    public int getTypeId(){
        return typeId;
    }

    public int getJumlah(){
        return jumlah;
    }

    public String getStatuspj(){
        return statuspj;
    }
    public String getStatus_pj(){
        return status_pj;
    }

    public String getNamaAset(){
        return namaAset;
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

    public String getMerkBarang(){
        return merkBarang;
    }

    public int getLoanId(){
        return loanId;
    }
}