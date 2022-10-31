package com.example.asetdsi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.FormPeminjamanBangunan;
import com.example.asetdsi.model.FormPeminjamanBarang;

import java.util.ArrayList;

public class FormPeminjamanBangunanAdapter extends RecyclerView.Adapter<FormPeminjamanBangunanAdapter.FormPeminjamanBangunanViewHolder> {


    ArrayList<FormPeminjamanBangunan> listData = new ArrayList<FormPeminjamanBangunan>();



    public  class FormPeminjamanBangunanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama_form_peminjaman_bgn;
        ImageView gambar_form_peminjaman_bangunan;

        public FormPeminjamanBangunanViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_form_peminjaman_bgn = itemView.findViewById(R.id.nama_form_peminjaman_bgn);
            gambar_form_peminjaman_bangunan = (ImageView) itemView.findViewById(R.id.gambar_form_peminjaman_bangunan);
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
        OnFormPeminjamanBangunanHolderClickListener listener = null;

        public interface OnFormPeminjamanBangunanHolderClickListener {
            void onClick(View view,FormPeminjamanBangunan formPeminjamanBangunan);

        }

    public void setListener(FormPeminjamanBangunanAdapter.OnFormPeminjamanBangunanHolderClickListener listener) {
        this.listener = listener;
    }


        public void setListData(ArrayList<FormPeminjamanBangunan> listData) {
            this.listData = listData;
        }

    @NonNull
    @Override
    public FormPeminjamanBangunanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_form_peminjaman_bangunan, parent,false);
        return new FormPeminjamanBangunanAdapter.FormPeminjamanBangunanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormPeminjamanBangunanViewHolder holder, int position) {
        FormPeminjamanBangunan formPeminjamanBangunan = listData.get(position);
        holder.nama_form_peminjaman_bgn.setText(formPeminjamanBangunan.nama_form_peminjaman_bgn);





    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


}
