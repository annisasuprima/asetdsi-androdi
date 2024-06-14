package com.example.asetdsi.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.fragment.NewPengusulanMaintenenceFragment;
import com.example.asetdsi.model.ArrayMt;
import com.example.asetdsi.model.ArrayPhotoMt;
import com.example.asetdsi.model.ListPhotoMaintenence;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RvArrayPhotoAdapter extends RecyclerView.Adapter<RvArrayPhotoAdapter.RvArrayPhotoViewHolder>{
//    private final List<ArrayPhotoMt> subItemList;
    NewPengusulanMaintenenceFragment mContext;

    public void setListuri(ArrayList<Uri> listuri) {
        this.listuri = listuri;
        notifyDataSetChanged();
    }

    ArrayList<Uri> listuri;

//    public ArrayMtAdapter(NewPengusulanMaintenenceFragment mContext, ArrayList<ArrayMt> listData) {
//        this.mContext = mContext;
//        this.listData = listData;
//    }

    public RvArrayPhotoAdapter(ArrayList<Uri> uri) {
        this.listuri = uri;

    }

    @NonNull
    @Override
    public RvArrayPhotoAdapter.RvArrayPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_rv_array_photomt, parent,false);
        return new RvArrayPhotoAdapter.RvArrayPhotoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RvArrayPhotoAdapter.RvArrayPhotoViewHolder holder, int position) {

        Uri uri = listuri.get(position);
//        holder.buktifoto_mt.setImageURI(uriArrayList.get(position));
        Picasso.get().
                load(uri)
                .into(holder.buktirv_photo_mt);

    }

    @Override
    public int getItemCount() {
        return listuri.size();
    }

    public class RvArrayPhotoViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rvBarangMaintenencemtmt;
        ImageView buktirv_photo_mt;
        public RvArrayPhotoViewHolder(@NonNull View itemView) {
            super(itemView);

            buktirv_photo_mt=itemView.findViewById(R.id.buktirv_photo_mt);

        }
    }

}
