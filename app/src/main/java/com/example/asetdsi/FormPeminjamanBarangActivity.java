package com.example.asetdsi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.asetdsi.adapter.DetailFormPengusulanBarangAdapter;
import com.example.asetdsi.adapter.FormPeminjamanBarangAdapter;
import com.example.asetdsi.model.FormPeminjamanBarang;
import com.example.asetdsi.model.Peminjaman;
import com.example.asetdsi.model.PengusulanBarang;
import com.example.asetdsi.model.Pj;

import java.util.ArrayList;

public class FormPeminjamanBarangActivity extends AppCompatActivity implements FormPeminjamanBarangAdapter.OnFormPeminjamanBarangHolderClickListener{
    RecyclerView rvFormPeminjamanBarang;
    ActionBar actionBar;
    ArrayList<Peminjaman> listData = new ArrayList<Peminjaman>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_peminjaman_barang);

//        listData = (ArrayList<Peminjaman>) getIntent().getExtras().getSerializable("list");
//        FormPeminjamanBarangAdapter adapter = new FormPeminjamanBarangAdapter(listData);
//        adapter.setListData(listData);
//        adapter.setListener(this);


//
        rvFormPeminjamanBarang = findViewById(R.id.formPeminjamanBarangRv);
        rvFormPeminjamanBarang.setAdapter(new FormPeminjamanBarangAdapter(listData));

        Intent intent = getIntent();
//        String array = intent.getStringExtra("list_check");


//        Log.e("JSONArray", String.valueOf(listData));

//
//        rvFormPeminjamanBarang.setAdapter(new FormPeminjamanBarangAdapter(listData));

//        Log.d("y43",array);
//        listData = (ArrayList<FormPeminjamanBarang>) getIntent().getSerializableExtra("list_check");

//        listData = getIntent().getStringArrayListExtra("list_check");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvFormPeminjamanBarang.setLayoutManager(layoutManager);

    }

//    public ArrayList<FormPeminjamanBarang> getFormPeminjaman(){
//        ArrayList<FormPeminjamanBarang> listFormPeminjamanBarang = new ArrayList<FormPeminjamanBarang>();
//        listFormPeminjamanBarang.add(new FormPeminjamanBarang(
//                "Meja",
//                "High Point",
//                1
//
//        ));
//        listFormPeminjamanBarang.add(new FormPeminjamanBarang(
//                "Infocus",
//                "Epson",
//                2
//        ));
//        listFormPeminjamanBarang.add(new FormPeminjamanBarang(
//                "Kursi",
//                "Futura",
//                10
//        ));
//
//        return listFormPeminjamanBarang;
//    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view, Peminjaman peminjaman) {
        Toast.makeText(this,"yay uncul",Toast.LENGTH_SHORT).show();

    }
}