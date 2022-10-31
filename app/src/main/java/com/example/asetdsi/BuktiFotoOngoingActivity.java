package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.ListPhotoAdapter;
import com.example.asetdsi.adapter.ListPhotoOngoingAdapter;
import com.example.asetdsi.model.BuktiFotoItem;
import com.example.asetdsi.model.BuktiFotoOngoingItem;
import com.example.asetdsi.model.BuktiFotoOngoingResponse;
import com.example.asetdsi.model.BuktiFotoResponse;
import com.example.asetdsi.model.ListPhotoMaintenence;
import com.example.asetdsi.model.ListPhotoOngoingMaintenence;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuktiFotoOngoingActivity extends AppCompatActivity {
    RecyclerView rvBuktiOpeng;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_foto_ongoing);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent  BuktiMtIntent = getIntent();
        Integer id  = BuktiMtIntent.getIntExtra("id_req_maintenence",0);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        ListPhotoOngoingAdapter adapter = new ListPhotoOngoingAdapter();

        rvBuktiOpeng = findViewById(R.id.rvBuktiOpeng);
        rvBuktiOpeng.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvBuktiOpeng.setLayoutManager(layoutManager);

    //Object Retrofit

    String accessToken = preferences.getString("ACCESS_TOKEN","");


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            ;

    PortalClient client = retrofit.create(PortalClient.class);

    Call<BuktiFotoOngoingResponse> call = client.getBuktiFotoMt("Bearer "+ accessToken,id);
        call.enqueue(new Callback<BuktiFotoOngoingResponse>() {
        @Override
        public void onResponse(Call<BuktiFotoOngoingResponse> call, Response<BuktiFotoOngoingResponse> response) {
            BuktiFotoOngoingResponse buktiFotoOngoingResponse = response.body();
            ArrayList<ListPhotoOngoingMaintenence> listPhotoOngoingMaintenence = new ArrayList<ListPhotoOngoingMaintenence>();
            if(buktiFotoOngoingResponse != null){
                List<BuktiFotoOngoingItem> listBuktiFotoOngoingItem = buktiFotoOngoingResponse.getData();
//                    Log.d("oi54", String.valueOf(listHomeBarangItem.size()));
                for(BuktiFotoOngoingItem buktiFotoOngoingItem : listBuktiFotoOngoingItem){
                    ListPhotoOngoingMaintenence listPhotoOMt = new ListPhotoOngoingMaintenence(
                            buktiFotoOngoingItem.getPhotoName()

                    );
                    listPhotoOngoingMaintenence.add(listPhotoOMt);
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

            }
            adapter.setListData(listPhotoOngoingMaintenence);
        }

        @Override
        public void onFailure(Call<BuktiFotoOngoingResponse> call, Throwable t) {
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


}

