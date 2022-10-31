package com.example.asetdsi.model;

import java.util.ArrayList;
import java.util.List;

public class DetailHistoryPengusulanMaintenence {
    public String nama_barang_detail_peng_mt;
    public String kondisi_peng_mt;
    public String permasalahan_mt;
    public int id_req_maintenence;
    public boolean isExpandable;


    public DetailHistoryPengusulanMaintenence(String nama_barang_detail_peng_mt, String kondisi_peng_mt, String permasalahan_mt, int id_req_maintenence) {
        this.nama_barang_detail_peng_mt = nama_barang_detail_peng_mt;
        this.kondisi_peng_mt = kondisi_peng_mt;
        this.permasalahan_mt = permasalahan_mt;
        this.id_req_maintenence = id_req_maintenence;
        isExpandable = false;
    }


    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public String getNama_barang_detail_peng_mt() {
        return nama_barang_detail_peng_mt;
    }

    public String getKondisi_peng_mt() {
        return kondisi_peng_mt;
    }

    public String getPermasalahan_mt() {
        return permasalahan_mt;
    }


    public boolean isExpandable() {
        return isExpandable;
    }
}


