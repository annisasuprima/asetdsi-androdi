package com.example.asetdsi.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.Barang;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenence;
import com.example.asetdsi.model.ListPhotoMaintenence;
import com.example.asetdsi.model.PhotosItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListPhotoAdapter extends RecyclerView.Adapter<ListPhotoAdapter.ListPhotoViewHolder> {

    ArrayList<ListPhotoMaintenence> listData = new ArrayList<ListPhotoMaintenence>();

    public class ListPhotoViewHolder extends RecyclerView.ViewHolder{
        ImageView bukti_mt;

        public ListPhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            bukti_mt=itemView.findViewById(R.id.bukti_mt);

        }
    }
    public void setListData(ArrayList<ListPhotoMaintenence> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListPhotoAdapter.ListPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_photo_detail_maintenence, parent,false);
        return new ListPhotoAdapter.ListPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPhotoAdapter.ListPhotoViewHolder holder, int position) {
//        String url = "https://res.cloudinary.com/nishia/image/upload/v1664647686/i86od6ftoji01rb2hb9o.png";
//
        ListPhotoMaintenence listPhotoMaintenence = listData.get(position);
        String file_name = listPhotoMaintenence.bukti_mt;
        String url = file_name;
        Picasso.get().load(url).into(holder.bukti_mt);



    }



    @Override
    public int getItemCount() {

        return listData.size();
    }
}

