package com.example.asetdsi.adapter;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.R;
import com.example.asetdsi.model.ArrayPhotoMt;
import com.example.asetdsi.model.DetailFormPengusulanBarang;
import com.example.asetdsi.model.ListBarangMaintenence;
import com.example.asetdsi.model.ListPhotoMaintenence;
import com.example.asetdsi.model.Peminjaman;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListBarangMaintenenceAdapter extends RecyclerView.Adapter<ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder> {

    ArrayList<ListBarangMaintenence> listData = new ArrayList<ListBarangMaintenence>();
    public ArrayList<ListBarangMaintenence> checkedlistData = new ArrayList<ListBarangMaintenence>();

  

//    public ListBarangMaintenenceAdapter(Context context) {
//        this.context = context;
//    }

    Context context;

    public class ListBarangMaintenenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nama_brg_maintenence;
        TextView merk_brg_maintenence;
        TextView kondisi_brg_maintenence;
        ImageView gambar_brg_maintenence;
        TextView item_code;
        CheckBox checkBoxMt;
        PengusulanClickListener pengusulanClickListener;

        public ListBarangMaintenenceViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_brg_maintenence = itemView.findViewById(R.id.nama_brg_maintenence);
            merk_brg_maintenence = itemView.findViewById(R.id.merk_brg_maintenence);
            kondisi_brg_maintenence=itemView.findViewById(R.id.kondisi_brg_maintenence);
            gambar_brg_maintenence=(ImageView) itemView.findViewById(R.id.gambar_brg_maintenence);
            item_code=itemView.findViewById(R.id.item_code);
            checkBoxMt=(CheckBox)itemView.findViewById(R.id.checkBoxMt);

            checkBoxMt.setOnClickListener(this);



        }

        public void setItemClickListener(PengusulanClickListener ic)
        {
            this.pengusulanClickListener=ic;
        }


        @Override
        public void onClick(View view) {
//            if(listener != null){
//                listener.onClick(view, listData.get(getAdapterPosition()));
//            }
            this.pengusulanClickListener.onItemClick(view,getLayoutPosition());

        }
    }

    //Click Listener
//    ListBarangMaintenenceAdapter.OnListBarangMaintenenceAdapterHolderClickListener listener = null;
//
//    public interface OnListBarangMaintenenceAdapterHolderClickListener{
//        void onClick(View view, ListBarangMaintenence listBarangMaintenence);
//    }
//
//    public void setListener(ListBarangMaintenenceAdapter.OnListBarangMaintenenceAdapterHolderClickListener listener) {
//        this.listener = listener;
//    }

    public void setListData(ArrayList<ListBarangMaintenence> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_barang_maintenence, parent,false);
        return new ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBarangMaintenenceAdapter.ListBarangMaintenenceViewHolder holder, int position) {

       ListBarangMaintenence listBarangMaintenence = listData.get(position);
        holder.nama_brg_maintenence.setText(listBarangMaintenence.nama_brg_maintenence);
        holder.merk_brg_maintenence.setText(listBarangMaintenence.merk_brg_maintenence);
        holder.kondisi_brg_maintenence.setText(listBarangMaintenence.kondisi_brg_maintenence);
        holder.item_code.setText(listBarangMaintenence.item_code);

        String file_name = listBarangMaintenence.gambar_brg_maintenence;
        String url = file_name;
        Picasso.get().load(url).into(holder.gambar_brg_maintenence);

        holder.setItemClickListener(new PengusulanClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox checkBoxMt = (CheckBox) v;

                if (checkBoxMt.isChecked()) {
                    TextView nama_brg_maintenence = holder.nama_brg_maintenence;
                    String nama_barang = nama_brg_maintenence.getText().toString();

                    TextView merk_brg_maintenence = holder.merk_brg_maintenence;
                    String merk_barang = merk_brg_maintenence.getText().toString();

                    listData.get(pos).setNama_brg_maintenence(nama_barang);
                    listData.get(pos).setMerk_brg_maintenence(merk_barang);
                    listData.get(pos).getId();
                    checkedlistData.add(listData.get(pos));

                } else if (!checkBoxMt.isChecked()) {
                    checkedlistData.remove(listData.get(pos));
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return listData.size();
    }

    public ArrayList<ListBarangMaintenence> getCheckedlistData(){
        return checkedlistData;
    }


}
