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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asetdsi.FormPeminjamanBarangActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.PeminjamanAdapter;
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

public class BarangFragment extends Fragment {
    View v;
    RecyclerView rvPnj ;
    StringBuffer ab=null;
    EditText value_jumlah;
    Button BtnTambahBarang;
    public static BarangFragment getInstance(){
        BarangFragment barangFragment = new BarangFragment();
        return barangFragment;
    }

    public BarangFragment() {
        // Required empty public constructor
    }

    ArrayList<Peminjaman> listData = new ArrayList<Peminjaman>();
    ArrayList<Peminjaman> checkedlist = new ArrayList<Peminjaman>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_barang, container, false);

        // Inflate the layout for this fragment
        SharedPreferences preferences = getContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );
        Intent peminjamanIntent = getActivity().getIntent();
        Integer id  = peminjamanIntent.getIntExtra("id",0);
        PeminjamanAdapter adapter = new PeminjamanAdapter(getContext());
        rvPnj = (RecyclerView) v.findViewById(R.id.rvPnj);
        rvPnj.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvPnj.setLayoutManager(layoutManager);
        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);
        Call<ListPeminjamanBarangResponse> call = client.getPeminjamanBarang("Bearer "+accessToken,id);
        call.enqueue(new Callback<ListPeminjamanBarangResponse>() {
            @Override
            public void onResponse(Call<ListPeminjamanBarangResponse> call, Response<ListPeminjamanBarangResponse> response) {
                ListPeminjamanBarangResponse listPeminjamanBarangResponse = response.body();
                ArrayList<Peminjaman> listPeminjaman = new ArrayList<Peminjaman>();
                if(listPeminjamanBarangResponse != null){
                    List<ListPeminjamanBarangItem> listPeminjamanBarangItem = listPeminjamanBarangResponse.getData();
                    Log.d("oi54", String.valueOf(listPeminjamanBarangItem.size()));
                    for(ListPeminjamanBarangItem peminjamanBarangItem : listPeminjamanBarangItem){
                        Peminjaman peminjaman = new Peminjaman(
                                peminjamanBarangItem.getAssetName(),
                                peminjamanBarangItem.getInventoryBrand(),
                                peminjamanBarangItem.getJumlah(),
                                peminjamanBarangItem.getPhoto(),
                                peminjamanBarangItem.getInventoryId()
                        );
                        listPeminjaman.add(peminjaman);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();
                }
                adapter.setListData(listPeminjaman);
            }

            @Override
            public void onFailure(Call<ListPeminjamanBarangResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
            }
        });

        //Button
        BtnTambahBarang = (Button) v.findViewById(R.id.BtnTambahBarang);
        BtnTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ab=new StringBuffer();
                boolean result = true;

                for (Peminjaman peminjaman : adapter.checkedlistData)
                {
                    ab.append(peminjaman.namabrg_pnj);
                    ab.append("\n");
                    ab.append(peminjaman.value_jumlah);
                    ab.append("\n");
                }
                if(adapter.checkedlistData.size()>0){
                    Intent intent = new Intent(getActivity(), FormPeminjamanBarangActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("listPeminjaman",adapter.checkedlistData);
                    intent.putExtras(bundle);
                    startActivity(intent);

//                    Toast.makeText(getContext(),ab.toString(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Please Check An Item First",Toast.LENGTH_SHORT).show();
                }
            }

        });
        return v;
    }


}