package com.example.asetdsi.fragment;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asetdsi.DetaillOngoingPeminjamanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.OngoingPeminjamanAdapter;
import com.example.asetdsi.model.OngoingPeminjaman;
import com.example.asetdsi.model.OngoingPeminjamanItem;
import com.example.asetdsi.model.OngoingPeminjamanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OngoingPeminjamanFragment extends Fragment implements OngoingPeminjamanAdapter.OnOngoingPeminjamanHolderClickListener {

    View v;
    private RecyclerView rvOngoingPeminjaman ;
    private List<OngoingPeminjaman> listOngoingPeminjaman;

    public static OngoingPeminjamanFragment getInstance() {
        OngoingPeminjamanFragment ongoingPeminjamanFragment = new OngoingPeminjamanFragment();
        return ongoingPeminjamanFragment;
    }
    public OngoingPeminjamanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SharedPreferences preferences = getContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        v= inflater.inflate(R.layout.fragment_ongoing_peminjaman, container, false);
        OngoingPeminjamanAdapter adapter = new OngoingPeminjamanAdapter();

        adapter.setListener(this);
        rvOngoingPeminjaman = (RecyclerView) v.findViewById(R.id.rvOngoingPeminjaman);
        rvOngoingPeminjaman.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvOngoingPeminjaman.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<OngoingPeminjamanResponse> call = client.getOngoingPeminjaman("Bearer "+accessToken);
        call.enqueue(new Callback<OngoingPeminjamanResponse>() {
            @Override
            public void onResponse(Call<OngoingPeminjamanResponse> call, Response<OngoingPeminjamanResponse> response) {
                OngoingPeminjamanResponse listOngoingPeminjamanResponse = response.body();
                ArrayList<OngoingPeminjaman> listOngoingPeminjaman = new ArrayList<OngoingPeminjaman>();
                if(listOngoingPeminjamanResponse != null){
                    List<OngoingPeminjamanItem> listOngoingPeminjamanItem = listOngoingPeminjamanResponse.getData();
                    Log.d("oi54", String.valueOf(listOngoingPeminjamanItem.size()));
                    for(OngoingPeminjamanItem ongoingPeminjamanItem : listOngoingPeminjamanItem){
                        OngoingPeminjaman ongoingPeminjaman = new OngoingPeminjaman(
                                ongoingPeminjamanItem.getDeskripsi(),
                                ongoingPeminjamanItem.getWaktu(),
                                ongoingPeminjamanItem.getWaktuAkhir(),
                                ongoingPeminjamanItem.getTanggal(),
                                ongoingPeminjamanItem.getStatus(),
                                ongoingPeminjamanItem.getTypeId(),
                                ongoingPeminjamanItem.getId()




                        );
                        listOngoingPeminjaman.add(ongoingPeminjaman);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();


                }
                adapter.setListData(listOngoingPeminjaman);
            }

            @Override
            public void onFailure(Call<OngoingPeminjamanResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();


            }
        });

        return v;

    }


    @Override
    public void onClick(View view, OngoingPeminjaman ongoingPeminjaman) {
        Intent OngoingpeminjamanIntent = new Intent(getContext(), DetaillOngoingPeminjamanActivity.class);
        OngoingpeminjamanIntent.putExtra("id",ongoingPeminjaman.id);
        OngoingpeminjamanIntent.putExtra("keterangan_history",ongoingPeminjaman.keterangan_ongoing);
        OngoingpeminjamanIntent.putExtra("tanggal_history",ongoingPeminjaman.tanggal_ongoing);
        OngoingpeminjamanIntent.putExtra("jam_history",ongoingPeminjaman.jam_ongoing);
        OngoingpeminjamanIntent.putExtra("jam_history_end",ongoingPeminjaman.jam_ongoing_end);
        OngoingpeminjamanIntent.putExtra("status_history",ongoingPeminjaman.status_ongoing);
//        HistorypeminjamanIntent.putExtra("nama_pj",pj.nama_pj);
        startActivity(OngoingpeminjamanIntent);

//        Toast.makeText(getActivity(),"yay uncul",Toast.LENGTH_SHORT).show();

    }
}