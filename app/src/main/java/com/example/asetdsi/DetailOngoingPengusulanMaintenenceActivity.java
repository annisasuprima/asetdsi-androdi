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
import com.example.asetdsi.adapter.DetailHistoryPengusulanMaintenenceAdapter;
import com.example.asetdsi.adapter.DetailOngoingPengusulanMaintenenceAdapter;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenence;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenenceItem;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenenceResponse;
import com.example.asetdsi.model.DetailOngoingPengusulanMaintenence;
import com.example.asetdsi.model.DetailOngoingPengusulanMaintenenceItem;
import com.example.asetdsi.model.DetailOngoingPengusulanMaintenenceResponse;
import com.example.asetdsi.model.PhotosItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailOngoingPengusulanMaintenenceActivity extends AppCompatActivity {

    ActionBar actionBar;
    TextView keterangan_detail_openg_mt;
    TextView status_detail_openg_mt;
    RecyclerView rvDetailOPengMt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ongoing_pengusulan_maintenence);

        ArrayList<DetailOngoingPengusulanMaintenence> listData = new ArrayList<DetailOngoingPengusulanMaintenence>();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent OngoingpengusulanIntent = getIntent();
        Integer id  = OngoingpengusulanIntent.getIntExtra("id",0);

        String keterangan_ongoing = OngoingpengusulanIntent.getStringExtra("keterangan");
        keterangan_detail_openg_mt= findViewById(R.id.keterangan_detail_openg_mt);
        keterangan_detail_openg_mt.setText(keterangan_ongoing);

        String status = OngoingpengusulanIntent.getStringExtra("status");
        status_detail_openg_mt = findViewById(R.id.status_detail_openg_mt);
        status_detail_openg_mt.setText(status);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        DetailOngoingPengusulanMaintenenceAdapter adapter = new DetailOngoingPengusulanMaintenenceAdapter();

        rvDetailOPengMt = findViewById(R.id.rvDetailOPengMt);
        rvDetailOPengMt.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailOPengMt.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DetailOngoingPengusulanMaintenenceResponse> call = client.getDetailOngoingPengusulanMaintenence("Bearer "+accessToken,id);
        call.enqueue(new Callback<DetailOngoingPengusulanMaintenenceResponse>() {
            @Override
            public void onResponse(Call<DetailOngoingPengusulanMaintenenceResponse> call, Response<DetailOngoingPengusulanMaintenenceResponse> response) {
                DetailOngoingPengusulanMaintenenceResponse detailOngoingPengusulanMaintenenceResponse = response.body();
                ArrayList<DetailOngoingPengusulanMaintenence> listDetailOPengMt = new ArrayList<DetailOngoingPengusulanMaintenence>();
//                ArrayList<PhotosItem> listPhotoItem = new ArrayList<PhotosItem>();
                if(detailOngoingPengusulanMaintenenceResponse != null){
                    List<DetailOngoingPengusulanMaintenenceItem> listDetailOngoingPengusulanMaintenenceItem = detailOngoingPengusulanMaintenenceResponse.getData();

                    for(DetailOngoingPengusulanMaintenenceItem detailOngoingPengusulanMaintenenceItem : listDetailOngoingPengusulanMaintenenceItem){
                        DetailOngoingPengusulanMaintenence detailOngoingPengusulanMaintenence = new DetailOngoingPengusulanMaintenence(
                                detailOngoingPengusulanMaintenenceItem.getInventoryBrand(),
                                detailOngoingPengusulanMaintenenceItem.getCondition(),
                                detailOngoingPengusulanMaintenenceItem.getProblemDescription(),
                                detailOngoingPengusulanMaintenenceItem.getIdReqMaintenence()


                        );

//                        PhotosItem listPhotoMaintenences = new PhotosItem(
//                        detailHistoryPengusulanMaintenenceItem.getPhotos()
//                        );

//                        listPhotoItem.add(listPhotoMaintenences);
                        listDetailOPengMt.add(detailOngoingPengusulanMaintenence);

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server Ni",Toast.LENGTH_SHORT).show();

                }
//                adapter1.setListData(listPhotoItem);
                adapter.setListData(listDetailOPengMt);
            }

            @Override
            public void onFailure(Call<DetailOngoingPengusulanMaintenenceResponse> call, Throwable t) {
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