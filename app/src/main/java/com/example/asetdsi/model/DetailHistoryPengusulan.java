package com.example.asetdsi.model;

public class DetailHistoryPengusulan {
    public String nama_barang_detail_peng;
    public String detail_spesifikasi_detail_peng;
    public int jumlah_barang_detail_peng;
    public int harga_barang_detail_peng;
    public String sumber_toko_peng;


    public DetailHistoryPengusulan(){}


    public DetailHistoryPengusulan(String nama_barang_detail_peng, String detail_spesifikasi_detail_peng, int jumlah_barang_detail_peng, int harga_barang_detail_peng, String sumber_toko_peng) {
        this.nama_barang_detail_peng = nama_barang_detail_peng;
        this.detail_spesifikasi_detail_peng = detail_spesifikasi_detail_peng;
        this.jumlah_barang_detail_peng = jumlah_barang_detail_peng;
        this.harga_barang_detail_peng = harga_barang_detail_peng;
        this.sumber_toko_peng = sumber_toko_peng;
    }
}
