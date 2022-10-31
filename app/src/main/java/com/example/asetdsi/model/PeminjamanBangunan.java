package com.example.asetdsi.model;

public class PeminjamanBangunan {




    public String nama_bgn_pnj;
    public String status_bgn_pnj;
    public String gambarbgn_pnj;
    public int building_id;
    public int pic_id;

    public PeminjamanBangunan(String nama_bgn_pnj, String status_bgn_pnj, String gambarbgn_pnj, int building_id, int pic_id) {
        this.nama_bgn_pnj = nama_bgn_pnj;
        this.status_bgn_pnj = status_bgn_pnj;
        this.gambarbgn_pnj = gambarbgn_pnj;
        this.building_id = building_id;
        this.pic_id = pic_id;
    }


    private boolean isSelected;
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }




}
