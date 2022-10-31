package com.example.asetdsi.model;

public class HistoryPengusulan {

    public String keterangan_history_pengusulan;
    public String status_history_pengusulan;
    public int type_id;
    public int id;
    public  HistoryPengusulan(){}


    public HistoryPengusulan(String keterangan_history_pengusulan, String status_history_pengusulan, int type_id, int id) {
        this.keterangan_history_pengusulan = keterangan_history_pengusulan;
        this.status_history_pengusulan = status_history_pengusulan;
        this.type_id = type_id;
        this.id = id;
    }
}
