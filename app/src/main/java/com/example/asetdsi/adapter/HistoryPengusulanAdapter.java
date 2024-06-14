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
import com.example.asetdsi.model.HistoryPengusulan;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryPengusulanAdapter extends RecyclerView.Adapter<HistoryPengusulanAdapter.HistoryPengusulanViewHolder> {

    ArrayList<HistoryPengusulan> listData = new ArrayList<HistoryPengusulan>();

    public class HistoryPengusulanViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView keterangan_history_pengusulan;
        TextView status_history_pengusulan;
        TextView status_history_pengusulan_fakultas;
        TextView alasan_pengusulan;
        ImageView logobarang_history_pengusulan;

        public HistoryPengusulanViewHolder(@NonNull View itemView) {
            super(itemView);
            keterangan_history_pengusulan = itemView.findViewById(R.id.keterangan_history_pengusulan);
            status_history_pengusulan = itemView.findViewById(R.id.status_history_pengusulan);
            status_history_pengusulan_fakultas = itemView.findViewById(R.id.status_history_pengusulan_fakultas);
            logobarang_history_pengusulan = (ImageView)itemView.findViewById(R.id.logobarang_history_pengusulan);
            alasan_pengusulan = itemView.findViewById(R.id.alasan_pengusulan);

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
    HistoryPengusulanAdapter.OnHistoryPengusulanHolderClickListener listener = null;

    public interface OnHistoryPengusulanHolderClickListener{
        void onClick(View view, HistoryPengusulan historyPengusulan);
    }

    public void setListener(HistoryPengusulanAdapter.OnHistoryPengusulanHolderClickListener listener) {
        this.listener = listener;
    }


    public void setListData(ArrayList<HistoryPengusulan> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryPengusulanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_history_pengusulan, parent,false);
        return new HistoryPengusulanAdapter.HistoryPengusulanViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryPengusulanViewHolder holder, int position) {
        HistoryPengusulan historyPengusulan = listData.get(position);
        holder.keterangan_history_pengusulan.setText(historyPengusulan.keterangan_history_pengusulan);
        holder.status_history_pengusulan.setText(historyPengusulan.status_history_pengusulan);

        if(Objects.equals(historyPengusulan.status_history_pengusulan,"accepted")){
            holder.status_history_pengusulan.setBackgroundResource(R.drawable.badge_ijau);
            holder.alasan_pengusulan.setText(historyPengusulan.alasan_pengusulan);
        }else if(Objects.equals(historyPengusulan.status_history_pengusulan,"rejected")){
            holder.status_history_pengusulan.setBackgroundResource(R.drawable.badge_merah);

                holder.alasan_pengusulan.setText(historyPengusulan.alasan_pengusulan);

        }else
        {
            holder.status_history_pengusulan.setBackgroundResource(R.drawable.badge);

        }

        holder.status_history_pengusulan_fakultas.setText(historyPengusulan.status_history_pengusulan_fakultas);
        if(Objects.equals(historyPengusulan.status_history_pengusulan_fakultas,"accepted")){
            holder.status_history_pengusulan_fakultas.setBackgroundResource(R.drawable.badge_ijau);
            holder.alasan_pengusulan.setText(historyPengusulan.alasan_pengusulan);
        }else if(Objects.equals(historyPengusulan.status_history_pengusulan_fakultas,"rejected")){
            holder.status_history_pengusulan_fakultas.setBackgroundResource(R.drawable.badge_merah);


                holder.alasan_pengusulan.setText(historyPengusulan.alasan_pengusulan);

        }
        else {
            holder.status_history_pengusulan_fakultas.setBackgroundResource(R.drawable.badge);

        }



//        if(historyPengusulan.status_history_pengusulan=="accepted" && historyPengusulan.status_history_pengusulan_fakultas=="rejected") {
//            holder.alasan_pengusulan.setText(historyPengusulan.alasan_pengusulan);
//        }else if(historyPengusulan.status_history_pengusulan=="accepted" && historyPengusulan.status_history_pengusulan_fakultas=="accepted"){
//            holder.alasan_pengusulan.setVisibility(View.GONE);
//        }



        if(historyPengusulan.type_id==1) {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.barang)
                    .into(holder.logobarang_history_pengusulan);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.maintenence)
                    .into(holder.logobarang_history_pengusulan);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
