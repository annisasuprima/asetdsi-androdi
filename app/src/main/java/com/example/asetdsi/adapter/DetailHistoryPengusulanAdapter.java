package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.DetailHistoryPengusulan;

import java.util.ArrayList;
import java.util.Objects;

public class DetailHistoryPengusulanAdapter extends RecyclerView.Adapter<DetailHistoryPengusulanAdapter.DetailHistoryPengusulanViewHolder> {


    ArrayList<DetailHistoryPengusulan> listData = new ArrayList<DetailHistoryPengusulan>();



    public class DetailHistoryPengusulanViewHolder extends RecyclerView.ViewHolder{
        TextView nama_barang_detail_peng;
        TextView detail_spesifikasi_detail_peng;
        TextView jumlah_barang_detail_peng;
        TextView harga_barang_detail_peng;
        TextView sumber_toko_peng;
        TextView status_detail_hpengusulan;
        TextView status_detail_hpengusulan_fakultas;


        public DetailHistoryPengusulanViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_detail_peng = itemView.findViewById(R.id.nama_barang_detail_peng);
            detail_spesifikasi_detail_peng = itemView.findViewById(R.id.detail_spesifikasi_detail_peng);
            jumlah_barang_detail_peng=itemView.findViewById(R.id.jumlah_barang_detail_peng);
            harga_barang_detail_peng=itemView.findViewById(R.id.harga_barang_detail_peng);
            sumber_toko_peng=itemView.findViewById(R.id.sumber_toko_peng);
            status_detail_hpengusulan=itemView.findViewById(R.id.status_detail_hpengusulan);
            status_detail_hpengusulan_fakultas=itemView.findViewById(R.id.status_detail_hpengusulan_fakultas);

        }
    }

    public void setListData(ArrayList<DetailHistoryPengusulan> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailHistoryPengusulanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_history_pengusulan, parent,false);
        return new DetailHistoryPengusulanAdapter.DetailHistoryPengusulanViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DetailHistoryPengusulanViewHolder holder, int position) {
        DetailHistoryPengusulan detailHistoryPengusulan = listData.get(position);
        holder.nama_barang_detail_peng.setText(detailHistoryPengusulan.nama_barang_detail_peng);
        holder.detail_spesifikasi_detail_peng.setText(detailHistoryPengusulan.detail_spesifikasi_detail_peng);
        holder.jumlah_barang_detail_peng.setText(Integer.toString(detailHistoryPengusulan.jumlah_barang_detail_peng));
        holder.harga_barang_detail_peng.setText(Integer.toString(detailHistoryPengusulan.harga_barang_detail_peng));
        holder.sumber_toko_peng.setText(detailHistoryPengusulan.sumber_toko_peng);

        holder.status_detail_hpengusulan.setText(detailHistoryPengusulan.status_detail_hpengusulan);
        if(Objects.equals(detailHistoryPengusulan.status_detail_hpengusulan,"accepted")){
            holder.status_detail_hpengusulan.setBackgroundResource(R.drawable.badge_ijau);
        }else if(Objects.equals(detailHistoryPengusulan.status_detail_hpengusulan,"rejected")){
            holder.status_detail_hpengusulan.setBackgroundResource(R.drawable.badge_merah);
        }else
        {
            holder.status_detail_hpengusulan.setBackgroundResource(R.drawable.badge);
        }

        holder.status_detail_hpengusulan_fakultas.setText(detailHistoryPengusulan.status_detail_hpengusulan_fakultas);
        if(Objects.equals(detailHistoryPengusulan.status_detail_hpengusulan_fakultas,"accepted")){
            holder.status_detail_hpengusulan_fakultas.setBackgroundResource(R.drawable.badge_ijau);
        }else if(Objects.equals(detailHistoryPengusulan.status_detail_hpengusulan_fakultas,"rejected")){
            holder.status_detail_hpengusulan_fakultas.setBackgroundResource(R.drawable.badge_merah);
        }else
        {
            holder.status_detail_hpengusulan_fakultas.setBackgroundResource(R.drawable.badge);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }



}
