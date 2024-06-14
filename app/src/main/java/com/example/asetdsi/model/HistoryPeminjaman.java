package com.example.asetdsi.model;

public class HistoryPeminjaman {
    public HistoryPeminjaman(String keterangan_history, String jam_history, String jam_history_end, String tanggal_history, String alasan, String status_history, int type_id, int id) {
        this.keterangan_history = keterangan_history;
        this.jam_history = jam_history;
        this.jam_history_end = jam_history_end;
        this.tanggal_history = tanggal_history;
        this.alasan = alasan;
        this.status_history = status_history;
        this.type_id = type_id;
        this.id = id;
    }

    public String keterangan_history;
    public String jam_history;
    public String jam_history_end;
    public String tanggal_history;
    public String alasan;

    public String status_history;
    public int type_id;
    public int id;




    public HistoryPeminjaman(){}



}
