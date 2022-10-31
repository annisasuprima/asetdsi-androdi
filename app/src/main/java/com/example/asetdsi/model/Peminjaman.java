package com.example.asetdsi.model;

import java.io.Serializable;

public class Peminjaman implements Serializable {
    public String getNamabrg_pnj() {
        return namabrg_pnj;
    }

    public void setNamabrg_pnj(String namabrg_pnj) {
        this.namabrg_pnj = namabrg_pnj;
    }

    public String getMerkbrg_pnj() {
        return merkbrg_pnj;
    }

    public void setMerkbrg_pnj(String merkbrg_pnj) {
        this.merkbrg_pnj = merkbrg_pnj;
    }

    public Integer getJumlahbrg_pnj() {
        return jumlahbrg_pnj;
    }

    public void setJumlahbrg_pnj(Integer jumlahbrg_pnj) {
        this.jumlahbrg_pnj = jumlahbrg_pnj;
    }

    public String getGambarbrg_pnj() {
        return gambarbrg_pnj;
    }

    public void setGambarbrg_pnj(String gambarbrg_pnj) {
        this.gambarbrg_pnj = gambarbrg_pnj;
    }

    public Integer getValue_jumlah() {
        return value_jumlah;
    }

    public void setValue_jumlah(Integer value_jumlah) {
        this.value_jumlah = value_jumlah;
    }

    public String namabrg_pnj;
    public String merkbrg_pnj;
    public Integer jumlahbrg_pnj;
    public String gambarbrg_pnj;
    public Integer value_jumlah;
    private boolean isSelected;
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean getSelected() {
        return isSelected;
    }
    public Peminjaman(){}


    public Peminjaman(String namabrg_pnj, String merkbrg_pnj, Integer jumlahbrg_pnj, String gambarbrg_pnj) {
        this.namabrg_pnj = namabrg_pnj;
        this.merkbrg_pnj = merkbrg_pnj;
        this.jumlahbrg_pnj = jumlahbrg_pnj;
        this.gambarbrg_pnj = gambarbrg_pnj;
    }

    public void value_jumlah(String toString) {
    }
}
