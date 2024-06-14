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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.DetailHistoryPengusulanAdapter;
import com.example.asetdsi.adapter.DetailOngoingPengusulanAdapter;
import com.example.asetdsi.model.DetailHistoryPengusulan;
import com.example.asetdsi.model.DetailHistoryPengusulanItem;
import com.example.asetdsi.model.DetailHistoryPengusulanResponse;
import com.example.asetdsi.model.DetailOngoingPengusulan;
import com.example.asetdsi.model.DetailOngoingPengusulanItem;
import com.example.asetdsi.model.DetailOngoingPengusulanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailOngoingPengusulanActivity extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView rvDetailOpeng;
    TextView keterangan_detail_openg;
    TextView status_detail_openg;
    TextView status_detail_openg_fakultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ongoing_pengusulan);


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent OngoingPengusulanIntent = getIntent();
        Integer id  = OngoingPengusulanIntent.getIntExtra("id",0);

        String keterangan_ongoing = OngoingPengusulanIntent.getStringExtra("keterangan");
        keterangan_detail_openg= findViewById(R.id.keterangan_detail_openg);
        keterangan_detail_openg.setText(keterangan_ongoing);
//
        String status = OngoingPengusulanIntent.getStringExtra("status");
         status_detail_openg = findViewById(R.id.status_detail_openg);
        status_detail_openg.setText(status);

        String statusfk = OngoingPengusulanIntent.getStringExtra("status_fk");
        status_detail_openg_fakultas = findViewById(R.id.status_detail_openg_fakultas);
        status_detail_openg_fakultas.setText(statusfk);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        DetailOngoingPengusulanAdapter adapter = new DetailOngoingPengusulanAdapter();

        rvDetailOpeng = findViewById(R.id.rvDetailOpeng);
        rvDetailOpeng.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailOpeng.setLayoutManager(layoutManager);


//Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DetailOngoingPengusulanResponse> call = client.getDetailOngoingPengusulan("Bearer "+accessToken,id);
        call.enqueue(new Callback<DetailOngoingPengusulanResponse>() {
            @Override
            public void onResponse(Call<DetailOngoingPengusulanResponse> call, Response<DetailOngoingPengusulanResponse> response) {
                DetailOngoingPengusulanResponse detailOngoingPengusulanResponse = response.body();
                ArrayList<DetailOngoingPengusulan> listDetailOPeng = new ArrayList<DetailOngoingPengusulan>();
                if(detailOngoingPengusulanResponse != null){
                    List<DetailOngoingPengusulanItem> listDetailOngoingPengusulanItem = detailOngoingPengusulanResponse.getData();
//                    Log.d("oi54", String.valueOf(listHomeBarangItem.size()));
                    for(DetailOngoingPengusulanItem detailOngoingPengusulanItem : listDetailOngoingPengusulanItem){
                        DetailOngoingPengusulan detailOngoingPengusulan = new DetailOngoingPengusulan(
                                detailOngoingPengusulanItem.getAssetName(),
                                detailOngoingPengusulanItem.getSpesificationDetail(),
                                detailOngoingPengusulanItem.getAmount(),
                                detailOngoingPengusulanItem.getUnitPrice(),
                                detailOngoingPengusulanItem.getSourceShop(),
                                detailOngoingPengusulanItem.getStatuspr(),
                                detailOngoingPengusulanItem.getStatusconfirmfaculty()

                        );
                        listDetailOPeng.add(detailOngoingPengusulan);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listDetailOPeng);
            }

            @Override
            public void onFailure(Call<DetailOngoingPengusulanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
                Log.d("p3s4n",t.getMessage());
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
}
