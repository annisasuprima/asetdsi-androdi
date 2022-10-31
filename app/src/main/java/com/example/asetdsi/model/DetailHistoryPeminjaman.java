package com.example.asetdsi.model;

public class DetailHistoryPeminjaman {
    public String nama_barang_detail_hp;
    public String merk_barang_detail_hp;
    public int jumlah_barang_detail_hp;

    public DetailHistoryPeminjaman(){}


    public DetailHistoryPeminjaman(String nama_barang_detail_hp, String merk_barang_detail_hp, int jumlah_barang_detail_hp) {
        this.nama_barang_detail_hp = nama_barang_detail_hp;
        this.merk_barang_detail_hp = merk_barang_detail_hp;
        this.jumlah_barang_detail_hp = jumlah_barang_detail_hp;
    }
}

