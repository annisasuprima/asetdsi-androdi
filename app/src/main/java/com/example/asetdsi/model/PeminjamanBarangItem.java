package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class PeminjamanBarangItem{

    @SerializedName("amount")
    private int amount;

    @SerializedName("asset_name")
    private String assetName;

    @SerializedName("inventory_id")
    private int inventoryId;

    @SerializedName("inventory_brand")
    private String inventoryBrand;

    public int getAmount(){
        return amount;
    }

    public String getAssetName(){
        return assetName;
    }

    public int getInventoryId(){
        return inventoryId;
    }

    public String getInventoryBrand(){
        return inventoryBrand;
    }
}