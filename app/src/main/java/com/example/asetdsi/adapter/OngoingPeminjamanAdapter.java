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
import com.example.asetdsi.model.OngoingPeminjaman;

import java.util.ArrayList;

public class OngoingPeminjamanAdapter extends RecyclerView.Adapter<OngoingPeminjamanAdapter.OngoingPeminjamanViewHolder> {

    ArrayList<OngoingPeminjaman> listData = new ArrayList<OngoingPeminjaman>();



    public class OngoingPeminjamanViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView keterangan_ongoing;
        TextView tanggal_ongoing;
        TextView jam_ongoing;
        TextView status_ongoing;
        ImageView logobarangongoing;

        public OngoingPeminjamanViewHolder(@NonNull View itemView) {
            super(itemView);
            keterangan_ongoing = itemView.findViewById(R.id.keterangan_ongoing);
            tanggal_ongoing = itemView.findViewById(R.id.tanggal_ongoing);
            jam_ongoing = itemView.findViewById(R.id.jam_ongoing);
            status_ongoing = itemView.findViewById(R.id.status_ongoing);
            logobarangongoing = (ImageView)itemView.findViewById(R.id.logobarangongoing);

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
    OngoingPeminjamanAdapter.OnOngoingPeminjamanHolderClickListener listener = null;

    public interface OnOngoingPeminjamanHolderClickListener {
        void onClick(View view, OngoingPeminjaman ongoingPeminjaman);
    }

    public void setListener(OngoingPeminjamanAdapter.OnOngoingPeminjamanHolderClickListener listener) {
        this.listener = listener;
    }

    public void setListData(ArrayList<OngoingPeminjaman> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public OngoingPeminjamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_ongoing, parent,false);
        return new OngoingPeminjamanAdapter.OngoingPeminjamanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingPeminjamanViewHolder holder, int position) {

        OngoingPeminjaman ongoingPeminjaman = listData.get(position);
        holder.keterangan_ongoing.setText(ongoingPeminjaman.keterangan_ongoing);
        holder.tanggal_ongoing.setText(ongoingPeminjaman.tanggal_ongoing);
        holder.jam_ongoing.setText(ongoingPeminjaman.jam_ongoing);
        holder.status_ongoing.setText(ongoingPeminjaman.status_ongoing);
        if(ongoingPeminjaman.type_id==1) {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.barang)
                    .into(holder.logobarangongoing);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.bangunan)
                    .into(holder.logobarangongoing);
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
