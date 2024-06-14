package com.example.asetdsi.model;

import java.io.Serializable;

public class DetailFormPengusulanMaintenence implements Serializable {
    public String nama_barang_pengusulanmt;
    public String kondisi_pengusulanmt;
    public String permasalahan_pengusulan_mt;


    public int getInventory_item_id() {
        return inventory_item_id;
    }

    public void setInventory_item_id(int inventory_item_id) {
        this.inventory_item_id = inventory_item_id;
    }

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

    public int inventory_item_id;
    public int proposal_id;

    public int pic_id;

    public DetailFormPengusulanMaintenence() {
        this.nama_barang_pengusulanmt = nama_barang_pengusulanmt;
        this.kondisi_pengusulanmt = kondisi_pengusulanmt;
        this.permasalahan_pengusulan_mt = permasalahan_pengusulan_mt;
        this.inventory_item_id = inventory_item_id;
        this.proposal_id = proposal_id;
        this.pic_id = pic_id;
    }





    public String getNama_barang_pengusulanmt() {
        return nama_barang_pengusulanmt;
    }

    public void setNama_barang_pengusulanmt(String nama_barang_pengusulanmt) {
        this.nama_barang_pengusulanmt = nama_barang_pengusulanmt;
    }

    public String getKondisi_pengusulanmt() {
        return kondisi_pengusulanmt;
    }

    public void setKondisi_pengusulanmt(String kondisi_pengusulanmt) {
        this.kondisi_pengusulanmt = kondisi_pengusulanmt;
    }

    public String getPermasalahan_pengusulan_mt() {
        return permasalahan_pengusulan_mt;
    }

    public void setPermasalahan_pengusulan_mt(String permasalahan_pengusulan_mt) {
        this.permasalahan_pengusulan_mt = permasalahan_pengusulan_mt;
    }
}
