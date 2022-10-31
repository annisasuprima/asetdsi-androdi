package com.example.asetdsi.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.FormPeminjamanBarangActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.model.Peminjaman;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeminjamanAdapter extends RecyclerView.Adapter<PeminjamanAdapter.PeminjamanViewHolder> {

    int count = 0;
    Context context;
    ArrayList<Peminjaman> listData = new ArrayList<Peminjaman>();
    public ArrayList<Peminjaman> checkedlistData = new ArrayList<Peminjaman>();

    public PeminjamanAdapter(Context context) {
        this.context = context;
    }




//    interface OnItemCheckListener {
//        void onItemCheck(Peminjaman item);
//        void onItemUncheck(Peminjaman item);
//    }
//
//    @NonNull
//    private OnItemCheckListener onItemClick;
//
//
//    public PeminjamanAdapter (ArrayList<Peminjaman> listData, @NonNull OnItemCheckListener onItemCheckListener) {
//        this.listData = listData;
//        this.onItemClick = onItemCheckListener;
//    }


//    public PeminjamanAdapter(Context context, ArrayList<Peminjaman> listData) {
//        this.context = context;
//        this.listData = listData;
//    }

//    public PeminjamanAdapter() {
//
//    }

//    public class PeminjamanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public static class PeminjamanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView namabrg_pnj;
        TextView merkbrg_pnj;
        TextView jumlahbrg_pnj;
        ImageView gambarbrg_pnj;
        TextView value_jumlah;
        ImageButton btn_tambah_pnj;
        ImageButton btn_kurang_pnj;
        CheckBox checkBox;
        PeminjamanClickListener peminjamanClickListener;

//        ItemClickListener itemClickListener;
        public PeminjamanViewHolder(@NonNull View itemView) {
            super(itemView);
            namabrg_pnj = itemView.findViewById(R.id.namabrg_pnj);
            merkbrg_pnj = itemView.findViewById(R.id.merkbrg_pnj);
            jumlahbrg_pnj = itemView.findViewById(R.id.jumlahbrg_pnj);
            gambarbrg_pnj = itemView.findViewById(R.id.gambarbrg_pnj);
            value_jumlah = itemView.findViewById(R.id.value_jumlah);
            btn_tambah_pnj = (ImageButton)itemView.findViewById(R.id.btn_tambah_pnj);
            btn_kurang_pnj = (ImageButton)itemView.findViewById(R.id.btn_kurang_pnj);
            checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);


            checkBox.setOnClickListener(this);

//            checkBox.setOnClickListener(this);


        }

        public void setItemClickListener(PeminjamanClickListener ic)
        {
            this.peminjamanClickListener=ic;
        }
            @Override
            public void onClick(View view) {
                this.peminjamanClickListener.onItemClick(view,getLayoutPosition());
            }
        }
    public void setListData(ArrayList<Peminjaman> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PeminjamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_peminjaman, parent,false);
        return new PeminjamanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeminjamanViewHolder holder, int position) {
//        final  int pos = position;
//        Peminjaman peminjaman = listData.get(position);
//        String file_name = peminjaman.gambarbrg_pnj;
//        String url = file_name;
//        holder.value_jumlah.setText("1");
//        holder.namabrg_pnj.setText(peminjaman.namabrg_pnj);
//        holder.merkbrg_pnj.setText(peminjaman.merkbrg_pnj);
//        holder.jumlahbrg_pnj.setText(Integer.toString(peminjaman.jumlahbrg_pnj));
//        Picasso.get().load(url).into(holder.gambarbrg_pnj);

        final Peminjaman peminjaman1 = listData.get(position);
        String file_name = peminjaman1.gambarbrg_pnj;
        String url = file_name;
        holder.value_jumlah.setText("1");
        holder.namabrg_pnj.setText(peminjaman1.namabrg_pnj);
        holder.merkbrg_pnj.setText(peminjaman1.merkbrg_pnj);
        holder.jumlahbrg_pnj.setText(Integer.toString(peminjaman1.jumlahbrg_pnj));
        Picasso.get().load(url).into(holder.gambarbrg_pnj);


//        total = peminjaman.jumlahbrg_pnj;

        holder.btn_tambah_pnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count >= peminjaman1.jumlahbrg_pnj){
                    return;
                }
                count++;
                holder.value_jumlah.setText("" + count);
            }

        });

        holder.btn_kurang_pnj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (count <= peminjaman1.jumlahbrg_pnj) {
                        if(count<=1){
                            count=1;
                        }
                        else {count--;}
                        holder.value_jumlah.setText(""+count);
                    }

                }
            });

        holder.setItemClickListener(new PeminjamanClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                CheckBox checkBox = (CheckBox) v;
                if (checkBox.isChecked()) {
                    checkedlistData.add(listData.get(pos));

                } else if (!checkBox.isChecked()) {
                    checkedlistData.remove(listData.get(pos));
                }

//                if (checkBox.isChecked()) {
//                    checkedlistData.add(peminjaman1);
//
//                } else if (!checkBox.isChecked()) {
//                    checkedlistData.remove(peminjaman1);
//                }


            }


        });


    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public ArrayList<Peminjaman> getCheckedlistData(){
        return checkedlistData;
    }

}
