package com.example.asetdsi.model;

import com.example.asetdsi.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class PengusulanBarang implements Serializable {

    public String nama_pengusulan_barang;
    public String detail_spesifikasi_pengusulan_barang;
    public Integer jumlah_pengusulan_barang;
    public Integer harga_pengusulan_barang;
    public String sumber_pengusulan_barang;
    public int proposal_id;
    public int pic_id;

    public int getProposal_id() {
        return proposal_id;
    }

    public void setProposal_id(int proposal_id) {
        this.proposal_id = proposal_id;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public PengusulanBarang() {
        this.nama_pengusulan_barang = nama_pengusulan_barang;
        this.detail_spesifikasi_pengusulan_barang = detail_spesifikasi_pengusulan_barang;
        this.jumlah_pengusulan_barang = jumlah_pengusulan_barang;
        this.harga_pengusulan_barang = harga_pengusulan_barang;
        this.sumber_pengusulan_barang = sumber_pengusulan_barang;
        this.proposal_id = proposal_id;
        this.pic_id = pic_id;
    }


    public String getNama_pengusulan_barang() {
        return nama_pengusulan_barang;
    }

    public void setNama_pengusulan_barang(String nama_pengusulan_barang) {
        this.nama_pengusulan_barang = nama_pengusulan_barang;
    }

    public String getDetail_spesifikasi_pengusulan_barang() {
        return detail_spesifikasi_pengusulan_barang;
    }

    public void setDetail_spesifikasi_pengusulan_barang(String detail_spesifikasi_pengusulan_barang) {
        this.detail_spesifikasi_pengusulan_barang = detail_spesifikasi_pengusulan_barang;
    }

    public Integer getJumlah_pengusulan_barang() {
        return jumlah_pengusulan_barang;
    }

    public void setJumlah_pengusulan_barang(Integer jumlah_pengusulan_barang) {
        this.jumlah_pengusulan_barang = jumlah_pengusulan_barang;
    }

    public Integer getHarga_pengusulan_barang() {
        return harga_pengusulan_barang;
    }

    public void setHarga_pengusulan_barang(Integer harga_pengusulan_barang) {
        this.harga_pengusulan_barang = harga_pengusulan_barang;
    }

    public String getSumber_pengusulan_barang() {
        return sumber_pengusulan_barang;
    }

    public void setSumber_pengusulan_barang(String sumber_pengusulan_barang) {
        this.sumber_pengusulan_barang = sumber_pengusulan_barang;
    }




}

