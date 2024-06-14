package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.DetailFormPengusulanBarangAdapter;
import com.example.asetdsi.adapter.FormPeminjamanBarangAdapter;
import com.example.asetdsi.model.FormPeminjamanBarang;
import com.example.asetdsi.model.Peminjaman;
import com.example.asetdsi.model.PeminjamanBarangResponse;
import com.example.asetdsi.model.PengusulanBarang;
import com.example.asetdsi.model.PengusulanBarangResponse;
import com.example.asetdsi.model.Pj;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormPeminjamanBarangActivity extends AppCompatActivity implements FormPeminjamanBarangAdapter.OnFormPeminjamanBarangHolderClickListener{
    RecyclerView rvFormPeminjamanBarang;
    ActionBar actionBar;
    ArrayList<Peminjaman> listData = new ArrayList<Peminjaman>();
    TextInputLayout ti_date,ti_at,ti_et,ti_keterangan;
    TextInputEditText at_date;
    TextInputEditText at_time;
    TextInputEditText et_time;
    TextInputEditText keterangan_peminjaman_brg;
    Button BtnTambahPeminjamanBarang;
    final Calendar myCalendar= Calendar.getInstance();

    private void updateLabel(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        at_date.setText(dateFormat.format(myCalendar.getTime()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_peminjaman_barang);

        listData = (ArrayList<Peminjaman>) getIntent().getExtras().getSerializable("listPeminjaman");

        rvFormPeminjamanBarang = findViewById(R.id.formPeminjamanBarangRv);
        rvFormPeminjamanBarang.setAdapter(new FormPeminjamanBarangAdapter(listData));

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvFormPeminjamanBarang.setLayoutManager(layoutManager);


        //    For Input Date
        at_date = findViewById(R.id.at_date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            //            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }

        };

        at_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FormPeminjamanBarangActivity.this,R.style.DatePicker,date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //For Input Time
        at_time = findViewById(R.id.at_time);
        at_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(at_time);
            }
        });

        //For Input Time
        et_time = findViewById(R.id.et_time);
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialogEnd(et_time);
            }
        });


    }


    private void showTimeDialog(final TextInputEditText at_time) {
        final Calendar calendar=Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                at_time.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        new TimePickerDialog(FormPeminjamanBarangActivity.this,R.style.TimePicker,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    private void showTimeDialogEnd(final TextInputEditText et_time) {
        final Calendar calendar=Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                et_time.setText(simpleDateFormat.format(calendar.getTime()));

            }

        };
    
        new TimePickerDialog(FormPeminjamanBarangActivity.this,R.style.TimePicker,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
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
    public void onClick(View view, Peminjaman peminjaman) {
//        Toast.makeText(this,"yay uncul",Toast.LENGTH_SHORT).show();

    }

    private boolean validate() {
        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        ti_date = findViewById(R.id.textInputDate);
        ti_at = findViewById(R.id.textInputAt);
        ti_et = findViewById(R.id.textInputEt);
        ti_keterangan = findViewById(R.id.textInputKeterangan);

        keterangan_peminjaman_brg = (TextInputEditText) findViewById(R.id.keterangan_peminjaman_brg);
        String keterangan = keterangan_peminjaman_brg.getText().toString();
        at_date = (TextInputEditText) findViewById(R.id.at_date);
        String date = at_date.getText().toString();
        at_time= (TextInputEditText) findViewById(R.id.at_time);
        et_time= (TextInputEditText) findViewById(R.id.et_time);

        String a_time = at_time.getText().toString();
        String e_time = et_time.getText().toString();

        if(date.isEmpty()){
            ti_date.setErrorEnabled(true);
            ti_date.setError("Tidak Boleh Kosong");
        }else{
            ti_date.setErrorEnabled(false);
            ti_date.setError(null);
        }

        if(a_time.isEmpty()){
            ti_at.setErrorEnabled(true);
            ti_at.setError("Tidak Boleh Kosong");
        }else{
            ti_at.setErrorEnabled(false);
            ti_at.setError(null);
        }

        if(e_time.isEmpty()){
            ti_et.setErrorEnabled(true);
            ti_et.setError("Tidak Boleh Kosong");
        }else{
            if(!checktime()){
                ti_et.setErrorEnabled(true);
                ti_et.setError("Jam tidak valid");
            }else{
                ti_et.setErrorEnabled(false);
                ti_et.setError(null);
            }
        }

        if(keterangan.isEmpty()){
            ti_keterangan.setErrorEnabled(true);
            ti_keterangan.setError("Tidak Boleh Kosong");
        }else{
            ti_keterangan.setErrorEnabled(false);
            ti_keterangan.setError(null);
        }

        if(date.isEmpty() || a_time.isEmpty() || e_time.isEmpty() || keterangan.isEmpty() || !checktime()){
            return false;
        }else{
            return true;
        }
    }

    private boolean checktime() {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        at_time= (TextInputEditText) findViewById(R.id.at_time);
        et_time= (TextInputEditText) findViewById(R.id.et_time);

        String a_time = at_time.getText().toString();
        String e_time = et_time.getText().toString();

        try {
            Date date1 = sdf.parse(a_time);
            Date date2 = sdf.parse(e_time);

            if(date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }

    public void TambahPeminjamanBarang(View view) {
        if(validate()){
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                    "com.example.asetdsi.PREFS",
                    Context.MODE_PRIVATE
            );

            keterangan_peminjaman_brg = (TextInputEditText) findViewById(R.id.keterangan_peminjaman_brg);
            String keterangan = keterangan_peminjaman_brg.getText().toString();

            at_date = (TextInputEditText) findViewById(R.id.at_date);
            String tanggal = at_date.getText().toString();

            at_time= (TextInputEditText) findViewById(R.id.at_time);
            String jam = at_time.getText().toString();

            et_time= (TextInputEditText) findViewById(R.id.et_time);
            String jam_end = et_time.getText().toString();

            Integer id = Integer.valueOf(preferences.getString("pic_id",""));
            String accessToken = preferences.getString("ACCESS_TOKEN","");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PortalClient client = retrofit.create(PortalClient.class);

            //SweetAlert
            SweetAlertDialog sweetDialog = new SweetAlertDialog(FormPeminjamanBarangActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            Call<PeminjamanBarangResponse> call = client.getPeminjamanBarang("Bearer "+accessToken,id,keterangan,tanggal,jam,jam_end,listData);
            call.enqueue(new Callback<PeminjamanBarangResponse>() {
                @Override
                public void onResponse(Call<PeminjamanBarangResponse> call, Response<PeminjamanBarangResponse> response) {
                    sweetDialog.dismiss();
                    if(response.body() != null && response.isSuccessful()) {
                        new SweetAlertDialog(FormPeminjamanBarangActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Berhasil!")
                                .setContentText("Peminjaman Barang Berhasil!")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intent = new Intent(FormPeminjamanBarangActivity.this, OngoingActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .show();
                    }else{
                        new SweetAlertDialog(FormPeminjamanBarangActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Gagal")
                                .setContentText("Peminjaman Barang Gagal!")
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<PeminjamanBarangResponse> call, Throwable t) {
                    sweetDialog.dismiss();
                    new SweetAlertDialog(FormPeminjamanBarangActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Koneksi Gagal")
                            .show();
                }
            });
        }else{

        }
    }
}