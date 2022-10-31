package com.example.asetdsi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.BuktiFotoActivity;
import com.example.asetdsi.DetailHistoryPeminjamanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenence;
import com.example.asetdsi.model.PhotosItem;

import java.util.ArrayList;
import java.util.List;



public class DetailHistoryPengusulanMaintenenceAdapter  extends RecyclerView.Adapter<DetailHistoryPengusulanMaintenenceAdapter.DetailHistoryPengusulanMaintenenceViewHolder> {
    Context context;
    ArrayList<DetailHistoryPengusulanMaintenence> listData = new ArrayList<DetailHistoryPengusulanMaintenence>();
//    ArrayList<PhotosItem>listPhotoAdapters = new ArrayList<PhotosItem>();
//public DetailHistoryPengusulanMaintenenceAdapter(ArrayList<DetailHistoryPengusulanMaintenence> listData){
//    this.listData = listData;
//}

    public class DetailHistoryPengusulanMaintenenceViewHolder extends RecyclerView.ViewHolder{

        TextView nama_barang_detail_peng_mt;
        TextView kondisi_peng_mt;
        TextView permasalahan_mt;
        ImageView bukti_peng;
        Button BtnBukti;
        RecyclerView list_photoRv;
        RelativeLayout expandablelayout;
        ImageView arrow_imageview;

        public DetailHistoryPengusulanMaintenenceViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_detail_peng_mt = itemView.findViewById(R.id.nama_barang_detail_peng_mt);
            kondisi_peng_mt = itemView.findViewById(R.id.kondisi_peng_mt);
            permasalahan_mt=itemView.findViewById(R.id.permasalahan_mt);
            BtnBukti = itemView.findViewById(R.id.BtnBukti);





        }
    }
    public void setListData(ArrayList<DetailHistoryPengusulanMaintenence> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailHistoryPengusulanMaintenenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_history_pengusulan_maintenence, parent,false);
        return new DetailHistoryPengusulanMaintenenceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DetailHistoryPengusulanMaintenenceAdapter.DetailHistoryPengusulanMaintenenceViewHolder holder, int position) {
        DetailHistoryPengusulanMaintenence detailHistoryPengusulanMaintenence = listData.get(position);

        holder.nama_barang_detail_peng_mt.setText(detailHistoryPengusulanMaintenence.nama_barang_detail_peng_mt);
        holder.kondisi_peng_mt.setText(detailHistoryPengusulanMaintenence.kondisi_peng_mt);
        holder.permasalahan_mt.setText(detailHistoryPengusulanMaintenence.permasalahan_mt);


        holder.BtnBukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BuktiIntent = new Intent(view.getContext(), BuktiFotoActivity.class);
                BuktiIntent.putExtra("id_req_maintenence",detailHistoryPengusulanMaintenence.id_req_maintenence);
                view.getContext().startActivity(BuktiIntent);
            }
        });


//
//        boolean isExpandable = detailHistoryPengusulanMaintenence.isExpandable();
//        holder.expandablelayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
//
//        if(isExpandable){
//            holder.arrow_imageview.setImageResource(R.drawable.arrow_up);
//        }
//        else{
//            holder.arrow_imageview.setImageResource(R.drawable.arrow_down);
//        }

//        ListPhotoAdapter adapter = new ListPhotoAdapter(listPhotoAdapters);
//        holder.list_photoRv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
//        holder.list_photoRv.setAdapter(adapter);
//
//        holder.arrow_imageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                detailHistoryPengusulanMaintenence.setExpandable(!detailHistoryPengusulanMaintenence.isExpandable);
////                listPhotoAdapters = (ArrayList<PhotosItem>) detailHistoryPengusulanMaintenence.getBukti_mt();
//                notifyItemChanged(holder.getAdapterPosition());
//                }
//        });






//        Picasso.get().load(url).into(holder.bukti_peng);
    }


    @Override
    public int getItemCount() {

        return listData.size();
    }
}
