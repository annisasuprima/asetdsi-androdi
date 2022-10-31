package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.ListPhotoMaintenence;
import com.example.asetdsi.model.ListPhotoOngoingMaintenence;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPhotoOngoingAdapter  extends RecyclerView.Adapter<ListPhotoOngoingAdapter.ListPhotoOngoingViewHolder> {
    ArrayList<ListPhotoOngoingMaintenence> listData = new ArrayList<ListPhotoOngoingMaintenence>();

    public class ListPhotoOngoingViewHolder extends RecyclerView.ViewHolder{
        ImageView bukti_openg_mt;

        public ListPhotoOngoingViewHolder(@NonNull View itemView) {
            super(itemView);
            bukti_openg_mt=itemView.findViewById(R.id.bukti_openg_mt);

        }
    }
    public void setListData(ArrayList<ListPhotoOngoingMaintenence> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListPhotoOngoingAdapter.ListPhotoOngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_photo_detail_ongoing_maintenence, parent,false);
        return new ListPhotoOngoingAdapter.ListPhotoOngoingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPhotoOngoingAdapter.ListPhotoOngoingViewHolder holder, int position) {
//        String url = "https://res.cloudinary.com/nishia/image/upload/v1664647686/i86od6ftoji01rb2hb9o.png";
//
        ListPhotoOngoingMaintenence listPhotoOngoingMaintenence = listData.get(position);
        String file_name = listPhotoOngoingMaintenence.bukti_openg_mt;
        String url = file_name;
        Picasso.get().load(url).into(holder.bukti_openg_mt);


    }


    @Override
    public int getItemCount() {

        return listData.size();
    }
}
