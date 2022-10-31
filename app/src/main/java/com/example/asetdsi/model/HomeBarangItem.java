package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class HomeBarangItem{

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("inventory_brand")
    private String inventoryBrand;

    @SerializedName("photo")
    private String photo;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("asset_id")
    private int assetId;

    public String getUpdatedAt(){
        return updatedAt;
    }

    public String getInventoryBrand(){
        return inventoryBrand;
    }

    public String getPhoto(){
        return photo;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public int getId(){
        return id;
    }

    public int getAssetId(){
        return assetId;
    }
}