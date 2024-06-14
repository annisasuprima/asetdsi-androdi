package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.DetailHistoryPeminjaman;

import java.util.ArrayList;
import java.util.Objects;

public class DetailHistoryPeminjamanAdapter extends RecyclerView.Adapter<DetailHistoryPeminjamanAdapter.DetailHistoryPeminjamanViewHolder> {



    ArrayList<DetailHistoryPeminjaman> listData = new ArrayList<DetailHistoryPeminjaman>();

    public class DetailHistoryPeminjamanViewHolder extends RecyclerView.ViewHolder{
        TextView nama_barang_detail_hp;
        TextView merk_barang_detail_hp;
        TextView jumlah_barang_detail_hp;
        TextView status_detail_history_peminjaman;

        public DetailHistoryPeminjamanViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_detail_hp = itemView.findViewById(R.id.nama_barang_detail_hp);
            merk_barang_detail_hp = itemView.findViewById(R.id.merk_barang_detail_hp);
            jumlah_barang_detail_hp=itemView.findViewById(R.id.jumlah_barang_detail_hp);
            status_detail_history_peminjaman=itemView.findViewById(R.id.status_detail_history_peminjaman);

        }
    }
    public void setListData(ArrayList<DetailHistoryPeminjaman> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailHistoryPeminjamanAdapter.DetailHistoryPeminjamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_history_peminjaman, parent,false);
        return new DetailHistoryPeminjamanAdapter.DetailHistoryPeminjamanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHistoryPeminjamanViewHolder holder, int position) {
        DetailHistoryPeminjaman detailHistoryPeminjaman = listData.get(position);
        holder.nama_barang_detail_hp.setText(detailHistoryPeminjaman.nama_barang_detail_hp);
        holder.merk_barang_detail_hp.setText(detailHistoryPeminjaman.merk_barang_detail_hp);
        holder.jumlah_barang_detail_hp.setText(Integer.toString(detailHistoryPeminjaman.jumlah_barang_detail_hp));
        holder.status_detail_history_peminjaman.setText(detailHistoryPeminjaman.status_detail_history_peminjaman);
        if(Objects.equals(detailHistoryPeminjaman.status_detail_history_peminjaman, "accepted")){
            holder.status_detail_history_peminjaman.setBackgroundResource(R.drawable.badge_ijau);
        }else{
            holder.status_detail_history_peminjaman.setBackgroundResource(R.drawable.badge_merah);
            //            holder.status_history.setBackgroundColor(Color.parseColor("#dc3545"));
        }

    }


    @Override
    public int getItemCount() {

        return listData.size();
    }

}
