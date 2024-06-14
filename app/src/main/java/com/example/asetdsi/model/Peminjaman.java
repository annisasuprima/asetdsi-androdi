package com.example.asetdsi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Peminjaman implements Parcelable{


    protected Peminjaman(Parcel in) {
        namabrg_pnj = in.readString();
        merkbrg_pnj = in.readString();
        if (in.readByte() == 0) {
            jumlahbrg_pnj = null;
        } else {
            jumlahbrg_pnj = in.readInt();
        }
        gambarbrg_pnj = in.readString();
        if (in.readByte() == 0) {
            value_jumlah = null;
        } else {
            value_jumlah = in.readInt();
        }
        persediaan = in.readInt();
        inventory_id = in.readInt();
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namabrg_pnj);
        dest.writeString(merkbrg_pnj);
        if (jumlahbrg_pnj == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(jumlahbrg_pnj);
        }
        dest.writeString(gambarbrg_pnj);
        if (value_jumlah == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(value_jumlah);
        }
        dest.writeInt(persediaan);
        dest.writeInt(inventory_id);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Peminjaman> CREATOR = new Creator<Peminjaman>() {
        @Override
        public Peminjaman createFromParcel(Parcel in) {
            return new Peminjaman(in);
        }

        @Override
        public Peminjaman[] newArray(int size) {
            return new Peminjaman[size];
        }
    };

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

    public Peminjaman(String namabrg_pnj, String merkbrg_pnj, Integer jumlahbrg_pnj, String gambarbrg_pnj, int inventory_id) {
        this.namabrg_pnj = namabrg_pnj;
        this.merkbrg_pnj = merkbrg_pnj;
        this.jumlahbrg_pnj = jumlahbrg_pnj;
        this.gambarbrg_pnj = gambarbrg_pnj;
        this.inventory_id = inventory_id;
    }

    public String namabrg_pnj;
    public String merkbrg_pnj;
    public Integer jumlahbrg_pnj;
    public String gambarbrg_pnj;
    public Integer value_jumlah;
    public int persediaan;
    public int inventory_id;
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



    public void value_jumlah(String toString) {
    }



}
