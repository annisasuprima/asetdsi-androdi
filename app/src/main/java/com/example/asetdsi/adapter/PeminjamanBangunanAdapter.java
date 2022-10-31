package com.example.asetdsi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.FormPeminjamanBangunanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.fragment.BangunanFragment;
import com.example.asetdsi.model.PeminjamanBangunan;
import com.example.asetdsi.model.Pj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeminjamanBangunanAdapter extends RecyclerView.Adapter<PeminjamanBangunanAdapter.PeminjamanBangunanViewHolder>{
    ArrayList<PeminjamanBangunan> listData = new ArrayList<PeminjamanBangunan>();
    Context context;


    public class PeminjamanBangunanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama_bgn_pnj;
        TextView status_bgn_pnj;
        ImageView gambarbgn_pnj;
        ImageView TambahBangunan;

        public PeminjamanBangunanViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_bgn_pnj = itemView.findViewById(R.id.nama_bgn_pnj);
            status_bgn_pnj = itemView.findViewById(R.id.status_bgn_pnj);
            gambarbgn_pnj = (ImageView) itemView.findViewById(R.id.gambarbgn_pnj);
//            TambahBangunan = (ImageView) itemView.findViewById(R.id.TambahBangunan);

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
    PeminjamanBangunanAdapter.OnPeminjamanBangunanHolderClickListener listener = null;

    public interface OnPeminjamanBangunanHolderClickListener {
        void onClick(View view, PeminjamanBangunan peminjamanBangunan);
    }

    public void setListener(PeminjamanBangunanAdapter.OnPeminjamanBangunanHolderClickListener listener) {
        this.listener = listener;
    }


    public void setListData(ArrayList<PeminjamanBangunan> listData) {
        this.listData = listData;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public PeminjamanBangunanAdapter.PeminjamanBangunanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_bangunan, parent,false);
        return new PeminjamanBangunanAdapter.PeminjamanBangunanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeminjamanBangunanAdapter.PeminjamanBangunanViewHolder holder, int position) {
        PeminjamanBangunan peminjamanBangunan = listData.get(position);

        String file_name = peminjamanBangunan.gambarbgn_pnj;
        String url = file_name;
        holder.nama_bgn_pnj.setText(peminjamanBangunan.nama_bgn_pnj);
        holder.status_bgn_pnj.setText(peminjamanBangunan.status_bgn_pnj);
        Picasso.get().load(url).into(holder.gambarbgn_pnj);


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


}
