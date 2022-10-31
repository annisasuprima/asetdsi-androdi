package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import com.example.asetdsi.adapter.PJAdapter;
import com.example.asetdsi.adapter.PeminjamanAdapter;
import com.example.asetdsi.model.Barang;
import com.example.asetdsi.model.DaftarBarangItem;
import com.example.asetdsi.model.DaftarBarangResponse;
import com.example.asetdsi.model.PJItem;
import com.example.asetdsi.model.PJResponse;
import com.example.asetdsi.model.Pj;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PJActivity extends AppCompatActivity implements PJAdapter.OnPjHolderClickListener{
    RecyclerView rvPj;
    private Button  BtnPjLihat;
    private CardView pjCv;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pjactivity);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        PJAdapter adapter = new PJAdapter();
//        adapter.setListData(getPj());
        adapter.setListener(this);

        rvPj = findViewById(R.id.rvPj);
        rvPj.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPj.setLayoutManager(layoutManager);


//Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<PJResponse> call = client.getPJ("Bearer "+accessToken);
        call.enqueue(new Callback<PJResponse>() {
            @Override
            public void onResponse(Call<PJResponse> call, Response<PJResponse> response) {
                PJResponse pjResponse = response.body();
                ArrayList<Pj> listPj = new ArrayList<Pj>();
                if(pjResponse != null){
                    List<PJItem> listPJItem = pjResponse.getData();
//                    Log.d("oi54", String.valueOf(listPJItem.size()));
                    for(PJItem pjItem : listPJItem){
                        Pj pj = new Pj(
                                pjItem.getPicName(),
                                pjItem.getJumlah(),
                                pjItem.getId()

                        );
                        listPj.add(pj);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listPj);
            }

            @Override
            public void onFailure(Call<PJResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view,Pj pj) {
        Intent peminjamanIntent = new Intent(this,PeminjamanActivity.class);
        peminjamanIntent.putExtra("nama_pj",pj.nama_pj);
        peminjamanIntent.putExtra("id",pj.id);
        startActivity(peminjamanIntent);
        Toast.makeText(this,"yay uncul",Toast.LENGTH_SHORT).show();
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