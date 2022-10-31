package com.example.asetdsi.model;

import android.widget.ImageView;

public class Barang {
    public String nama_brg;
    public String nama_pjbrg;
    public String merk_brg;
    public String availablebrg;
    public Integer jumlah;
    public String gambar_brg;

    public Barang(){}


    public Barang(String nama_brg, String nama_pjbrg, String merk_brg, String availablebrg, Integer jumlah, String gambar_brg) {
        this.nama_brg = nama_brg;
        this.nama_pjbrg = nama_pjbrg;
        this.merk_brg = merk_brg;
        this.availablebrg = availablebrg;
        this.jumlah = jumlah;
        this.gambar_brg = gambar_brg;
    }
}
