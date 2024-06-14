package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PeminjamanBarangResponse{

    @SerializedName("loan_date")
    private String loanDate;


    @SerializedName("loan_description")
    private String loanDescription;

    @SerializedName("aset")
    private List<Peminjaman> peminjamanBarangItem;

    @SerializedName("loan_time")
    private String loanTime;

    @SerializedName("loan_time_end")
    private String loanTimeEnd;

    public String getLoanDate(){
        return loanDate;
    }

    public String getLoanDescription(){
        return loanDescription;
    }

    public List<Peminjaman> getAset(){
        return peminjamanBarangItem;
    }

    public String getLoanTime(){
        return loanTime;
    }

    public String getLoanTimeEnd(){
        return loanTimeEnd;
    }
}