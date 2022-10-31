package com.example.asetdsi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

public class FormPengusulanBarangActivity extends AppCompatActivity {
    ActionBar actionBar;
    Button BtnTambahPengusulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengusulan_barang);

        Intent peminjamanIntent = getIntent();
        String nama_pj_pengusulan = peminjamanIntent.getStringExtra("nama_pj_pengusulan");


        actionBar = getSupportActionBar();
        actionBar.setTitle(nama_pj_pengusulan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}