package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class DaftarBarangMaintenenceItem{

    @SerializedName("item_code")
    private String itemCode;

    @SerializedName("condition")
    private String condition;

    @SerializedName("asset_name")
    private String assetName;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("pic_id")
    private int picId;

    @SerializedName("inventory_id")
    private int inventoryId;

    @SerializedName("available")
    private String available;

    @SerializedName("inventory_brand")
    private String inventoryBrand;

    @SerializedName("photo")
    private String photo;

    @SerializedName("id")
    private int id;

    @SerializedName("pic_name")
    private String picName;

    public String getItemCode(){
        return itemCode;
    }

    public String getCondition(){
        return condition;
    }

    public String getAssetName(){
        return assetName;
    }

    public int getJumlah(){
        return jumlah;
    }

    public int getPicId(){
        return picId;
    }

    public int getInventoryId(){
        return inventoryId;
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

    public int getId(){
        return id;
    }

    public String getPicName(){
        return picName;
    }
}