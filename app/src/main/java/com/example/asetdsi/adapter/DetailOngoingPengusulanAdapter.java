package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.DetailOngoingPengusulan;

import java.util.ArrayList;

public class DetailOngoingPengusulanAdapter extends RecyclerView.Adapter<DetailOngoingPengusulanAdapter.DetailOngoingPengusulanViewHolder> {



    ArrayList<DetailOngoingPengusulan> listData = new ArrayList<DetailOngoingPengusulan>();

    public class DetailOngoingPengusulanViewHolder extends RecyclerView.ViewHolder{
        TextView nama_barang_detail_openg;
        TextView detail_spesifikasi_detail_openg;
        TextView jumlah_barang_detail_openg;
        TextView harga_barang_detail_openg;
        TextView sumber_toko_openg;
        TextView status_detail_opengusulan;
        TextView status_detail_opengusulan_fakultas;

        public DetailOngoingPengusulanViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_detail_openg = itemView.findViewById(R.id.nama_barang_detail_openg);
            detail_spesifikasi_detail_openg = itemView.findViewById(R.id.detail_spesifikasi_detail_openg);
            jumlah_barang_detail_openg=itemView.findViewById(R.id.jumlah_barang_detail_openg);
            harga_barang_detail_openg=itemView.findViewById(R.id.harga_barang_detail_openg);
            sumber_toko_openg=itemView.findViewById(R.id.sumber_toko_openg);
//            status_detail_opengusulan = itemView.findViewById(R.id.status_detail_opengusulan);
//            status_detail_opengusulan_fakultas= itemView.findViewById(R.id.status_detail_opengusulan_fakultas);

        }
    }

    public void setListData(ArrayList<DetailOngoingPengusulan> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailOngoingPengusulanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_ongoing_pengusulan,parent,false);
        return new DetailOngoingPengusulanAdapter.DetailOngoingPengusulanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOngoingPengusulanViewHolder holder, int position) {
        DetailOngoingPengusulan detailOngoingPengusulan = listData.get(position);
        holder.nama_barang_detail_openg.setText(detailOngoingPengusulan.nama_barang_detail_openg);
        holder.detail_spesifikasi_detail_openg.setText(detailOngoingPengusulan.detail_spesifikasi_detail_openg);
        holder.jumlah_barang_detail_openg.setText(Integer.toString(detailOngoingPengusulan.jumlah_barang_detail_openg));
        holder.harga_barang_detail_openg.setText(Integer.toString(detailOngoingPengusulan.harga_barang_detail_openg));
        holder.sumber_toko_openg.setText(detailOngoingPengusulan.sumber_toko_openg);
//        holder.status_detail_opengusulan.setText(detailOngoingPengusulan.status_detail_opengusulan);
//        holder.status_detail_opengusulan_fakultas.setText(detailOngoingPengusulan.status_detail_opengusulan_fakultas);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


}
