package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.FormPeminjamanBangunanAdapter;
import com.example.asetdsi.adapter.FormPeminjamanBarangAdapter;
import com.example.asetdsi.model.FormPeminjamanBangunan;
import com.example.asetdsi.model.FormPeminjamanBangunanResponse;
import com.example.asetdsi.model.FormPeminjamanBarang;
import com.example.asetdsi.model.RegisterClass;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormPeminjamanBangunanActivity extends AppCompatActivity implements FormPeminjamanBangunanAdapter.OnFormPeminjamanBangunanHolderClickListener {

    RecyclerView rvFormPeminjamanBangunan;
    ActionBar actionBar;
    TextInputEditText et_date;
    TextInputEditText jam_peminjaman_bangunan;
    TextInputEditText keterangan_peminjaman_bangunan;
    ImageView gambar_form_peminjaman_bangunan_act;
    TextView nama_form_peminjaman_bgn_act;
    Button tambahPeminjamanBangunan;
    final Calendar myCalendar= Calendar.getInstance();

//    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        et_date.setText(dateFormat.format(myCalendar.getTime()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_peminjaman_bangunan);


//        rvFormPeminjamanBangunan = findViewById(R.id.formPeminjamanBangunanRv);
//        rvFormPeminjamanBangunan.setAdapter(adapter);

        tambahPeminjamanBangunan = (Button) findViewById(R.id.tambahPeminjamanBangunan);
        Intent PeminjamanBangunanIntent = getIntent();
            Integer id  = PeminjamanBangunanIntent.getIntExtra("id",0);


            String nama_bangunan = PeminjamanBangunanIntent.getStringExtra("nama_bangunan");
            nama_form_peminjaman_bgn_act = findViewById(R.id.nama_form_peminjaman_bgn_act);
            nama_form_peminjaman_bgn_act.setText(nama_bangunan);

        String gambar_bangunan = PeminjamanBangunanIntent.getStringExtra("gambar");
        gambar_form_peminjaman_bangunan_act = (ImageView) findViewById(R.id.gambar_form_peminjaman_bangunan_act);
        Picasso.get().load(gambar_bangunan).into(gambar_form_peminjaman_bangunan_act);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));




        //    For Input Date
        et_date = findViewById(R.id.et_date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }

//            private void updateLabel() {
//            }
        };

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FormPeminjamanBangunanActivity.this,R.style.DatePicker,date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

    @Override
    public void onClick(View view, FormPeminjamanBangunan formPeminjamanBangunan) {
        Toast.makeText(this,"yay uncul",Toast.LENGTH_SHORT).show();

    }

//    public void data(){
//        SharedPreferences preferences = getSharedPreferences(
//                "com.example.raund.PREFS", MODE_PRIVATE);
//        token = preferences.getString( "ACCESS_TOKEN", "");
//        nim = preferences.getString("NIM","");
//        currentLoc = preferences.getString("CURRENT_LOC", "Tidak diketahui");
//        currentLat = preferences.getString("CURRENT_LAT","Tidak diketahui");
//        currentLong = preferences.getString("CURRENT_LONG", "Tidak diketahui");
//        destLoc = preferences.getString("DEST_LOC", "Tidak diketahui");
//        destLat = preferences.getString("DEST_LAT", "Tidak diketahui");
//        destLong = preferences.getString("DEST_LONG", "Tidak diketahui");
//        tglGo = preferences.getString("TGL_BRNGKT","Tidak diketahui");
//    }


    public void TambahPeminjamanBangunan(View view) {
//        data();


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        et_date = (TextInputEditText) findViewById(R.id.et_date);
        jam_peminjaman_bangunan = (TextInputEditText) findViewById(R.id.jam_peminjaman_bangunan);
        keterangan_peminjaman_bangunan = (TextInputEditText) findViewById(R.id.keterangan_peminjaman_bangunan);

        String tanggal = et_date.getText().toString();
        String jam = jam_peminjaman_bangunan.getText().toString();
        String keterangan = keterangan_peminjaman_bangunan.getText().toString();

        Intent PeminjamanBangunanIntent = getIntent();
        Integer building_id  = PeminjamanBangunanIntent.getIntExtra("building_id",0);
        Integer id  = PeminjamanBangunanIntent.getIntExtra("pic_id",0);

        String nama_bangunan = PeminjamanBangunanIntent.getStringExtra("nama_bangunan");
        nama_form_peminjaman_bgn_act = findViewById(R.id.nama_form_peminjaman_bgn_act);
        nama_form_peminjaman_bgn_act.setText(nama_bangunan);

        String gambar_bangunan = PeminjamanBangunanIntent.getStringExtra("gambar");
        gambar_form_peminjaman_bangunan_act = (ImageView) findViewById(R.id.gambar_form_peminjaman_bangunan_act);
        Picasso.get().load(gambar_bangunan).into(gambar_form_peminjaman_bangunan_act);

        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<FormPeminjamanBangunanResponse> call = client.getFormPeminjamanBangunan("Bearer "+accessToken,id,building_id,nama_bangunan,gambar_bangunan,tanggal,jam,keterangan);
        call.enqueue(new Callback<FormPeminjamanBangunanResponse>() {
            @Override
            public void onResponse(Call<FormPeminjamanBangunanResponse> call, Response<FormPeminjamanBangunanResponse> response) {
                if(response.body() != null && response.isSuccessful()) {
                    Toast.makeText(FormPeminjamanBangunanActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FormPeminjamanBangunanActivity.this, OngoingActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal Sini", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<FormPeminjamanBangunanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });


    }
}