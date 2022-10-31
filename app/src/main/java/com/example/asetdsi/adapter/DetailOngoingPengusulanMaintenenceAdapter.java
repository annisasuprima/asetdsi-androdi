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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.BuktiFotoActivity;
import com.example.asetdsi.BuktiFotoOngoingActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenence;
import com.example.asetdsi.model.DetailOngoingPengusulanMaintenence;

import java.util.ArrayList;

public class DetailOngoingPengusulanMaintenenceAdapter extends RecyclerView.Adapter<DetailOngoingPengusulanMaintenenceAdapter.DetailOngoingPengusulanMaintenenceViewHolder> {
    Context context;

    ArrayList<DetailOngoingPengusulanMaintenence> listData = new ArrayList<DetailOngoingPengusulanMaintenence>();

    public class DetailOngoingPengusulanMaintenenceViewHolder extends RecyclerView.ViewHolder{

        TextView nama_barang_detail_openg_mt;
        TextView kondisi_openg_mt;
        TextView permasalahan_openg_mt;
        ImageView bukti_peng;
        Button BtnBuktiOPeng;

        public DetailOngoingPengusulanMaintenenceViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_barang_detail_openg_mt = itemView.findViewById(R.id.nama_barang_detail_openg_mt);
            kondisi_openg_mt = itemView.findViewById(R.id.kondisi_openg_mt);
            permasalahan_openg_mt=itemView.findViewById(R.id.permasalahan_openg_mt);
            BtnBuktiOPeng = itemView.findViewById(R.id.BtnBuktiOpeng);

        }
    }

    public void setListData(ArrayList<DetailOngoingPengusulanMaintenence> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailOngoingPengusulanMaintenenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_detail_ongoing_pengusulan_maintenence, parent,false);
        return new DetailOngoingPengusulanMaintenenceAdapter.DetailOngoingPengusulanMaintenenceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DetailOngoingPengusulanMaintenenceAdapter.DetailOngoingPengusulanMaintenenceViewHolder holder, int position) {
        DetailOngoingPengusulanMaintenence detailOngoingPengusulanMaintenence = listData.get(position);

        holder.nama_barang_detail_openg_mt.setText(detailOngoingPengusulanMaintenence.nama_barang_detail_openg_mt);
        holder.kondisi_openg_mt.setText(detailOngoingPengusulanMaintenence.kondisi_openg_mt);
        holder.permasalahan_openg_mt.setText(detailOngoingPengusulanMaintenence.permasalahan_openg_mt);


        holder.BtnBuktiOPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BuktiMtIntent = new Intent(view.getContext(), BuktiFotoOngoingActivity.class);
                BuktiMtIntent.putExtra("id_req_maintenence",detailOngoingPengusulanMaintenence.id_req_maintenence);
                view.getContext().startActivity(BuktiMtIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
