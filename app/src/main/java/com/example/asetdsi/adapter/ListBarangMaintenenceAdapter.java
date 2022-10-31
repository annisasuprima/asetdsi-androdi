package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.DetailFormPengusulanBarang;
import com.example.asetdsi.model.ListBarangMaintenence;

import java.util.ArrayList;

public class ListBarangMaintenenceAdapter extends RecyclerView.Adapter<ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder> {

    ArrayList<ListBarangMaintenence> listData = new ArrayList<ListBarangMaintenence>();

    public class ListBarangMaintenenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nama_brg_maintenence;
        TextView merk_brg_maintenence;
        TextView kondisi_brg_maintenence;


        public ListBarangMaintenenceViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_brg_maintenence = itemView.findViewById(R.id.nama_brg_maintenence);
            merk_brg_maintenence = itemView.findViewById(R.id.merk_brg_maintenence);
            kondisi_brg_maintenence=itemView.findViewById(R.id.kondisi_brg_maintenence);

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
    ListBarangMaintenenceAdapter.OnListBarangMaintenenceAdapterHolderClickListener listener = null;

    public interface OnListBarangMaintenenceAdapterHolderClickListener{
        void onClick(View view, ListBarangMaintenence listBarangMaintenence);
    }

    public void setListener(ListBarangMaintenenceAdapter.OnListBarangMaintenenceAdapterHolderClickListener listener) {
        this.listener = listener;
    }

    public void setListData(ArrayList<ListBarangMaintenence> listData) {
        this.listData = listData;
    }


    @NonNull
    @Override
    public ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_barang_maintenence, parent,false);
        return new ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder holder, int position) {

       ListBarangMaintenence listBarangMaintenence = listData.get(position);
        holder.nama_brg_maintenence.setText(listBarangMaintenence.nama_brg_maintenence);
        holder.merk_brg_maintenence.setText(listBarangMaintenence.merk_brg_maintenence);
        holder.kondisi_brg_maintenence.setText(listBarangMaintenence.kondisi_brg_maintenence);

    }

    @Override
    public int getItemCount() {

        return listData.size();
    }


}
