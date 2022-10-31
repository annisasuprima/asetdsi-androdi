package com.example.asetdsi.model;

public class DetailFormPengusulanBarang {
    public String nama_barang_detail_pengusulan;
    public String detail_spesifikasi_pengusulan;
    public String sumber_toko_pengusulan;
    public Integer jumlah_barang_detail_pengusulan;
    public Integer harga_barang_detail_pengusulan;
    public Integer total_detail_pengusulan;

    public DetailFormPengusulanBarang(){}


    public DetailFormPengusulanBarang(String nama_barang_detail_pengusulan, String detail_spesifikasi_pengusulan, String sumber_toko_pengusulan, Integer jumlah_barang_detail_pengusulan, Integer harga_barang_detail_pengusulan, Integer total_detail_pengusulan) {
        this.nama_barang_detail_pengusulan = nama_barang_detail_pengusulan;
        this.detail_spesifikasi_pengusulan = detail_spesifikasi_pengusulan;
        this.sumber_toko_pengusulan = sumber_toko_pengusulan;
        this.jumlah_barang_detail_pengusulan = jumlah_barang_detail_pengusulan;
        this.harga_barang_detail_pengusulan = harga_barang_detail_pengusulan;
        this.total_detail_pengusulan = total_detail_pengusulan;
    }
}
