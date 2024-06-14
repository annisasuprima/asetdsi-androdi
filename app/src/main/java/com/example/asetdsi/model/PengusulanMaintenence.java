package com.example.asetdsi.model;

import java.io.Serializable;

public class PengusulanMaintenence implements Serializable {

    public PengusulanMaintenence() {
        this.deskripsi_permasalahan = deskripsi_permasalahan;
        this.inventory_item_id = inventory_item_id;
        this.proposal_id = proposal_id;
        this.pic_id = pic_id;
    }

    public String getDeskripsi_permasalahan() {
        return deskripsi_permasalahan;
    }

    public void setDeskripsi_permasalahan(String deskripsi_permasalahan) {
        this.deskripsi_permasalahan = deskripsi_permasalahan;
    }

    public int getInventory_item_id() {
        return inventory_item_id;
    }

    public void setInventory_item_id(int inventory_item_id) {
        this.inventory_item_id = inventory_item_id;
    }

    public int getProposal_id() {
        return proposal_id;
    }

    public void setProposal_id(int proposal_id) {
        this.proposal_id = proposal_id;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public String deskripsi_permasalahan;
    public int inventory_item_id;
    public int proposal_id;
    public int pic_id;
}
