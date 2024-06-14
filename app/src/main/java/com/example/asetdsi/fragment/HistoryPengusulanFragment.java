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

import com.example.asetdsi.DetailHistoryPengusulanActivity;
import com.example.asetdsi.DetailHistoryPengusulanMaintenenceActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.HistoryPengusulanAdapter;
import com.example.asetdsi.model.HistoryPengusulan;
import com.example.asetdsi.model.HistoryPengusulanItem;
import com.example.asetdsi.model.HistoryPengusulanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryPengusulanFragment extends Fragment implements HistoryPengusulanAdapter.OnHistoryPengusulanHolderClickListener {

    View v;
    private RecyclerView rvHistoryPengusulan ;
    private List<HistoryPengusulan> listHistoryPengusulan;


    public static HistoryPengusulanFragment getInstance(){
        HistoryPengusulanFragment historyPengusulanFragment = new HistoryPengusulanFragment();
        return historyPengusulanFragment;
    }


    public HistoryPengusulanFragment() {
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
        v= inflater.inflate(R.layout.fragment_history_pengusulan, container, false);
        HistoryPengusulanAdapter adapter = new HistoryPengusulanAdapter();

        adapter.setListener(this);
        rvHistoryPengusulan = (RecyclerView) v.findViewById(R.id.rvHistoryPengusulan);
        rvHistoryPengusulan.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHistoryPengusulan.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<HistoryPengusulanResponse> call = client.getHistoryPengusulan("Bearer "+accessToken);

        call.enqueue(new Callback<HistoryPengusulanResponse>() {
            @Override
            public void onResponse(Call<HistoryPengusulanResponse> call, Response<HistoryPengusulanResponse> response) {
                HistoryPengusulanResponse listHistoryPengusulanResponse = response.body();
                ArrayList<HistoryPengusulan> listHistoryPengusulan = new ArrayList<HistoryPengusulan>();
                if(listHistoryPengusulanResponse != null){
                    List<HistoryPengusulanItem> listHistoryPengusulanItem = listHistoryPengusulanResponse.getData();
                    Log.d("oi54", String.valueOf(listHistoryPengusulanItem.size()));
                    for(HistoryPengusulanItem historyPengusulanItem : listHistoryPengusulanItem){
                        HistoryPengusulan historyPengusulan = new HistoryPengusulan(
                                historyPengusulanItem.getDeskripsi(),
                                historyPengusulanItem.getStatuspr(),
                                historyPengusulanItem.getStatusconfirmfaculty(),
                                historyPengusulanItem.getAlasan(),
                                historyPengusulanItem.getTypeId(),
                                historyPengusulanItem.getId()

                        );
                        listHistoryPengusulan.add(historyPengusulan);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();


                }
                adapter.setListData(listHistoryPengusulan);
            }

            @Override
            public void onFailure(Call<HistoryPengusulanResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();


            }
        });


        return v;
    }

    public String nama_barang_detail_peng;
    public String detail_spesifikasi_detail_peng;
    public int jumlah_barang_detail_peng;
    public int harga_barang_detail_peng;
    public String sumber_toko_peng;
    public int type_id;


    @Override
    public void onClick(View view, HistoryPengusulan historyPengusulan) {
        if(historyPengusulan.type_id==1){
            Intent HistorypengusulanIntent = new Intent(getContext(), DetailHistoryPengusulanActivity.class);
                HistorypengusulanIntent.putExtra("keterangan",historyPengusulan.keterangan_history_pengusulan);
                HistorypengusulanIntent.putExtra("status",historyPengusulan.status_history_pengusulan);
            HistorypengusulanIntent.putExtra("status_fk",historyPengusulan.status_history_pengusulan_fakultas);
                HistorypengusulanIntent.putExtra("id",historyPengusulan.id);
            HistorypengusulanIntent.putExtra("alasan",historyPengusulan.alasan_pengusulan);
                startActivity(HistorypengusulanIntent);
//            Toast.makeText(getActivity(),"yay uncul",Toast.LENGTH_SHORT).show();

        }
        else{
            Intent HistorypengusulanIntent = new Intent(getContext(), DetailHistoryPengusulanMaintenenceActivity.class);
            HistorypengusulanIntent.putExtra("keterangan",historyPengusulan.keterangan_history_pengusulan);
            HistorypengusulanIntent.putExtra("status",historyPengusulan.status_history_pengusulan);
            HistorypengusulanIntent.putExtra("status_fk",historyPengusulan.status_history_pengusulan_fakultas);
            HistorypengusulanIntent.putExtra("id",historyPengusulan.id);
            HistorypengusulanIntent.putExtra("alasan",historyPengusulan.alasan_pengusulan);
            startActivity(HistorypengusulanIntent);
        }

    }
}