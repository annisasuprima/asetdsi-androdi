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
import com.example.asetdsi.DetailOngoingPengusulanActivity;
import com.example.asetdsi.DetailOngoingPengusulanMaintenenceActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.OngoingPeminjamanAdapter;
import com.example.asetdsi.adapter.OngoingPengusulanAdapter;
import com.example.asetdsi.model.HistoryPengusulan;
import com.example.asetdsi.model.HistoryPengusulanItem;
import com.example.asetdsi.model.HistoryPengusulanResponse;
import com.example.asetdsi.model.OngoingPeminjaman;
import com.example.asetdsi.model.OngoingPengusulan;
import com.example.asetdsi.model.OngoingPengusulanItem;
import com.example.asetdsi.model.OngoingPengusulanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OngoingPengusulanFragment extends Fragment implements OngoingPengusulanAdapter.OnOngoingPengusulanHolderClickListener {

    View v;
    private RecyclerView rvOngoingPengusulan ;
    private List<OngoingPengusulan> listOngoingPengusulan;

    public OngoingPengusulanFragment() {
        // Required empty public constructor
    }

    public static OngoingPengusulanFragment getInstance() {
        OngoingPengusulanFragment ongoingPengusulanFragment = new OngoingPengusulanFragment();
        return ongoingPengusulanFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences preferences = getContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_ongoing_pengusulan, container, false);
        OngoingPengusulanAdapter adapter = new OngoingPengusulanAdapter();

        adapter.setListener(this);

        rvOngoingPengusulan = (RecyclerView) v.findViewById(R.id.rvOngoingPengusulan);
        rvOngoingPengusulan.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvOngoingPengusulan.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<OngoingPengusulanResponse> call = client.getOngoingPengusulan("Bearer "+accessToken);
        call.enqueue(new Callback<OngoingPengusulanResponse>() {
            @Override
            public void onResponse(Call<OngoingPengusulanResponse> call, Response<OngoingPengusulanResponse> response) {
                OngoingPengusulanResponse listOngoingPengusulanResponse = response.body();
                ArrayList<OngoingPengusulan> listOngoingPengusulan = new ArrayList<OngoingPengusulan>();
                if(listOngoingPengusulanResponse != null){
                    List<OngoingPengusulanItem> listOngoingPengusulanItem = listOngoingPengusulanResponse.getData();
                    Log.d("oi54", String.valueOf(listOngoingPengusulanItem.size()));
                    for(OngoingPengusulanItem ongoingPengusulanItem : listOngoingPengusulanItem){
                        OngoingPengusulan ongoingPengusulan = new OngoingPengusulan(
                                ongoingPengusulanItem.getDeskripsi(),
                                ongoingPengusulanItem.getStatuspr(),
                                ongoingPengusulanItem.getTypeId(),
                                ongoingPengusulanItem.getId()



                        );
                        listOngoingPengusulan.add(ongoingPengusulan);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();


                }
                adapter.setListData(listOngoingPengusulan);
            }

            @Override
            public void onFailure(Call<OngoingPengusulanResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();


            }
        });

        return v;
    }


    @Override
    public void onClick(View view, OngoingPengusulan ongoingPengusulan) {
        if(ongoingPengusulan.type_id==1){
            Intent OngoingpengusulanIntent = new Intent(getContext(), DetailOngoingPengusulanActivity.class);
            OngoingpengusulanIntent.putExtra("keterangan",ongoingPengusulan.ket_op);
            OngoingpengusulanIntent.putExtra("status",ongoingPengusulan.status_ongoing_pengusulan);
            OngoingpengusulanIntent.putExtra("id",ongoingPengusulan.id);
            startActivity(OngoingpengusulanIntent);
//            Toast.makeText(getActivity(),"yay uncul",Toast.LENGTH_SHORT).show();

        }
        else{
            Intent OngoingpengusulanIntent = new Intent(getContext(), DetailOngoingPengusulanMaintenenceActivity.class);
            OngoingpengusulanIntent.putExtra("keterangan",ongoingPengusulan.ket_op);
            OngoingpengusulanIntent.putExtra("status",ongoingPengusulan.status_ongoing_pengusulan);
            OngoingpengusulanIntent.putExtra("id",ongoingPengusulan.id);
            startActivity(OngoingpengusulanIntent);
        }
    }
}