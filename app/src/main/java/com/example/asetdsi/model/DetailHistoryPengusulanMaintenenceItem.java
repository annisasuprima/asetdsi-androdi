package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailHistoryPengusulanMaintenenceItem{

    @SerializedName("problem_description")
    private String problemDescription;

    @SerializedName("item_code")
    private String itemCode;

    @SerializedName("proposal_id")
    private int proposalId;

    @SerializedName("nama_mahasiswa")
    private String namaMahasiswa;

    @SerializedName("inventory_brand")
    private String inventoryBrand;

    @SerializedName("statuspr")
    private String statuspr;

    @SerializedName("photos")
    private List<PhotosItem> photos;

    @SerializedName("id_req_maintenence")
    private int idReqMaintenence;

    @SerializedName("condition")
    private String condition;

    @SerializedName("inventory_item_id")
    private int inventoryItemId;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("id")
    private int id;

    @SerializedName("mahasiswa_id")
    private int mahasiswaId;

    public String getProblemDescription(){
        return problemDescription;
    }

    public String getItemCode(){
        return itemCode;
    }

    public int getProposalId(){
        return proposalId;
    }

    public String getNamaMahasiswa(){
        return namaMahasiswa;
    }

    public String getInventoryBrand(){
        return inventoryBrand;
    }

    public String getStatuspr(){
        return statuspr;
    }

    public List<PhotosItem> getPhotos(){
        return photos;
    }

    public int getIdReqMaintenence(){
        return idReqMaintenence;
    }

    public String getCondition(){
        return condition;
    }

    public int getInventoryItemId(){
        return inventoryItemId;
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
}