package com.example.asetdsi.model;

public class DetailHistoryPeminjaman {
    public String nama_barang_detail_hp;
    public String merk_barang_detail_hp;
    public int jumlah_barang_detail_hp;
    public String status_detail_history_peminjaman;


    public DetailHistoryPeminjaman(String nama_barang_detail_hp, String merk_barang_detail_hp, int jumlah_barang_detail_hp, String status_detail_history_peminjaman) {
        this.nama_barang_detail_hp = nama_barang_detail_hp;
        this.merk_barang_detail_hp = merk_barang_detail_hp;
        this.jumlah_barang_detail_hp = jumlah_barang_detail_hp;
        this.status_detail_history_peminjaman = status_detail_history_peminjaman;
    }

  ;

    public DetailHistoryPeminjaman(){}



}

