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
import com.example.asetdsi.adapter.PJPengusulanAdapter;
import com.example.asetdsi.model.AuthData;
import com.example.asetdsi.model.PJItem;
import com.example.asetdsi.model.PJPengusulanItem;
import com.example.asetdsi.model.PJPengusulanResponse;
import com.example.asetdsi.model.PJResponse;
import com.example.asetdsi.model.Pj;
import com.example.asetdsi.model.PjPengusulan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PJPengusulanActivity extends AppCompatActivity implements PJPengusulanAdapter.OnPjPengusulanHolderClickListener {

    RecyclerView rvPjPengusulan;
    private CardView pjPengusulanCv;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pjpengusulan);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        PJPengusulanAdapter adapter = new PJPengusulanAdapter();
//        adapter.setListData(getPjPengusulan());
        adapter.setListener(this);

        rvPjPengusulan = findViewById(R.id.rvPjPengusulan);
        rvPjPengusulan.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPjPengusulan.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<PJPengusulanResponse> call = client.getPJPengusulan("Bearer "+accessToken);
        call.enqueue(new Callback<PJPengusulanResponse>() {
            @Override
            public void onResponse(Call<PJPengusulanResponse> call, Response<PJPengusulanResponse> response) {
                PJPengusulanResponse pjPengusulanResponse = response.body();
                ArrayList<PjPengusulan> listPjPengusulan = new ArrayList<PjPengusulan>();
                if(pjPengusulanResponse != null){
                    List<PJPengusulanItem> listPJPengusulanItem = pjPengusulanResponse.getData();
//                    Log.d("oi54", String.valueOf(listPJItem.size()));
                    for(PJPengusulanItem pjItem : listPJPengusulanItem){
                        PjPengusulan pjPengusulan = new PjPengusulan(
                                pjItem.getPicName(),
                                pjItem.getJumlah(),
                                pjItem.getId()





                        );
                        listPjPengusulan.add(pjPengusulan);
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listPjPengusulan);
            }

            @Override
            public void onFailure(Call<PJPengusulanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
            }
        });

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

    @Override
    public void onClick(View view, PjPengusulan pjPengusulan) {

        SharedPreferences preferences =
                getSharedPreferences("com.example.asetdsi.PREFS", MODE_PRIVATE);

        SharedPreferences.Editor pjedit = preferences.edit();
        pjedit.putString("id", String.valueOf(pjPengusulan.id));
        pjedit.apply();


        Intent pengusulanIntent = new Intent(this,PengusulanActivity.class);
        pengusulanIntent.putExtra("nama_pj_pengusulan",pjPengusulan.nama_pj_pengusulan);
        pengusulanIntent.putExtra("id",pjPengusulan.id);
        startActivity(pengusulanIntent);

        Intent pengusulanIntent2 = new Intent(this,Detail_Form_Pengusulan_Barang_Activity.class);
        pengusulanIntent2.putExtra("id",pjPengusulan.id);
        Toast.makeText(this,"yay uncul",Toast.LENGTH_SHORT).show();

    }
}