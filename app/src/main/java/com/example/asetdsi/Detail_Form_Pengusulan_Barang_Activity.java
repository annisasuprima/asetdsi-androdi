package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.DetailFormPengusulanBarangAdapter;
import com.example.asetdsi.adapter.PJAdapter;
import com.example.asetdsi.model.DetailFormPengusulanBarang;
import com.example.asetdsi.model.FormPeminjamanBangunanResponse;
import com.example.asetdsi.model.PengusulanBarang;
import com.example.asetdsi.model.PengusulanBarangItem;
import com.example.asetdsi.model.PengusulanBarangResponse;
import com.example.asetdsi.model.Pj;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail_Form_Pengusulan_Barang_Activity extends AppCompatActivity{

    RecyclerView rvDetailPengusulanBarang;
    ActionBar actionBar;
    Button BtnTambahLagiPengusulan;
    TextInputEditText keterangan_detail_pengusulanbrg;
    ArrayList<PengusulanBarang> listData = new ArrayList<>();
    ArrayList<PengusulanBarangItem > aset = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form_pengusulan_barang);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));
        keterangan_detail_pengusulanbrg = (TextInputEditText) findViewById(R.id.keterangan_detail_pengusulanbrg);

        rvDetailPengusulanBarang = findViewById(R.id.rvDetailPengusulanBarang);
//        rvDetailPengusulanBarang.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailPengusulanBarang.setLayoutManager(layoutManager);


        listData = (ArrayList<PengusulanBarang>) getIntent().getExtras().getSerializable("list");
//
//        Log.e("JSONArray", String.valueOf(listData));

//
        rvDetailPengusulanBarang.setAdapter(new DetailFormPengusulanBarangAdapter(listData));
        //Button
//        BtnTambahLagiPengusulan = findViewById(R.id.BtnTambahLagiPengusulan);
//        BtnTambahLagiPengusulan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TambahFormPengusulanBarangActivity();
//            }
//        });

    }
//    public void TambahFormPengusulanBarangActivity(){
//        Intent intent = new Intent(this,OngoingActivity.class);
//        startActivity(intent);
//    }

    public void TambahPengusulanBarang(View view) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        keterangan_detail_pengusulanbrg = (TextInputEditText) findViewById(R.id.keterangan_detail_pengusulanbrg);
        String keterangan = keterangan_detail_pengusulanbrg.getText().toString();


        Integer id = Integer.valueOf(preferences.getString("id",""));
//        Log.d("y43", String.valueOf(id));


        listData = (ArrayList<PengusulanBarang>) getIntent().getExtras().getSerializable("list");



//        aset = (ArrayList<PengusulanBarangItem>) getIntent().getExtras().getSerializable("list");


//        Log.d("y43",listData.toString());

        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<PengusulanBarangResponse> call = client.getPengusulanbarang("Bearer "+accessToken,id,keterangan,listData);
        call.enqueue(new Callback<PengusulanBarangResponse>() {
            @Override
            public void onResponse(Call<PengusulanBarangResponse> call, Response<PengusulanBarangResponse> response) {

//                try {
//                    if(response.body()!=null)
//                        Toast.makeText(Detail_Form_Pengusulan_Barang_Activity.this,"Berhasil", Toast.LENGTH_LONG).show();
//                    if(response.errorBody()!=null)
//                        Toast.makeText(Detail_Form_Pengusulan_Barang_Activity.this," response message "+response.errorBody().string(),Toast.LENGTH_LONG).show();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }

                if(response.body() != null && response.isSuccessful()) {
//                    Toast.makeText(Detail_Form_Pengusulan_Barang_Activity.this, response.body().getProposalDescription(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Detail_Form_Pengusulan_Barang_Activity.this, OngoingActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal Sini", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<PengusulanBarangResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }


}