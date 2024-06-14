package com.example.asetdsi.fragment;

import static android.app.Activity.RESULT_OK;
import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Detail_Form_Pengusulan_Maintenence_Activity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.ListBarangMaintenenceAdapter;
import com.example.asetdsi.adapter.ListPickPhotoMtAdapter;
import com.example.asetdsi.model.DaftarBarangMaintenenceItem;
import com.example.asetdsi.model.DaftarBarangMaintenenceResponse;
import com.example.asetdsi.model.DetailFormPengusulanMaintenence;
import com.example.asetdsi.model.ListBarangMaintenence;
import com.example.asetdsi.model.ListPickPhotoMt;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PengusulanMaintenenceFragment extends Fragment implements View.OnClickListener {
    View v;
    LinearLayout layout_barang_mt;
    Button BtnTambahBarangmt;
    Button BtnRemove;
    Button BtnTambahDoneMt;
    Button BtnFotomt;
    RecyclerView rVlistPhotoMt;
    StringBuffer ab=null;
    private RecyclerView rvBarangMaintenence;
    TextInputEditText deskripsi_permasalahan;
    ArrayList<DetailFormPengusulanMaintenence> listData = new ArrayList<DetailFormPengusulanMaintenence>();
    ArrayList<Uri> uri = new ArrayList<>();
    ListPickPhotoMtAdapter listPickPhotoMtAdapter;

    private static final int Read_Permission = 101;
    private static final int PICK_IMAGE = 1;

    public PengusulanMaintenenceFragment() {
        // Required empty public constructor
    }

    public static PengusulanMaintenenceFragment getInstance(){
        PengusulanMaintenenceFragment pengusulanMaintenenceFragment = new PengusulanMaintenenceFragment();
        return pengusulanMaintenenceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_pengusulan_maintenence, container, false);


        layout_barang_mt = v.findViewById(R.id.layout_barang_mt);
        BtnTambahBarangmt = (Button)v.findViewById(R.id.BtnTambahBarangmt);
        BtnRemove = (Button)v.findViewById(R.id.BtnRemove);

//        BtnFotomt = (Button)v.findViewById(R.id.BtnFotomt);



        BtnTambahBarangmt.setOnClickListener(this);

        //Button
        BtnTambahDoneMt = (Button) v.findViewById(R.id.BtnTambahDoneMt);
        BtnTambahDoneMt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfValidAndRead()) {
                    Intent intent = new Intent(getActivity(), Detail_Form_Pengusulan_Maintenence_Activity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",listData);
//                    bundle.putSerializable("pic_id",pengusulanBarang.pic_id);
                    intent.putExtras(bundle);
                    startActivity(intent);


//
//                    //Sini ChekBox
//                    ab=new StringBuffer();
//                    for (ListBarangMaintenence listBarangMaintenence : adapter.checkedlistData)
//                    {
//                        ab.append(listBarangMaintenence.nama_brg_maintenence);
//                        ab.append("\n");
//                        ab.append(listBarangMaintenence.id);
//                        ab.append("\n");
//                    }
//                    if(adapter.checkedlistData.size()>0){
//                        Intent intent = new Intent(getActivity(), FormPengusulanMaintenenceActivity.class);
//                        ListBarangMaintenence listBarangMaintenence = new ListBarangMaintenence();
//                        intent.putExtra("nama_barang",listBarangMaintenence.nama_brg_maintenence);
//                        intent.putExtra("inventory_item_id",listBarangMaintenence.id);
////            Bundle bundle = new Bundle();
////            bundle.putSerializable("listPeminjaman",adapter.checkedlistData);
////            intent.putExtras(bundle);
//                        startActivity(intent);
//
//                        Toast.makeText(getContext(),ab.toString(),Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getContext(),"Please Check An Item First",Toast.LENGTH_SHORT).show();
//                    }
////SampaiSini


                }

            }
        });

        return v;
    }

    private boolean checkIfValidAndRead() {
        listData.clear();
        boolean result = true;

        for (int i=0;i<layout_barang_mt.getChildCount();i++) {
            View BarangView = layout_barang_mt.getChildAt(i);

            deskripsi_permasalahan = (TextInputEditText)BarangView.findViewById(R.id.deskripsi_permasalahan);

            ListBarangMaintenenceAdapter adapter = new ListBarangMaintenenceAdapter();
            rvBarangMaintenence = (RecyclerView) BarangView.findViewById(R.id.rvBarangMaintenence);

            DetailFormPengusulanMaintenence detailFormPengusulanMaintenence= new DetailFormPengusulanMaintenence();

            if(!deskripsi_permasalahan.getText().toString().equals("")){
                detailFormPengusulanMaintenence.setPermasalahan_pengusulan_mt(deskripsi_permasalahan.getText().toString());

            }else{
                result = false;
                break;
            }



//            if()

//            if(!deskripsi_permasalahan.getText().toString().equals("")){
//                pengusulanMaintenence.setDeskripsi_permasalahan(deskripsi_permasalahan.getText().toString());
//
//            }else{
//                result = false;
//                break;
//            }

            listData.add(detailFormPengusulanMaintenence);

        }

        if(listData.size()==0){
            result = false;
            Toast.makeText(getContext(),"Tambahkan Barang Terlebih Dahulu",Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(getContext(),"Tambahkan Barang Dengan Benar",Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    @Override
    public void onClick(View view) {
        addView();
    }

    private void addView() {

        SharedPreferences preferences = getContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent pengusulanIntent = getActivity().getIntent();
        Integer id  = pengusulanIntent.getIntExtra("id",0);

        final View BarangMtView = getLayoutInflater().inflate(R.layout.row_add_barang_mt,null,false);

        TextView deskripsi_permasalahan = (TextView)BarangMtView.findViewById(R.id.deskripsi_permasalahan);
        Button BtnFotomt = (Button)BarangMtView.findViewById(R.id.BtnFotomt);
        Button BtnRemove = (Button)BarangMtView.findViewById(R.id.BtnRemove);
        rVlistPhotoMt =(RecyclerView)BarangMtView.findViewById(R.id.rVlistPhotoMt);

        ListBarangMaintenenceAdapter adapter = new ListBarangMaintenenceAdapter();
//        adapter.setListData(getListMaintenence());

        rvBarangMaintenence = (RecyclerView) BarangMtView.findViewById(R.id.rvBarangMaintenence);
        rvBarangMaintenence.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvBarangMaintenence.setLayoutManager(layoutManager);


        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DaftarBarangMaintenenceResponse> call = client.getBarangMt("Bearer "+accessToken,id);
        call.enqueue(new Callback<DaftarBarangMaintenenceResponse>() {
            @Override
            public void onResponse(Call<DaftarBarangMaintenenceResponse> call, Response<DaftarBarangMaintenenceResponse> response) {
                DaftarBarangMaintenenceResponse daftarBarangMaintenenceResponse = response.body();
                ArrayList<ListBarangMaintenence> listBarangMaintenence = new ArrayList<ListBarangMaintenence>();
                if(daftarBarangMaintenenceResponse != null){
                    List<DaftarBarangMaintenenceItem> daftarBarangMaintenenceItem = daftarBarangMaintenenceResponse.getData();
//                    Log.d("oi54", String.valueOf(listPeminjamanBarangItem.size()));
                    for(DaftarBarangMaintenenceItem pengusulanBarangItem : daftarBarangMaintenenceItem){
                        ListBarangMaintenence barangMaintenence = new ListBarangMaintenence(
                                pengusulanBarangItem.getAssetName(),
                                pengusulanBarangItem.getInventoryBrand(),
                                pengusulanBarangItem.getCondition(),
                                pengusulanBarangItem.getPhoto(),
                                pengusulanBarangItem.getItemCode(),
                                pengusulanBarangItem.getId()

                        );
                        listBarangMaintenence.add(barangMaintenence);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();


                }
                adapter.setListData(listBarangMaintenence);
            }

            @Override
            public void onFailure(Call<DaftarBarangMaintenenceResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();


            }
        });

        BtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(BarangMtView);
            }
        });

        //Photo
        listPickPhotoMtAdapter = new ListPickPhotoMtAdapter(uri);
        rVlistPhotoMt.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rVlistPhotoMt.setAdapter(listPickPhotoMtAdapter) ;



        BtnFotomt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                ){
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Read_Permission);

                    return;
                }

                 Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                 intent.setType("image/*");
                 if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                     intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                 }
//                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
            }

        });

        layout_barang_mt.addView(BarangMtView);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
                if(data.getClipData() != null){
                    int countOfImages = data.getClipData().getItemCount();
                    for (int i=0;i<countOfImages;i++){
                        Uri imageuri = data.getClipData().getItemAt(i).getUri();
                        uri.add(imageuri);
                    }
                }else {
                    Uri imageuri = data.getData();
                    uri.add(imageuri);
                }
                listPickPhotoMtAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getContext(),"You haven't pick any image",Toast.LENGTH_SHORT).show();
            }



    }

    private void removeView(View view){
        layout_barang_mt.removeView(view);
    }

//    private ArrayList<ListBarangMaintenence> getListMaintenence() {
//        ArrayList<ListBarangMaintenence> listBarangMaintenence = new ArrayList<ListBarangMaintenence>();
//        listBarangMaintenence.add(new ListBarangMaintenence(
//                "Meja",
//                "High Point",
//                "Buruk"
//        ));
//
//        listBarangMaintenence.add(new ListBarangMaintenence(
//                "AC",
//                "Panasonic",
//                "Buruk"
//        ));
//
//        return listBarangMaintenence;
//    }
}