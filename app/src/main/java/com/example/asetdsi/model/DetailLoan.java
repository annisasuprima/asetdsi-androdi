package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailLoan{

    @SerializedName("barang")
    private List<BarangItem> barang;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("inventory_item_id")
    private int inventoryItemId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("loan_id")
    private int loanId;

    public List<BarangItem> getBarang(){
        return barang;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public int getInventoryItemId(){
        return inventoryItemId;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public int getId(){
        return id;
    }

    public int getLoanId(){
        return loanId;
    }
}