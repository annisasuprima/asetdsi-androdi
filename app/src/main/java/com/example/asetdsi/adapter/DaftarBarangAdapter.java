package com.example.asetdsi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asetdsi.PeminjamanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.model.Barang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DaftarBarangAdapter extends RecyclerView.Adapter<DaftarBarangAdapter.DaftarBarangViewHolder> {

    ArrayList<Barang> listData = new ArrayList<Barang>();

public class DaftarBarangViewHolder extends RecyclerView.ViewHolder{
    TextView nama_brg;
    TextView nama_pjbrg;
    TextView merk_brg;
    TextView availablebrg;
    TextView jumlah;
    ImageView gambar_brg;

    public DaftarBarangViewHolder(@NonNull View itemView) {
        super(itemView);
        nama_brg = itemView.findViewById(R.id.nama_brg);
        nama_pjbrg = itemView.findViewById(R.id.nama_pjbrg);
        merk_brg=itemView.findViewById(R.id.merk_brg);
        availablebrg=itemView.findViewById(R.id.availablebrg);
        jumlah=itemView.findViewById(R.id.jumlah);
        gambar_brg=itemView.findViewById(R.id.gambar_brg);

    }
}
    public void setListData(ArrayList<Barang> listData) {
    this.listData = listData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DaftarBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_brg, parent,false);
        return new DaftarBarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarBarangViewHolder holder, int position) {
//        String url = "https://res.cloudinary.com/nishia/image/upload/v1664647686/i86od6ftoji01rb2hb9o.png";


        Barang barang = listData.get(position);
        String file_name = barang.gambar_brg;
        String url = file_name;
        holder.nama_brg.setText(barang.nama_brg);
        holder.nama_pjbrg.setText(barang.nama_pjbrg);
        holder.merk_brg.setText(barang.merk_brg);
        holder.availablebrg.setText(barang.availablebrg);
        holder.jumlah.setText(Integer.toString(barang.jumlah));
        Picasso.get().load(url).into(holder.gambar_brg);



    }



    @Override
    public int getItemCount() {

    return listData.size();
    }
}
