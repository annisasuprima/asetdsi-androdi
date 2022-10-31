package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class ListPeminjamanBangunanItem{

    @SerializedName("building_name")
    private String buildingName;

    @SerializedName("building_id")
    private int buildingId;

    @SerializedName("asset_name")
    private String assetName;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("pic_id")
    private int picId;

    @SerializedName("available")
    private String available;

    @SerializedName("photo")
    private String photo;

    @SerializedName("pic_name")
    private String picName;

    public String getBuildingName(){
        return buildingName;
    }

    public int getBuildingId(){
        return buildingId;
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

    public String getAvailable(){
        return available;
    }

    public String getPhoto(){
        return photo;
    }

    public String getPicName(){
        return picName;
    }
}