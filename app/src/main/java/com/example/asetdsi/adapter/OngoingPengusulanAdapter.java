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
import com.example.asetdsi.model.OngoingPeminjaman;
import com.example.asetdsi.model.OngoingPengusulan;

import java.util.ArrayList;


public class OngoingPengusulanAdapter extends RecyclerView.Adapter<OngoingPengusulanAdapter.OngoingPengusulanViewHolder> {

    ArrayList<OngoingPengusulan> listData = new ArrayList<OngoingPengusulan>();

    public class OngoingPengusulanViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView ket_op;
        TextView status_ongoing_pengusulan;
        TextView status_ongoing_pengusulan_fakultas;
        ImageView logobarang_ongoing_pengusulan;

        public OngoingPengusulanViewHolder(@NonNull View itemView) {
            super(itemView);
            ket_op = (TextView)itemView.findViewById(R.id.ket_op);
            status_ongoing_pengusulan = itemView.findViewById(R.id.status_ongoing_pengusulan);
            status_ongoing_pengusulan_fakultas = itemView.findViewById(R.id.status_ongoing_pengusulan_fakultas);
            logobarang_ongoing_pengusulan = (ImageView)itemView.findViewById(R.id.logobarang_ongoing_pengusulan);

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
        OngoingPengusulanAdapter.OnOngoingPengusulanHolderClickListener listener = null;

        public interface OnOngoingPengusulanHolderClickListener {
            void onClick(View view, OngoingPengusulan ongoingPengusulan);
        }

        public void setListener(OngoingPengusulanAdapter.OnOngoingPengusulanHolderClickListener listener) {
            this.listener = listener;
        }



        public void setListData(ArrayList<OngoingPengusulan> listData) {
            this.listData = listData;
            notifyDataSetChanged();
        }


    @NonNull
    @Override
    public OngoingPengusulanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_ongoing_pengusulan, parent,false);
        return new OngoingPengusulanAdapter.OngoingPengusulanViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OngoingPengusulanViewHolder holder, int position) {
        OngoingPengusulan ongoingPengusulan = listData.get(position);
        holder.ket_op.setText(ongoingPengusulan.ket_op);
        holder.status_ongoing_pengusulan.setText(ongoingPengusulan.status_ongoing_pengusulan);
        holder.status_ongoing_pengusulan_fakultas.setText(ongoingPengusulan.status_ongoing_pengusulan_fakultas);
        if(ongoingPengusulan.type_id==1) {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.barang)
                    .into(holder.logobarang_ongoing_pengusulan);
        }
//        }else {
//            Glide.with(holder.itemView.getContext())
//                    .load(R.drawable.maintenence)
//                    .into(holder.logobarang_ongoing_pengusulan);
//        }


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
