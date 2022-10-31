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

import com.example.asetdsi.DaftarBarangActivity;
import com.example.asetdsi.DetailHistoryPeminjamanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.HistoryPeminjamanAdapter;
import com.example.asetdsi.adapter.PJAdapter;
import com.example.asetdsi.model.DetailHistoryPeminjaman;
import com.example.asetdsi.model.HistoryPeminjaman;
import com.example.asetdsi.model.HistoryPeminjamanItem;
import com.example.asetdsi.model.HistoryPeminjamanResponse;
import com.example.asetdsi.model.ListPeminjamanBarangItem;
import com.example.asetdsi.model.ListPeminjamanBarangResponse;
import com.example.asetdsi.model.Peminjaman;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryPeminjamanFragment extends Fragment implements HistoryPeminjamanAdapter.OnHistoryPeminjamanHolderClickListener{

    View v;
    private RecyclerView rvHistoryPeminjaman ;
    private List<HistoryPeminjaman> listHistoryPeminjaman;

    public static HistoryPeminjamanFragment getInstance(){
        HistoryPeminjamanFragment historyPeminjamanFragment = new HistoryPeminjamanFragment();
        return historyPeminjamanFragment;
    }

    public HistoryPeminjamanFragment() {
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

        v= inflater.inflate(R.layout.fragment_history_peminjaman, container, false);
        HistoryPeminjamanAdapter adapter = new HistoryPeminjamanAdapter();
//        adapter.setListData(getHistory());
        adapter.setListener(this);
        rvHistoryPeminjaman = (RecyclerView) v.findViewById(R.id.rvHistoryPeminjaman);
        rvHistoryPeminjaman.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHistoryPeminjaman.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<HistoryPeminjamanResponse> call = client.getHistoryPeminjaman("Bearer "+accessToken);
        call.enqueue(new Callback<HistoryPeminjamanResponse>() {
            @Override
            public void onResponse(Call<HistoryPeminjamanResponse> call, Response<HistoryPeminjamanResponse> response) {
                HistoryPeminjamanResponse listHistoryPeminjamanResponse = response.body();
                ArrayList<HistoryPeminjaman> listHistoryPeminjaman = new ArrayList<HistoryPeminjaman>();
                if(listHistoryPeminjamanResponse != null){
                    List<HistoryPeminjamanItem> listHistoryPeminjamanItem = listHistoryPeminjamanResponse.getData();
//                    Log.d("oi54", String.valueOf(listHistoryPeminjamanItem.size()));
                    for(HistoryPeminjamanItem historyPeminjamanItem : listHistoryPeminjamanItem){
                        HistoryPeminjaman historyPeminjaman = new HistoryPeminjaman(
                                historyPeminjamanItem.getDeskripsi(),
                                historyPeminjamanItem.getWaktu(),
                                historyPeminjamanItem.getTanggal(),
                                historyPeminjamanItem.getStatus(),
                                historyPeminjamanItem.getTypeId(),
                                historyPeminjamanItem.getId()


                        );
                        listHistoryPeminjaman.add(historyPeminjaman);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();


                }
                adapter.setListData(listHistoryPeminjaman);
            }

            @Override
            public void onFailure(Call<HistoryPeminjamanResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();




            }
        });


        return v;
    }


    @Override
    public void onClick(View view, HistoryPeminjaman historyPeminjaman) {
        Intent HistorypeminjamanIntent = new Intent(getContext(), DetailHistoryPeminjamanActivity.class);
        HistorypeminjamanIntent.putExtra("id",historyPeminjaman.id);
        HistorypeminjamanIntent.putExtra("keterangan_history",historyPeminjaman.keterangan_history);
        HistorypeminjamanIntent.putExtra("tanggal_history",historyPeminjaman.tanggal_history);
        HistorypeminjamanIntent.putExtra("jam_history",historyPeminjaman.jam_history);
        HistorypeminjamanIntent.putExtra("status_history",historyPeminjaman.status_history);
//        HistorypeminjamanIntent.putExtra("nama_pj",pj.nama_pj);
        startActivity(HistorypeminjamanIntent);
        Toast.makeText(getActivity(),"yay uncul",Toast.LENGTH_SHORT).show();
    }
}