package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.ArrayMt;
import com.example.asetdsi.model.DetailFormPengusulanMaintenence;
import com.example.asetdsi.model.PengusulanBarang;

import java.util.ArrayList;

public class DetailFormPengusulanMaintenenceAdapter extends RecyclerView.Adapter<DetailFormPengusulanMaintenenceAdapter.DetailFormPengusulanMaintenenceViewHolder> {



//    ArrayList<DetailFormPengusulanMaintenence> listData = new ArrayList<>();
    ArrayList<ArrayMt> listArray = new ArrayList<>();
    public DetailFormPengusulanMaintenenceAdapter(ArrayList<ArrayMt> listArray) {
        this.listArray = listArray;
    }


    public class DetailFormPengusulanMaintenenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nama_barang_pengusulanmt;
        TextView kondisi_pengusulanmt;
        TextView permasalahan_pengusulan_mt;
        TextView kode_pengusulan_mt;
        Button BtnBuktiPengusulanMt;
        RecyclerView rvDetailPhotoMtRv;

        public DetailFormPengusulanMaintenenceViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_pengusulanmt = itemView.findViewById(R.id.nama_barang_pengusulanmt);
            kondisi_pengusulanmt = itemView.findViewById(R.id.kondisi_pengusulan_mt);
            permasalahan_pengusulan_mt = itemView.findViewById(R.id.permasalahan_pengusulanmt);
            kode_pengusulan_mt=itemView.findViewById(R.id.kode_pengusulan_mt);

//            rvDetailPhotoMtRv=itemView.findViewById(R.id.rvDetailPhotoMtRv);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null){
                listener.onClick(view, listArray.get(getAdapterPosition()));
            }
        }
    }

    //Click Listener
    DetailFormPengusulanMaintenenceAdapter.OnDetailFormPengusulanMaintenenceHolderClickListener listener = null;

    public interface OnDetailFormPengusulanMaintenenceHolderClickListener{
        void onClick(View view, ArrayMt arrayMt);
    }

    public void setListener(DetailFormPengusulanMaintenenceAdapter.OnDetailFormPengusulanMaintenenceHolderClickListener listener) {
        this.listener = listener;
    }



    public void setListData(ArrayList<ArrayMt> listArray) {
        this.listArray = listArray;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailFormPengusulanMaintenenceAdapter.DetailFormPengusulanMaintenenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_pengusulan_maintenence, parent,false);
        return new DetailFormPengusulanMaintenenceAdapter.DetailFormPengusulanMaintenenceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DetailFormPengusulanMaintenenceAdapter.DetailFormPengusulanMaintenenceViewHolder holder, int position) {


        ArrayMt arrayMt = listArray.get(position);
        holder.nama_barang_pengusulanmt.setText(arrayMt.nama_barang_arraymt);
      holder.kondisi_pengusulanmt.setText(arrayMt.kondisi_arraymt);
      holder.permasalahan_pengusulan_mt.setText(arrayMt.permasalahan_arraymt);
      holder.kode_pengusulan_mt.setText(arrayMt.kode_arraymt);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvDetailPhotoMtRv.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );


        //multipleimage
//        layoutManager.setInitialPrefetchItemCount(arrayMt.getUri().size());
//
//        // Create sub item view adapter
//        RvArrayPhotoAdapter subItemAdapter = new RvArrayPhotoAdapter(arrayMt.getUri());
//
//        holder.rvDetailPhotoMtRv.setLayoutManager(layoutManager);
//        holder.rvDetailPhotoMtRv.setAdapter(subItemAdapter);


    }

    @Override
    public int getItemCount() {
        return listArray.size();
    }


}
