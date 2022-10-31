package com.example.asetdsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.Pj;
import com.example.asetdsi.model.PjPengusulan;

import java.util.ArrayList;

public class PJPengusulanAdapter extends RecyclerView.Adapter<PJPengusulanAdapter.PjPengusulanViewHolder>{

    ArrayList<PjPengusulan> listData = new ArrayList<PjPengusulan>();

    public  class PjPengusulanViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView nama_pj_pengusulan;
        TextView jumlahpj_pengusulan;
        //        CardView pjCv;
        public PjPengusulanViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_pj_pengusulan = itemView.findViewById(R.id.nama_pj_pengusulan);
            jumlahpj_pengusulan = itemView.findViewById(R.id.jumlahpj_pengusulan);

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
    PJPengusulanAdapter.OnPjPengusulanHolderClickListener listener = null;

    public interface OnPjPengusulanHolderClickListener{
        void onClick(View view,PjPengusulan pjPengusulan);
    }

    public void setListener(PJPengusulanAdapter.OnPjPengusulanHolderClickListener listener) {
        this.listener = listener;
    }

    public void setListData(ArrayList<PjPengusulan> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PJPengusulanAdapter.PjPengusulanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_pj_pengusulan, parent,false);
        return new PJPengusulanAdapter.PjPengusulanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PJPengusulanAdapter.PjPengusulanViewHolder holder, int position) {
        PjPengusulan pjPengusulan = listData.get(position);
        holder.nama_pj_pengusulan.setText(pjPengusulan.nama_pj_pengusulan);
        holder.jumlahpj_pengusulan.setText(Integer.toString(pjPengusulan.jumlahpj_pengusulan));

    }


    @Override
    public int getItemCount() {
        return listData.size();
    }


}


