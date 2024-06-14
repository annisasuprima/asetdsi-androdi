package com.example.asetdsi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListBarangMaintenence implements Parcelable {

    public String nama_brg_maintenence;

    public String getNama_brg_maintenence() {
        return nama_brg_maintenence;
    }

    public void setNama_brg_maintenence(String nama_brg_maintenence) {
        this.nama_brg_maintenence = nama_brg_maintenence;
    }

    public String getMerk_brg_maintenence() {
        return merk_brg_maintenence;
    }

    public void setMerk_brg_maintenence(String merk_brg_maintenence) {
        this.merk_brg_maintenence = merk_brg_maintenence;
    }

    public String getKondisi_brg_maintenence() {
        return kondisi_brg_maintenence;
    }

    public void setKondisi_brg_maintenence(String kondisi_brg_maintenence) {
        this.kondisi_brg_maintenence = kondisi_brg_maintenence;
    }

    public String getGambar_brg_maintenence() {
        return gambar_brg_maintenence;
    }

    public void setGambar_brg_maintenence(String gambar_brg_maintenence) {
        this.gambar_brg_maintenence = gambar_brg_maintenence;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String merk_brg_maintenence;
    public String kondisi_brg_maintenence;
    public String gambar_brg_maintenence;
    public String item_code;
    public Integer id;

    private boolean isSelected;
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public ListBarangMaintenence(){}


    public ListBarangMaintenence(String nama_brg_maintenence, String merk_brg_maintenence, String kondisi_brg_maintenence, String gambar_brg_maintenence, String item_code, Integer id) {
        this.nama_brg_maintenence = nama_brg_maintenence;
        this.merk_brg_maintenence = merk_brg_maintenence;
        this.kondisi_brg_maintenence = kondisi_brg_maintenence;
        this.gambar_brg_maintenence = gambar_brg_maintenence;
        this.item_code = item_code;
        this.id = id;
    }

    protected ListBarangMaintenence(Parcel in) {
        nama_brg_maintenence = in.readString();
        merk_brg_maintenence = in.readString();
        kondisi_brg_maintenence = in.readString();
        gambar_brg_maintenence = in.readString();
        item_code = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
    }

    public static final Creator<ListBarangMaintenence> CREATOR = new Creator<ListBarangMaintenence>() {
        @Override
        public ListBarangMaintenence createFromParcel(Parcel in) {
            return new ListBarangMaintenence(in);
        }

        @Override
        public ListBarangMaintenence[] newArray(int size) {
            return new ListBarangMaintenence[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama_brg_maintenence);
        parcel.writeString(merk_brg_maintenence);
        parcel.writeString(kondisi_brg_maintenence);
        parcel.writeString(gambar_brg_maintenence);
        parcel.writeString(item_code);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
    }
}
