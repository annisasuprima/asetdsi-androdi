package com.example.asetdsi.model;

public class DetailOngoingPengusulan {

        public String nama_barang_detail_openg;
        public String detail_spesifikasi_detail_openg;
        public int jumlah_barang_detail_openg;
        public int harga_barang_detail_openg;
        public String sumber_toko_openg;


   public DetailOngoingPengusulan(){}

        public DetailOngoingPengusulan(String nama_barang_detail_openg, String detail_spesifikasi_detail_openg, int jumlah_barang_detail_openg, int harga_barang_detail_openg, String sumber_toko_openg) {
                this.nama_barang_detail_openg = nama_barang_detail_openg;
                this.detail_spesifikasi_detail_openg = detail_spesifikasi_detail_openg;
                this.jumlah_barang_detail_openg = jumlah_barang_detail_openg;
                this.harga_barang_detail_openg = harga_barang_detail_openg;
                this.sumber_toko_openg = sumber_toko_openg;
        }
}
