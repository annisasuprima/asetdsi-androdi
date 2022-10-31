package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asetdsi.R;
import com.example.asetdsi.model.HistoryPeminjaman;
import com.example.asetdsi.model.Peminjaman;
import com.example.asetdsi.model.Pj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HistoryPeminjamanAdapter extends RecyclerView.Adapter<HistoryPeminjamanAdapter.HistoryPeminjamanViewHolder> {


    ArrayList<HistoryPeminjaman> listData = new ArrayList<HistoryPeminjaman>();

    public class HistoryPeminjamanViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView keterangan_history;
        TextView tanggal_history;
        TextView jam_history;
        TextView status_history;
        ImageView logobarang;


        public HistoryPeminjamanViewHolder(@NonNull View itemView) {
            super(itemView);
            keterangan_history = itemView.findViewById(R.id.keterangan_history);
            tanggal_history = itemView.findViewById(R.id.tanggal_history);
            jam_history = itemView.findViewById(R.id.jam_history);
            logobarang = itemView.findViewById(R.id.logobarang);
            status_history = itemView.findViewById(R.id.status_history);
            logobarang = (ImageView)itemView.findViewById(R.id.logobarang);
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
    HistoryPeminjamanAdapter.OnHistoryPeminjamanHolderClickListener listener = null;

    public interface OnHistoryPeminjamanHolderClickListener{
        void onClick(View view, HistoryPeminjaman historyPeminjaman);
    }

    public void setListener(HistoryPeminjamanAdapter.OnHistoryPeminjamanHolderClickListener listener) {
        this.listener = listener;
    }


    public void setListData(ArrayList<HistoryPeminjaman> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryPeminjamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_history, parent,false);
        return new HistoryPeminjamanAdapter.HistoryPeminjamanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryPeminjamanViewHolder holder, int position) {
        HistoryPeminjaman historyPeminjaman = listData.get(position);
        holder.keterangan_history.setText(historyPeminjaman.keterangan_history);
        holder.tanggal_history.setText(historyPeminjaman.tanggal_history);
        holder.jam_history.setText(historyPeminjaman.jam_history);
        holder.status_history.setText(historyPeminjaman.status_history);
        if(historyPeminjaman.type_id==1) {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.barang)
                    .into(holder.logobarang);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.bangunan)
                    .into(holder.logobarang);
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


}
