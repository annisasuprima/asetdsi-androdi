package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class DetailOngoingPengusulanMaintenenceItem{

    @SerializedName("problem_description")
    private String problemDescription;

    @SerializedName("id_req_maintenence")
    private int idReqMaintenence;

    @SerializedName("item_code")
    private String itemCode;

    @SerializedName("condition")
    private String condition;

    @SerializedName("proposal_id")
    private int proposalId;

    @SerializedName("nama_mahasiswa")
    private String namaMahasiswa;

    @SerializedName("inventory_item_id")
    private int inventoryItemId;

    @SerializedName("inventory_brand")
    private String inventoryBrand;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("id")
    private int id;

    @SerializedName("mahasiswa_id")
    private int mahasiswaId;

    @SerializedName("statuspr")
    private String statuspr;

    public String getProblemDescription(){
        return problemDescription;
    }

    public int getIdReqMaintenence(){
        return idReqMaintenence;
    }

    public String getItemCode(){
        return itemCode;
    }

    public String getCondition(){
        return condition;
    }

    public int getProposalId(){
        return proposalId;
    }

    public String getNamaMahasiswa(){
        return namaMahasiswa;
    }

    public int getInventoryItemId(){
        return inventoryItemId;
    }

    public String getInventoryBrand(){
        return inventoryBrand;
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