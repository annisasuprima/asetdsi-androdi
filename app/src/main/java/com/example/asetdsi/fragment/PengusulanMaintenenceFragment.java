package com.example.asetdsi.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asetdsi.FormPeminjamanBarangActivity;
import com.example.asetdsi.HomeActivity;
import com.example.asetdsi.OngoingPengusulanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.adapter.ListBarangMaintenenceAdapter;
import com.example.asetdsi.model.ListBarangMaintenence;
import com.example.asetdsi.model.Peminjaman;

import java.util.ArrayList;
import java.util.List;


public class PengusulanMaintenenceFragment extends Fragment implements View.OnClickListener {
    View v;
    LinearLayout layout_barang_mt;
    Button BtnTambahBarangmt;
    Button BtnRemove;
    Button BtnTambahDoneMt;
    private RecyclerView rvBarangMaintenence;

    List<String> listbarangmt = new ArrayList<>();
    public PengusulanMaintenenceFragment() {
        // Required empty public constructor
    }

    public static PengusulanMaintenenceFragment getInstance(){
        PengusulanMaintenenceFragment pengusulanMaintenenceFragment = new PengusulanMaintenenceFragment();
        return pengusulanMaintenenceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_pengusulan_maintenence, container, false);


        layout_barang_mt = v.findViewById(R.id.layout_barang_mt);
        BtnTambahBarangmt = (Button)v.findViewById(R.id.BtnTambahBarangmt);
        BtnRemove = (Button)v.findViewById(R.id.BtnRemove);

        BtnTambahBarangmt.setOnClickListener(this);

        //Button
        BtnTambahDoneMt = (Button) v.findViewById(R.id.BtnTambahDoneMt);
        BtnTambahDoneMt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OngoingPengusulanActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onClick(View view) {
        addView();
    }

    private void addView() {

        final View BarangMtView = getLayoutInflater().inflate(R.layout.row_add_barang_mt,null,false);

        TextView deskripsi_permasalahan = (TextView)BarangMtView.findViewById(R.id.deskripsi_permasalahan);
        Button BtnFotomt = (Button)BarangMtView.findViewById(R.id.BtnFotomt);
        Button BtnRemove = (Button)BarangMtView.findViewById(R.id.BtnRemove);

        ListBarangMaintenenceAdapter adapter = new ListBarangMaintenenceAdapter();
        adapter.setListData(getListMaintenence());

        rvBarangMaintenence = (RecyclerView) BarangMtView.findViewById(R.id.rvBarangMaintenence);
        rvBarangMaintenence.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvBarangMaintenence.setLayoutManager(layoutManager);

        BtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(BarangMtView);
            }
        });

        layout_barang_mt.addView(BarangMtView);

    }

    private void removeView(View view){
        layout_barang_mt.removeView(view);
    }

    private ArrayList<ListBarangMaintenence> getListMaintenence() {
        ArrayList<ListBarangMaintenence> listBarangMaintenence = new ArrayList<ListBarangMaintenence>();
        listBarangMaintenence.add(new ListBarangMaintenence(
                "Meja",
                "High Point",
                "Buruk"
        ));

        listBarangMaintenence.add(new ListBarangMaintenence(
                "AC",
                "Panasonic",
                "Buruk"
        ));

        return listBarangMaintenence;
    }
}