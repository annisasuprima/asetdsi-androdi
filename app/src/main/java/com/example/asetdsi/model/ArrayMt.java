package com.example.asetdsi.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArrayMt implements Parcelable {
    public String nama_barang_arraymt;
    public String kondisi_arraymt;
    public String permasalahan_arraymt;
    public String photo_pengusulanmt;

    protected ArrayMt(Parcel in) {
        nama_barang_arraymt = in.readString();
        kondisi_arraymt = in.readString();
        permasalahan_arraymt = in.readString();
        photo_pengusulanmt = in.readString();
        kode_arraymt = in.readString();
    }

    public static final Creator<ArrayMt> CREATOR = new Creator<ArrayMt>() {
        @Override
        public ArrayMt createFromParcel(Parcel in) {
            return new ArrayMt(in);
        }

        @Override
        public ArrayMt[] newArray(int size) {
            return new ArrayMt[size];
        }
    };

    public String getNama_barang_arraymt() {
        return nama_barang_arraymt;
    }

    public void setNama_barang_arraymt(String nama_barang_arraymt) {
        this.nama_barang_arraymt = nama_barang_arraymt;
    }

    public String getKondisi_arraymt() {
        return kondisi_arraymt;
    }

    public void setKondisi_arraymt(String kondisi_arraymt) {
        this.kondisi_arraymt = kondisi_arraymt;
    }

    public String getPermasalahan_arraymt() {
        return permasalahan_arraymt;
    }

    public void setPermasalahan_arraymt(String permasalahan_arraymt) {
        this.permasalahan_arraymt = permasalahan_arraymt;
    }

    public String getPhoto_pengusulanmt() {
        return photo_pengusulanmt;
    }

    public void setPhoto_pengusulanmt(String photo_pengusulanmt) {
        this.photo_pengusulanmt = photo_pengusulanmt;
    }

    public String getKode_arraymt() {
        return kode_arraymt;
    }

    public void setKode_arraymt(String kode_arraymt) {
        this.kode_arraymt = kode_arraymt;
    }

    public ArrayMt() {
        this.nama_barang_arraymt = nama_barang_arraymt;
        this.kondisi_arraymt = kondisi_arraymt;
        this.permasalahan_arraymt = permasalahan_arraymt;
        this.photo_pengusulanmt = photo_pengusulanmt;
        this.kode_arraymt = kode_arraymt;
    }

    public String kode_arraymt;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama_barang_arraymt);
        parcel.writeString(kondisi_arraymt);
        parcel.writeString(permasalahan_arraymt);
        parcel.writeString(photo_pengusulanmt);
        parcel.writeString(kode_arraymt);
    }

//    private ArrayList<Uri> uri;



//
//    public ArrayMt() {
//        this.nama_barang_arraymt = nama_barang_arraymt;
//        this.kondisi_arraymt = kondisi_arraymt;
//        this.permasalahan_arraymt = permasalahan_arraymt;
//    }


}
