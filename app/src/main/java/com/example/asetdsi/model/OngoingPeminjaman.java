package com.example.asetdsi.model;

public class OngoingPeminjaman {
    public String keterangan_ongoing;
    public String jam_ongoing;
    public String jam_ongoing_end;
    public String tanggal_ongoing;
    public String status_ongoing;
    public int type_id;

    public int id;

    public OngoingPeminjaman(String keterangan_ongoing, String jam_ongoing, String jam_ongoing_end, String tanggal_ongoing, String status_ongoing, int type_id, int id) {
        this.keterangan_ongoing = keterangan_ongoing;
        this.jam_ongoing = jam_ongoing;
        this.jam_ongoing_end = jam_ongoing_end;
        this.tanggal_ongoing = tanggal_ongoing;
        this.status_ongoing = status_ongoing;
        this.type_id = type_id;
        this.id = id;
    }


    public OngoingPeminjaman(){}


}
