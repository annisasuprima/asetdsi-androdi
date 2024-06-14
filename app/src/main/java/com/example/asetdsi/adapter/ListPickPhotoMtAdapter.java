package com.example.asetdsi.adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.ListPhotoMaintenence;
import com.example.asetdsi.model.ListPickPhotoMt;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPickPhotoMtAdapter extends RecyclerView.Adapter<ListPickPhotoMtAdapter.ListPickPhotoMtViewHolder> {

    private ArrayList<Uri> uriArrayList;

    public ListPickPhotoMtAdapter(ArrayList<Uri> uriArrayList) {
        this.uriArrayList = uriArrayList;
    }

    public class ListPickPhotoMtViewHolder extends RecyclerView.ViewHolder{
        ImageView buktifoto_mt,deleteImg;


        public ListPickPhotoMtViewHolder(@NonNull View itemView) {
            super(itemView);
            buktifoto_mt=itemView.findViewById(R.id.buktifoto_mt);
//            deleteImg=itemView.findViewById(R.id.deleteImg);

        }
    }
    public void setListData(ArrayList<Uri> uriArrayList) {
        this.uriArrayList = uriArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListPickPhotoMtAdapter.ListPickPhotoMtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_photo_mt, parent,false);
        return new ListPickPhotoMtAdapter.ListPickPhotoMtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPickPhotoMtAdapter.ListPickPhotoMtViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        String url = "https://res.cloudinary.com/nishia/image/upload/v1664647686/i86od6ftoji01rb2hb9o.png";
//
        Uri uri = uriArrayList.get(position);
//        holder.buktifoto_mt.setImageURI(uriArrayList.get(position));

        Picasso.get().
                load(uri)
                .into(holder.buktifoto_mt);

        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uriArrayList.remove(uri);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,getItemCount());
            }
        });



    }



    @Override
    public int getItemCount() {

        return uriArrayList.size();
    }
}
