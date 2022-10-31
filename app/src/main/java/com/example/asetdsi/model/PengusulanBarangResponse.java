package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PengusulanBarangResponse{

    @SerializedName("aset")
    private List<PengusulanBarang> pengusulanBarangItem;

    @SerializedName("proposal_description")
    private String proposalDescription;

    public List<PengusulanBarang> getData(){
        return pengusulanBarangItem;
    }

    public String getProposalDescription(){
        return proposalDescription;
    }
}