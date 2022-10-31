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
import com.example.asetdsi.model.ListBarangHome;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeBarangAdapter extends RecyclerView.Adapter<HomeBarangAdapter.HomeBarangViewHolder> {
    ArrayList<ListBarangHome> listData = new ArrayList<ListBarangHome>();

    public class HomeBarangViewHolder extends RecyclerView.ViewHolder{
        TextView nama_baranghome;
        ImageView gambarbaranghome;



        public HomeBarangViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_baranghome = itemView.findViewById(R.id.nama_baranghome);
            gambarbaranghome=(ImageView) itemView.findViewById(R.id.gambarbaranghome);


        }
    }
    public void setListData(ArrayList<ListBarangHome> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HomeBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_home, parent,false);
        return new HomeBarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBarangAdapter.HomeBarangViewHolder holder, int position) {
//        String url = "https://res.cloudinary.com/nishia/image/upload/v1664647686/i86od6ftoji01rb2hb9o.png";
       ListBarangHome listBarangHome = listData.get(position);


        String file_name = listBarangHome.gambarbaranghome;
        String url = file_name;
        holder.nama_baranghome.setText(listBarangHome.nama_baranghome);
        Picasso.get().load(url).into(holder.gambarbaranghome);

    }

    @Override
    public int getItemCount() {

        return listData.size();
    }
}
