package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class PengusulanBarangItem{

    @SerializedName("amount")
    private int amount;

    @SerializedName("asset_name")
    private String assetName;

    @SerializedName("spesification_detail")
    private String spesificationDetail;

    @SerializedName("unit_price")
    private int unitPrice;

    @SerializedName("source_shop")
    private String sourceShop;

    @SerializedName("proposal_id")
    private String proposalId;

    @SerializedName("pic_id")
    private String picId;



    public PengusulanBarangItem(int amount, String assetName, String spesificationDetail, int unitPrice, String sourceShop, String proposalId, String picId) {
        this.amount = amount;
        this.assetName = assetName;
        this.spesificationDetail = spesificationDetail;
        this.unitPrice = unitPrice;
        this.sourceShop = sourceShop;
        this.proposalId = proposalId;
        this.picId = picId;
    }


    public int getAmount(){
        return amount;
    }

    public String getAssetName(){
        return assetName;
    }

    public String getSpesificationDetail(){
        return spesificationDetail;
    }

    public int getUnitPrice(){
        return unitPrice;
    }

    public String getSourceShop(){
        return sourceShop;
    }

    public String getProposalId() {
        return proposalId;
    }

    public String getPicId() {
        return picId;
    }
}