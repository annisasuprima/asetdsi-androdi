package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.Barang;
import com.example.asetdsi.model.DetailFormPengusulanBarang;
import com.example.asetdsi.model.PengusulanBarang;
import com.example.asetdsi.model.Pj;

import java.util.ArrayList;

public class DetailFormPengusulanBarangAdapter extends RecyclerView.Adapter<DetailFormPengusulanBarangAdapter.DetailFormPengusulanBarangViewHolder> {

    ArrayList<PengusulanBarang> listData = new ArrayList<PengusulanBarang>();

    public DetailFormPengusulanBarangAdapter(ArrayList<PengusulanBarang> listData) {
    this.listData = listData;
    }

    public class DetailFormPengusulanBarangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nama_barang_detail_pengusulan;
        TextView detail_spesifikasi_pengusulan;
        TextView jumlah_barang_detail_pengusulan;
        TextView harga_barang_detail_pengusulan;
        TextView total_detail_pengusulan;
        TextView sumber_toko_pengusulan;
//    ImageView gambar_brg;

        public DetailFormPengusulanBarangViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_detail_pengusulan = itemView.findViewById(R.id.nama_barang_detail_pengusulan);
            detail_spesifikasi_pengusulan = itemView.findViewById(R.id.detail_spesifikasi_pengusulan);
            jumlah_barang_detail_pengusulan=itemView.findViewById(R.id.jumlah_barang_detail_pengusulan);
            harga_barang_detail_pengusulan=itemView.findViewById(R.id.harga_barang_detail_pengusulan);
            total_detail_pengusulan=itemView.findViewById(R.id.total_detail_pengusulan);
            sumber_toko_pengusulan=itemView.findViewById(R.id.sumber_toko_pengusulan);
//        gambar_brg=itemView.findViewById(R.id.gambar_brg);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(listener != null){
                listener.onClick(view, listData.get(getAdapterPosition()));
            }
        }
    }

    //Click Listener
    OnDetailFormPengusulanBarangHolderClickListener listener = null;

    public interface OnDetailFormPengusulanBarangHolderClickListener{
        void onClick(View view, PengusulanBarang detailFormPengusulanBarang);
    }

    public void setListener(DetailFormPengusulanBarangAdapter.OnDetailFormPengusulanBarangHolderClickListener listener) {
        this.listener = listener;
    }


    public void setListData(ArrayList<PengusulanBarang> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DetailFormPengusulanBarangAdapter.DetailFormPengusulanBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_pengusulan_barang, parent,false);
        return new DetailFormPengusulanBarangAdapter.DetailFormPengusulanBarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailFormPengusulanBarangAdapter.DetailFormPengusulanBarangViewHolder holder, int position) {

        PengusulanBarang detailFormPengusulanBarang = listData.get(position);
        holder.nama_barang_detail_pengusulan.setText(detailFormPengusulanBarang.nama_pengusulan_barang);
        holder.detail_spesifikasi_pengusulan.setText(detailFormPengusulanBarang.detail_spesifikasi_pengusulan_barang);
        holder.jumlah_barang_detail_pengusulan.setText(Integer.toString(detailFormPengusulanBarang.jumlah_pengusulan_barang));
        holder.harga_barang_detail_pengusulan.setText(Integer.toString(detailFormPengusulanBarang.harga_pengusulan_barang));
        holder.sumber_toko_pengusulan.setText(detailFormPengusulanBarang.sumber_pengusulan_barang);

        int kali = detailFormPengusulanBarang.jumlah_pengusulan_barang * detailFormPengusulanBarang.harga_pengusulan_barang;
        int total = 0;
        total = kali;

        holder.total_detail_pengusulan.setText(Integer.toString(total));


//        holder.total_detail_pengusulan.setText(Integer.toString(detailFormPengusulanBarang.total_detail_pengusulan));

    }

    @Override
    public int getItemCount() {

        return listData.size();
    }
}
