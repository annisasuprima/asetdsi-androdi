package com.example.asetdsi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asetdsi.model.PengusulanBarang;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NewPengusulunBarangFormActivity extends AppCompatActivity implements View.OnClickListener {

    ActionBar actionBar;
    LinearLayout newlayout_barang;
    Button BtnNewTambahBarang;
    Button BtnNewRemoveBarang;
    Button BtnNewTambahDoneBrg;
    TextInputEditText keterangan_pengusulanbrg;
    TextInputEditText nama_pengusulan_barang,detail_spesifikasi_pengusulan_barang,jumlah_pengusulan_barang,
            harga_pengusulan_barang,sumber_pengusulan_barang;
    String rproposal_description, rasset_name, rspesification_detail,
            ramount, runit_price, rsource_shop;

    ArrayList<PengusulanBarang> listData = new ArrayList<PengusulanBarang>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pengusulun_barang_form);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        newlayout_barang = findViewById(R.id.newlayout_barang);
        BtnNewTambahBarang = (Button)findViewById(R.id.BtnNewTambahBarang);
        BtnNewRemoveBarang = (Button)findViewById(R.id.BtnRemoveBarang);
//        keterangan_pengusulanbrg = (TextInputEditText) v.findViewById(R.id.keterangan_pengusulanbrg);



        BtnNewTambahBarang.setOnClickListener(this);
//        BtnTambahDoneBrg.setOnClickListener(this);

        BtnNewTambahDoneBrg = (Button) findViewById(R.id.BtnNewTambahDoneBrg);
        BtnNewTambahDoneBrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfValidAndRead()){
                    Intent intent = new Intent(getApplicationContext(),Detail_Form_Pengusulan_Barang_Activity.class);
                    PengusulanBarang pengusulanBarang = new PengusulanBarang();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",listData);
//                    bundle.putSerializable("pic_id",pengusulanBarang.pic_id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }


            }
        });



    }


    @Override
    public void onClick(View view) {
            addView();
    }

    private boolean checkIfValidAndRead() {
        listData.clear();
        boolean result = true;

        for (int i=0;i<newlayout_barang.getChildCount();i++) {
            View BarangView = newlayout_barang.getChildAt(i);

            nama_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.nama_pengusulan_barang);
            detail_spesifikasi_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.detail_spesifikasi_pengusulan_barang);
            jumlah_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.jumlah_pengusulan_barang);
            harga_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.harga_pengusulan_barang);
            sumber_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.sumber_pengusulan_barang);


            PengusulanBarang pengusulanBarang= new PengusulanBarang();

            if(!nama_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setNama_pengusulan_barang(nama_pengusulan_barang.getText().toString());

            }else{
                result = false;
                break;
            }

            if(!detail_spesifikasi_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setDetail_spesifikasi_pengusulan_barang(detail_spesifikasi_pengusulan_barang.getText().toString());
            }else{
                result = false;
                break;
            }

            if(!jumlah_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setJumlah_pengusulan_barang(Integer.valueOf(jumlah_pengusulan_barang.getText().toString()));
            }else{
                result = false;
                break;
            }

            if (!harga_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setHarga_pengusulan_barang(Integer.valueOf(harga_pengusulan_barang.getText().toString()));
            }else {
                result =false;
                break;
            }

            if (!sumber_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setSumber_pengusulan_barang(sumber_pengusulan_barang.getText().toString());
            }else{
                result = false;
                break;
            }


            listData.add(pengusulanBarang);

        }

        if(listData.size()==0){
            result = false;
            Toast.makeText(getApplicationContext(),"Tambahkan Barang Terlebih Dahulu",Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(getApplicationContext(),"Tambahkan Barang Dengan Benar",Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    private void addView() {
        TextInputEditText nama_pengusulan_barang;
        TextInputEditText detail_spesifikasi_pengusulan_barang;
        TextInputEditText jumlah_pengusulan_barang;
        TextInputEditText harga_pengusulan_barang;
        TextInputEditText sumber_pengusulan_barang;

        final View BarangView = getLayoutInflater().inflate(R.layout.row_add_barang,null,false);

//        DISINI


//        TextView keterangan_pengusulanbrg = (TextView)BarangView.findViewById(R.id.keterangan_pengusulan_barang);
        Button BtnRemoveBarang = (Button)BarangView.findViewById(R.id.BtnRemoveBarang);
        nama_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.nama_pengusulan_barang);
        detail_spesifikasi_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.detail_spesifikasi_pengusulan_barang);
        jumlah_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.jumlah_pengusulan_barang);
        harga_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.harga_pengusulan_barang);
        sumber_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.sumber_pengusulan_barang);




//        String nama = nama_pengusulan_barang.getText().toString();
//        Log.d("y43", nama);
//
//        List<PengusulanBarangItem> listPengusulan= new ArrayList<PengusulanBarangItem>();
//        listPengusulan.add(new PengusulanBarangItem(
//                nama_pengusulan_barang.getText().toString(),
//                detail_spesifikasi_pengusulan_barang.getText().toString(),
//                Integer.valueOf(jumlah_pengusulan_barang.getText().toString()),
//                Integer.valueOf(harga_pengusulan_barang.getText().toString()),
//                sumber_pengusulan_barang.getText().toString()
//        ));
//
//
//        Log.d("y43", nama_pengusulan_barang.toString());



        BtnRemoveBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(BarangView);
            }
        });

        newlayout_barang.addView(BarangView);
    }

    private void removeView(View view) {

        newlayout_barang.removeView(view);
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