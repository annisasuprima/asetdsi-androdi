package com.example.asetdsi.fragment;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asetdsi.FormPeminjamanBangunanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.PeminjamanBangunanAdapter;
import com.example.asetdsi.model.ListPeminjamanBangunanItem;
import com.example.asetdsi.model.ListPeminjamanBangunanResponse;
import com.example.asetdsi.model.PeminjamanBangunan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BangunanFragment extends Fragment implements PeminjamanBangunanAdapter.OnPeminjamanBangunanHolderClickListener{
    View v;
    private RecyclerView rvBgn;
    private List<PeminjamanBangunan> listPeminjamanBangunan;
    Button BtnTambahBangunan;

    public static BangunanFragment getInstance(){
        BangunanFragment bangunanFragment = new BangunanFragment();
        return bangunanFragment;
    }

    public BangunanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v= inflater.inflate(R.layout.fragment_bangunan, container, false);

        // Inflate the layout for this fragment
        SharedPreferences preferences = getContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );


        Intent peminjamanIntent = getActivity().getIntent();
        Integer id  = peminjamanIntent.getIntExtra("id",0);


        PeminjamanBangunanAdapter adapter = new PeminjamanBangunanAdapter();
//        adapter.setListData(getPeminjaman());
        adapter.setListener(this);

        rvBgn = (RecyclerView) v.findViewById(R.id.rvBgn);
        rvBgn.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvBgn.setLayoutManager(layoutManager);


        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<ListPeminjamanBangunanResponse> call = client.getPeminjamanBangunan("Bearer "+accessToken,id);
        call.enqueue(new Callback<ListPeminjamanBangunanResponse>() {
            @Override
            public void onResponse(Call<ListPeminjamanBangunanResponse> call, Response<ListPeminjamanBangunanResponse> response) {
                ListPeminjamanBangunanResponse listPeminjamanBangunanResponse = response.body();
                ArrayList<PeminjamanBangunan> listPeminjamanBangunan = new ArrayList<PeminjamanBangunan>();
                if(listPeminjamanBangunanResponse != null){
                    List<ListPeminjamanBangunanItem> listPeminjamanBangunanItem = listPeminjamanBangunanResponse.getData();
                    Log.d("oi54", String.valueOf(listPeminjamanBangunanItem.size()));
                    for(ListPeminjamanBangunanItem peminjamanBangunanItem : listPeminjamanBangunanItem){
                        PeminjamanBangunan peminjamanBangunan = new PeminjamanBangunan(
                                peminjamanBangunanItem.getBuildingName(),
                                peminjamanBangunanItem.getAvailable(),
                                peminjamanBangunanItem.getPhoto(),
                                peminjamanBangunanItem.getBuildingId(),
                                peminjamanBangunanItem.getPicId()


                        );
                        listPeminjamanBangunan.add(peminjamanBangunan);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();


                }
                adapter.setListData(listPeminjamanBangunan);
            }

            @Override
            public void onFailure(Call<ListPeminjamanBangunanResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();


            }
        });

        return v;
    }


    @Override
    public void onClick(View view, PeminjamanBangunan peminjamanBangunan) {

        Intent PeminjamanBangunanIntent = new Intent(getContext(), FormPeminjamanBangunanActivity.class);
        PeminjamanBangunanIntent.putExtra("nama_bangunan",peminjamanBangunan.nama_bgn_pnj);
        PeminjamanBangunanIntent.putExtra("gambar",peminjamanBangunan.gambarbgn_pnj);
        PeminjamanBangunanIntent.putExtra("building_id",peminjamanBangunan.building_id);
        PeminjamanBangunanIntent.putExtra("pic_id",peminjamanBangunan.pic_id);
        startActivity(PeminjamanBangunanIntent);
//            Toast.makeText(getActivity(),"yay uncul",Toast.LENGTH_SHORT).show();

    }

}
