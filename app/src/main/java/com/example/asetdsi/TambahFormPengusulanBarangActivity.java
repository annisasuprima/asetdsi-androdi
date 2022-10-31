package com.example.asetdsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TambahFormPengusulanBarangActivity extends AppCompatActivity {

    Button BtnTambahLagiPengusulanBarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_form_pengusulan_barang);

        //Button
        BtnTambahLagiPengusulanBarang = findViewById(R.id.BtnTambahLagiPengusulanBarang);
        BtnTambahLagiPengusulanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Detail_Form_Pengusulan_Barang_Activity();
            }
        });

    }
    public void Detail_Form_Pengusulan_Barang_Activity(){
        Intent intent = new Intent(this,Detail_Form_Pengusulan_Barang_Activity.class);
        startActivity(intent);
    }

}