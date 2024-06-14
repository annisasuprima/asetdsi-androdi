package com.example.asetdsi.fragment;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Detail_Form_Pengusulan_Barang_Activity;
import com.example.asetdsi.FormPeminjamanBarangActivity;
import com.example.asetdsi.HomeActivity;
import com.example.asetdsi.LoginActivity;
import com.example.asetdsi.OngoingActivity;
import com.example.asetdsi.OngoingPengusulanActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.RegisterActivity;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.ListBarangMaintenenceAdapter;
import com.example.asetdsi.model.AuthClass;
import com.example.asetdsi.model.AuthData;
import com.example.asetdsi.model.Barang;
import com.example.asetdsi.model.EditProfileClass;
import com.example.asetdsi.model.EditProfileData;
import com.example.asetdsi.model.FormPeminjamanBangunanItem;
import com.example.asetdsi.model.PeminjamanBangunan;
import com.example.asetdsi.model.PengusulanBarang;
import com.example.asetdsi.model.PengusulanBarangItem;
import com.example.asetdsi.model.PengusulanBarangResponse;
import com.example.asetdsi.model.RegisterClass;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PengusulanBarangFragment extends Fragment implements View.OnClickListener{

    View v;
    ActionBar actionBar;
    LinearLayout layout_barang;
    Button BtnTambahBarang;
    Button BtnRemoveBarang;
    Button BtnTambahDoneBrg;
    TextInputEditText keterangan_pengusulanbrg;
    TextInputEditText nama_pengusulan_barang,detail_spesifikasi_pengusulan_barang,jumlah_pengusulan_barang,
            harga_pengusulan_barang,sumber_pengusulan_barang;
    String rproposal_description, rasset_name, rspesification_detail,
    ramount, runit_price, rsource_shop;
    Button BtnTambahPengusulan;
    public PengusulanBarangFragment() {
        // Required empty public constructor
    }

    public static PengusulanBarangFragment getInstance(){
        PengusulanBarangFragment pengusulanBarangFragment = new PengusulanBarangFragment();
        return pengusulanBarangFragment;
    }

    ArrayList<PengusulanBarang> listData = new ArrayList<PengusulanBarang>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_pengusulan_barang, container, false);


        layout_barang = v.findViewById(R.id.layout_barang);
        BtnTambahBarang = (Button)v.findViewById(R.id.BtnTambahBarang);
        BtnRemoveBarang = (Button)v.findViewById(R.id.BtnRemoveBarang);
//        keterangan_pengusulanbrg = (TextInputEditText) v.findViewById(R.id.keterangan_pengusulanbrg);



        BtnTambahBarang.setOnClickListener(this);
//        BtnTambahDoneBrg.setOnClickListener(this);

        BtnTambahDoneBrg = (Button) v.findViewById(R.id.BtnTambahDoneBrg);
        BtnTambahDoneBrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfValidAndRead()){
                    Intent intent = new Intent(getContext(),Detail_Form_Pengusulan_Barang_Activity.class);
                    PengusulanBarang pengusulanBarang = new PengusulanBarang();
//                    Integer id = pengusulanBarang.getPic_id();
//                    intent.putExtra("nama_aset",pengusulanBarang.nama_pengusulan_barang);
//                    intent.putExtra("detail_spesifikasi",pengusulanBarang.detail_spesifikasi_pengusulan_barang);
//                    intent.putExtra("jumlah",pengusulanBarang.jumlah_pengusulan_barang);
//                    intent.putExtra("harga",pengusulanBarang.harga_pengusulan_barang);
//                    intent.putExtra("sumber",pengusulanBarang.sumber_pengusulan_barang);
//                    intent.putExtra("proposal_id",pengusulanBarang.proposal_id);
//                    intent.putExtra("pic_id",id);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",listData);
//                    bundle.putSerializable("pic_id",pengusulanBarang.pic_id);
                    intent.putExtras(bundle);
                    startActivity(intent);
 }


            }
        });

        return v;


    }



    @Override
    public void onClick(View view) {
        addView();
    }

    private boolean checkIfValidAndRead() {
        listData.clear();
        boolean result = true;

        for (int i=0;i<layout_barang.getChildCount();i++) {
            View BarangView = layout_barang.getChildAt(i);

            nama_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.nama_pengusulan_barang);
            detail_spesifikasi_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.detail_spesifikasi_pengusulan_barang);
            jumlah_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.jumlah_pengusulan_barang);
            harga_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.harga_pengusulan_barang);
            sumber_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.sumber_pengusulan_barang);


            PengusulanBarang pengusulanBarang= new PengusulanBarang();

            if(!nama_pengusulan_barang.getText().toString().equals("")){
                    pengusulanBarang.setNama_pengusulan_barang(nama_pengusulan_barang.getText().toString());

            }else{
                result = false;
                break;
            }

            if(!detail_spesifikasi_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setDetail_spesifikasi_pengusulan_barang(detail_spesifikasi_pengusulan_barang.getText().toString());
            }else{
                result = false;
                break;
            }

            if(!jumlah_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setJumlah_pengusulan_barang(Integer.valueOf(jumlah_pengusulan_barang.getText().toString()));
            }else{
                result = false;
                break;
            }

            if (!harga_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setHarga_pengusulan_barang(Integer.valueOf(harga_pengusulan_barang.getText().toString()));
            }else {
                result =false;
                break;
            }

            if (!sumber_pengusulan_barang.getText().toString().equals("")){
                pengusulanBarang.setSumber_pengusulan_barang(sumber_pengusulan_barang.getText().toString());
            }else{
                result = false;
                break;
            }


            listData.add(pengusulanBarang);

        }

        if(listData.size()==0){
            result = false;
            Toast.makeText(getContext(),"Tambahkan Barang Terlebih Dahulu",Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(getContext(),"Tambahkan Barang Dengan Benar",Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    private void addView() {

        TextInputEditText nama_pengusulan_barang;
        TextInputEditText detail_spesifikasi_pengusulan_barang;
        TextInputEditText jumlah_pengusulan_barang;
        TextInputEditText harga_pengusulan_barang;
        TextInputEditText sumber_pengusulan_barang;

        final View BarangView = getLayoutInflater().inflate(R.layout.row_add_barang,null,false);

//        DISINI


//        TextView keterangan_pengusulanbrg = (TextView)BarangView.findViewById(R.id.keterangan_pengusulan_barang);
        Button BtnRemoveBarang = (Button)BarangView.findViewById(R.id.BtnRemoveBarang);
        nama_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.nama_pengusulan_barang);
        detail_spesifikasi_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.detail_spesifikasi_pengusulan_barang);
        jumlah_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.jumlah_pengusulan_barang);
        harga_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.harga_pengusulan_barang);
        sumber_pengusulan_barang = (TextInputEditText)BarangView.findViewById(R.id.sumber_pengusulan_barang);




//        String nama = nama_pengusulan_barang.getText().toString();
//        Log.d("y43", nama);
//
//        List<PengusulanBarangItem> listPengusulan= new ArrayList<PengusulanBarangItem>();
//        listPengusulan.add(new PengusulanBarangItem(
//                nama_pengusulan_barang.getText().toString(),
//                detail_spesifikasi_pengusulan_barang.getText().toString(),
//                Integer.valueOf(jumlah_pengusulan_barang.getText().toString()),
//                Integer.valueOf(harga_pengusulan_barang.getText().toString()),
//                sumber_pengusulan_barang.getText().toString()
//        ));
//
//
//        Log.d("y43", nama_pengusulan_barang.toString());



        BtnRemoveBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(BarangView);
            }
        });

        layout_barang.addView(BarangView);
    }

    private void removeView(View view){
        layout_barang.removeView(view);
    }
}