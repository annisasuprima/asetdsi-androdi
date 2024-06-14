package com.example.asetdsi.model;

import com.google.gson.annotations.SerializedName;

public class FormPeminjamanBangunanItem{

    @SerializedName("loan_date")
    private String loanDate;

    @SerializedName("building_id")
    private String buildingId;

    @SerializedName("pic_id")
    private String picId;

    @SerializedName("type_id")
    private int typeId;

    @SerializedName("loan_description")
    private String loanDescription;

    @SerializedName("photo")
    private String photo;

    @SerializedName("mahasiswa_id")
    private int mahasiswaId;

    @SerializedName("loan_time")
    private String loanTime;

    @SerializedName("loan_time_end")
    private String loanTimeEnd;

    @SerializedName("status")
    private String status;

    @SerializedName("loan_id")
    private int loanId;

    public String getLoanDate(){
        return loanDate;
    }

    public String getBuildingId(){
        return buildingId;
    }

    public String getPicId(){
        return picId;
    }

    public int getTypeId(){
        return typeId;
    }

    public String getLoanDescription(){
        return loanDescription;
    }

    public String getPhoto(){
        return photo;
    }

    public int getMahasiswaId(){
        return mahasiswaId;
    }

    public String getLoanTime(){
        return loanTime;
    }
    public String getLoanTimeEnd(){
        return loanTimeEnd;
    }

    public String getStatus(){
        return status;
    }

    public int getLoanId(){
        return loanId;
    }
}