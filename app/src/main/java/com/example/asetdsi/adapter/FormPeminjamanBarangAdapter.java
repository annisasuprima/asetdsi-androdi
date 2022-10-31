package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.FormPeminjamanBarang;
import com.example.asetdsi.model.Peminjaman;
import com.example.asetdsi.model.Pj;

import java.util.ArrayList;

public class FormPeminjamanBarangAdapter extends RecyclerView.Adapter<FormPeminjamanBarangAdapter.FormPeminjamanBarangViewHolder> {


    ArrayList<Peminjaman> listData = new ArrayList<Peminjaman>();

    public FormPeminjamanBarangAdapter(ArrayList<Peminjaman> listData) {
        this.listData = listData;
    }

    public  class FormPeminjamanBarangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama_form_peminjaman_barang;
        TextView merk_form_peminjaman_barang;
        TextView jumlah_form_peminjaman_barang;

        public FormPeminjamanBarangViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_form_peminjaman_barang = itemView.findViewById(R.id.nama_form_peminjaman_brg);
            merk_form_peminjaman_barang = itemView.findViewById(R.id.merk_form_peminjaman_brg);
            jumlah_form_peminjaman_barang = itemView.findViewById(R.id.jumlah_form_peminjaman_brg);
//            pjCv = itemView.findViewById(R.id.pjCv);pjCv
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(view, listData.get(getAdapterPosition()));
            }
        }

    }

    //Click Listener
    OnFormPeminjamanBarangHolderClickListener listener = null;


    public interface OnFormPeminjamanBarangHolderClickListener {
        void onClick(View view,Peminjaman peminjaman);

    }


    public void setListener(OnFormPeminjamanBarangHolderClickListener listener) {
        this.listener = listener;

    }

    public void setListData(ArrayList<Peminjaman> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FormPeminjamanBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_form_peminjaman, parent,false);
        return new FormPeminjamanBarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormPeminjamanBarangViewHolder holder, int position) {

        Peminjaman peminjaman = listData.get(position);
       holder.nama_form_peminjaman_barang.setText(peminjaman.namabrg_pnj);
       holder.merk_form_peminjaman_barang.setText(peminjaman.merkbrg_pnj);
       holder.jumlah_form_peminjaman_barang.setText(Integer.toString(peminjaman.jumlahbrg_pnj));


    }

    @Override
    public int getItemCount() {

        return listData == null ? 0 : listData.size();
    }

}
