package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class DetailHistoryPengusulanItem{

    @SerializedName("amount")
    private int amount;

    @SerializedName("asset_name")
    private String assetName;

    @SerializedName("proposal_id")
    private int proposalId;

    @SerializedName("nama_mahasiswa")
    private String namaMahasiswa;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("id")
    private int id;

    @SerializedName("spesification_detail")
    private String spesificationDetail;

    @SerializedName("mahasiswa_id")
    private int mahasiswaId;

    @SerializedName("unit_price")
    private int unitPrice;

    @SerializedName("statuspr")
    private String statuspr;

    @SerializedName("source_shop")
    private String sourceShop;

    public int getAmount(){
        return amount;
    }

    public String getAssetName(){
        return assetName;
    }

    public int getProposalId(){
        return proposalId;
    }

    public String getNamaMahasiswa(){
        return namaMahasiswa;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public int getId(){
        return id;
    }

    public String getSpesificationDetail(){
        return spesificationDetail;
    }

    public int getMahasiswaId(){
        return mahasiswaId;
    }

    public int getUnitPrice(){
        return unitPrice;
    }

    public String getStatuspr(){
        return statuspr;
    }

    public String getSourceShop(){
        return sourceShop;
    }
}