package com.example.asetdsi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asetdsi.adapter.DetailFormPengusulanBarangAdapter;
import com.example.asetdsi.adapter.DetailFormPengusulanMaintenenceAdapter;
import com.example.asetdsi.model.ArrayMt;
import com.example.asetdsi.model.DetailFormPengusulanMaintenence;
import com.example.asetdsi.model.PengusulanBarang;
import com.example.asetdsi.model.PengusulanBarangItem;
import com.example.asetdsi.model.PengusulanMaintenence;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Detail_Form_Pengusulan_Maintenence_Activity extends AppCompatActivity {

    RecyclerView rvDetailPengusulanMt;
    ActionBar actionBar;
    Button BtnTambahPengusulanMt;
    TextInputEditText keterangan_detail_pengusulanmt;
    RecyclerView rvDetailPhotoMtRv;
    ArrayList<DetailFormPengusulanMaintenence> listData = new ArrayList<>();
    ArrayList<ArrayMt> listArray = new ArrayList<>();
//    ArrayList<PengusulanBarangItem> aset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form_pengusulan_maintenence);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));
        keterangan_detail_pengusulanmt = (TextInputEditText) findViewById(R.id.keterangan_detail_pengusulanmt);

//        listData = (ArrayList<DetailFormPengusulanMaintenence>) getIntent().getExtras().getSerializable("list");
        listArray = (ArrayList<ArrayMt>) getIntent().getExtras().getSerializable("list");

//        DetailFormPengusulanMaintenenceAdapter adapter = new DetailFormPengusulanMaintenenceAdapter();
//        adapter.setListData(getPeminjaman());

        rvDetailPengusulanMt = findViewById(R.id.rvDetailPengusulanMt);
//        rvDetailPengusulanMt.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailPengusulanMt.setLayoutManager(layoutManager);
        rvDetailPengusulanMt.setAdapter(new DetailFormPengusulanMaintenenceAdapter(listArray));


//        listData = (ArrayList<PengusulanBarang>) getIntent().getExtras().getSerializable("list");
//
//        Log.e("JSONArray", String.valueOf(listData));

//
//        rvDetailPengusulanMt.setAdapter(new DetailFormPengusulanBarangAdapter(listData));

    }

//    public ArrayList<DetailFormPengusulanMaintenence> getPeminjaman(){
//        ArrayList<DetailFormPengusulanMaintenence> listData = new ArrayList<DetailFormPengusulanMaintenence>();
////        listData.add(new DetailFormPengusulanMaintenence());
//
//        listData.add(new DetailFormPengusulanMaintenence(
//                "cek",
//                "buruk",
//                "gatau"
//        ));
//            listData.add(new DetailFormPengusulanMaintenence(
//                    "woi",
//                    "buruk",
//                    "gatau apa"
//            ));
//            listData.add(new DetailFormPengusulanMaintenence(
//                    "cek",
//                    "buruk",
//                    "gatau"
//            ));
//            listData.add(new DetailFormPengusulanMaintenence(
//                    "woi",
//                    "buruk",
//                    "gatau apa"
//            ));
//            listData.add(new DetailFormPengusulanMaintenence(
//                    "cek",
//                    "buruk",
//                    "gatau"
//            ));
//            listData.add(new DetailFormPengusulanMaintenence(
//                    "woi",
//                    "buruk",
//                    "gatau apa"
//            ));
//
//            return listData;
//    }

    public void TambahPengusulanMaintenence(View view) {
        //
    }
}
