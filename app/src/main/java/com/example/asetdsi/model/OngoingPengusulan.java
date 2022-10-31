package com.example.asetdsi.model;

public class OngoingPengusulan {
    public String ket_op;
    public String status_ongoing_pengusulan;
    public int type_id;
    public int id;

    public OngoingPengusulan(){}


    public OngoingPengusulan(String ket_op, String status_ongoing_pengusulan, int type_id, int id) {
        this.ket_op = ket_op;
        this.status_ongoing_pengusulan = status_ongoing_pengusulan;
        this.type_id = type_id;
        this.id = id;
    }
}
