package com.example.asetdsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.fragment.NewPengusulanMaintenenceFragment;
import com.example.asetdsi.model.ArrayMt;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArrayMtAdapter extends RecyclerView.Adapter<ArrayMtAdapter.ArrayMtViewHolder>{
   NewPengusulanMaintenenceFragment mContext;

    public void setListData(ArrayList<ArrayMt> listData) {
        this.listData = listData;
        notifyDataSetChanged();

    }

        private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private ArrayList<ArrayMt> listData;


    public ArrayMtAdapter(NewPengusulanMaintenenceFragment mContext, ArrayList<ArrayMt> listData) {
        this.mContext = mContext;
        this.listData = listData;
    }


    @NonNull
    @Override
    public ArrayMtAdapter.ArrayMtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_array_mt, parent,false);
        return new ArrayMtAdapter.ArrayMtViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArrayMtAdapter.ArrayMtViewHolder holder, int position) {

        ArrayMt arrayMt = listData.get(position);
        holder.permasalahan_arraymt.setText(arrayMt.permasalahan_arraymt);
        holder.nama_barang_arraymt.setText(arrayMt.nama_barang_arraymt);
        holder.kode_arraymt.setText(arrayMt.kode_arraymt);
        holder.kondisi_arraymt.setText(arrayMt.kondisi_arraymt);

        String filename = arrayMt.photo_pengusulanmt;
        Picasso.get().
                load(filename)
                .into(holder.photo_pengusulanmt);
//        holder.photo_pengusulanmt.setImageResource(Integer.parseInt(arrayMt.photo_pengusulanmt));





        //photoRececyler

//                LinearLayoutManager layoutManager = new LinearLayoutManager(
//                holder.rvPhotoMtRv.getContext(),
//                LinearLayoutManager.HORIZONTAL,
//                false
//        );
//        layoutManager.setInitialPrefetchItemCount(arrayMt.getUri().size());
//        RvArrayPhotoAdapter rvArrayPhotoAdapter;
//        rvArrayPhotoAdapter = new RvArrayPhotoAdapter(listData.get(position).getUri());
//        // Create sub item view adapter
////        RvArrayPhotoAdapter subItemAdapter = new RvArrayPhotoAdapter(arrayMt.getUri());
//
//        holder.rvPhotoMtRv.setLayoutManager(layoutManager);
//        holder.rvPhotoMtRv.setHasFixedSize(true);
//        holder.rvPhotoMtRv.setAdapter(rvArrayPhotoAdapter);
//        holder.rvPhotoMtRv.setRecycledViewPool(viewPool);
//        rvArrayPhotoAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ArrayMtViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rvBarangMaintenencemtmt;
        TextView permasalahan_arraymt,nama_barang_arraymt,kondisi_arraymt,kode_arraymt;
        ImageView photo_pengusulanmt;
        RecyclerView rvPhotoMtRv;
        public ArrayMtViewHolder(@NonNull View itemView) {
            super(itemView);

            permasalahan_arraymt=itemView.findViewById(R.id.permasalahan_arraymt);
            nama_barang_arraymt=itemView.findViewById(R.id.nama_barang_arraymt);
            kode_arraymt=itemView.findViewById(R.id.kode_arraymt);
            kondisi_arraymt=itemView.findViewById(R.id.kondisi_arraymt);
            photo_pengusulanmt=itemView.findViewById(R.id.photo_pengusulanmt);

            rvPhotoMtRv=itemView.findViewById(R.id.rvPhotoMtRv);
        }
    }
}
