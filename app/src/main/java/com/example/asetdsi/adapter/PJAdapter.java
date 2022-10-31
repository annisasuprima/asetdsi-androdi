package com.example.asetdsi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.PeminjamanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.model.Barang;
import com.example.asetdsi.model.Pj;

import java.util.ArrayList;

public class PJAdapter extends RecyclerView.Adapter<PJAdapter.PjViewHolder> {

    ArrayList<Pj> listData = new ArrayList<Pj>();

    public  class PjViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView nama_pj;
        TextView jumlahpj;
//        CardView pjCv;
        public PjViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_pj = itemView.findViewById(R.id.nama_pj);
            jumlahpj = itemView.findViewById(R.id.jumlahpj);
//            pjCv = itemView.findViewById(R.id.pjCv);pjCv
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
    OnPjHolderClickListener listener = null;

    public interface OnPjHolderClickListener{
        void onClick(View view,Pj pj);
    }

    public void setListener(OnPjHolderClickListener listener) {
        this.listener = listener;
    }

    public void setListData(ArrayList<Pj> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PjViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_pj, parent,false);
        return new PjViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PjViewHolder holder, int position) {
    Pj pj = listData.get(position);
    holder.nama_pj.setText(pj.nama_pj);
    holder.jumlahpj.setText(Integer.toString(pj.jumlahpj));



    }


    @Override
    public int getItemCount() {
        return listData.size();
    }


}
