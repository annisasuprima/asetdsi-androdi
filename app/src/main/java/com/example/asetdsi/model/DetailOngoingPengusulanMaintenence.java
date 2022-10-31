package com.example.asetdsi.model;

public class DetailOngoingPengusulanMaintenence {

    public String nama_barang_detail_openg_mt;
    public String kondisi_openg_mt;
    public String permasalahan_openg_mt;
    public int id_req_maintenence;


    public DetailOngoingPengusulanMaintenence(String nama_barang_detail_openg_mt, String kondisi_openg_mt, String permasalahan_openg_mt, int id_req_maintenence) {
        this.nama_barang_detail_openg_mt = nama_barang_detail_openg_mt;
        this.kondisi_openg_mt = kondisi_openg_mt;
        this.permasalahan_openg_mt = permasalahan_openg_mt;
        this.id_req_maintenence = id_req_maintenence;
    }
}
