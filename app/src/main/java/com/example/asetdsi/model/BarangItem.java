package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class BarangItem{

    @SerializedName("condition")
    private String condition;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("pic_id")
    private int picId;

    @SerializedName("asset_name")
    private String assetName;

    @SerializedName("available")
    private String available;

    @SerializedName("inventory_brand")
    private String inventoryBrand;

    @SerializedName("photo")
    private String photo;

    @SerializedName("pic_name")
    private String picName;

    public String getCondition(){
        return condition;
    }

    public int getJumlah(){
        return jumlah;
    }

    public int getPicId(){
        return picId;
    }

    public String getAssetName(){
        return assetName;
    }

    public String getAvailable(){
        return available;
    }

    public String getInventoryBrand(){
        return inventoryBrand;
    }

    public String getPhoto(){
        return photo;
    }

    public String getPicName(){
        return picName;
    }
}