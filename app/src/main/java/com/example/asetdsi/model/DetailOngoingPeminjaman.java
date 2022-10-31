package com.example.asetdsi.model;

public class DetailOngoingPeminjaman {
    public String nama_barang_detail_op;
    public String merk_barang_detail_op;
    public int jumlah_barang_detail_op;

    public DetailOngoingPeminjaman(){}


    public DetailOngoingPeminjaman(String nama_barang_detail_op, String merk_barang_detail_op, int jumlah_barang_detail_op) {
        this.nama_barang_detail_op = nama_barang_detail_op;
        this.merk_barang_detail_op = merk_barang_detail_op;
        this.jumlah_barang_detail_op = jumlah_barang_detail_op;
    }
}
