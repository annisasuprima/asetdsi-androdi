package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.DetailHistoryPeminjaman;
import com.example.asetdsi.model.DetailOngoingPeminjaman;

import java.util.ArrayList;

public class DetailOngoingPeminjamanAdapter extends RecyclerView.Adapter<DetailOngoingPeminjamanAdapter.DetailOngoingPeminjamanViewHolder> {


    ArrayList<DetailOngoingPeminjaman> listData = new ArrayList<DetailOngoingPeminjaman>();

    public class DetailOngoingPeminjamanViewHolder extends RecyclerView.ViewHolder{
        TextView nama_barang_detail_op;
        TextView merk_barang_detail_op;
        TextView jumlah_barang_detail_op;

        public DetailOngoingPeminjamanViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_detail_op = itemView.findViewById(R.id.nama_barang_detail_op);
            merk_barang_detail_op = itemView.findViewById(R.id.merk_barang_detail_op);
            jumlah_barang_detail_op=itemView.findViewById(R.id.jumlah_barang_detail_op);

        }
    }
    public void setListData(ArrayList<DetailOngoingPeminjaman> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailOngoingPeminjamanAdapter.DetailOngoingPeminjamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_ongoing_peminjaman, parent,false);
        return new DetailOngoingPeminjamanAdapter.DetailOngoingPeminjamanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOngoingPeminjamanAdapter.DetailOngoingPeminjamanViewHolder holder, int position) {
        DetailOngoingPeminjaman detailOngoingPeminjaman = listData.get(position);
        holder.nama_barang_detail_op.setText(detailOngoingPeminjaman.nama_barang_detail_op);
        holder.merk_barang_detail_op.setText(detailOngoingPeminjaman.merk_barang_detail_op);
        holder.jumlah_barang_detail_op.setText(Integer.toString(detailOngoingPeminjaman.jumlah_barang_detail_op));
    }


    @Override
    public int getItemCount() {

        return listData.size();
    }


}
