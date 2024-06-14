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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.FormPeminjamanBangunanAdapter;
import com.example.asetdsi.adapter.FormPeminjamanBarangAdapter;
import com.example.asetdsi.model.FormPeminjamanBangunan;
import com.example.asetdsi.model.FormPeminjamanBangunanResponse;
import com.example.asetdsi.model.FormPeminjamanBarang;
import com.example.asetdsi.model.RegisterClass;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

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

public class FormPeminjamanBangunanActivity extends AppCompatActivity implements FormPeminjamanBangunanAdapter.OnFormPeminjamanBangunanHolderClickListener {

    RecyclerView rvFormPeminjamanBangunan;
    ActionBar actionBar;
    TextInputLayout ti_dateBg, ti_atBg, ti_etBg, ti_keteranganBg;
    TextInputEditText et_date;
    TextInputEditText et_time,et_time_end;
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

        //For Input Time
        et_time= findViewById(R.id.et_time);
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(et_time);
            }
        });

        //For Input Time ENd
        et_time_end= findViewById(R.id.et_time_end);
        et_time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialogEnd(et_time_end);
            }
        });

    }

    private void showTimeDialog(final TextInputEditText et_time) {
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

        new TimePickerDialog(FormPeminjamanBangunanActivity.this,R.style.TimePicker,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    private void showTimeDialogEnd(final TextInputEditText et_time_end) {
        final Calendar calendar=Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                et_time_end.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(FormPeminjamanBangunanActivity.this,R.style.TimePicker,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
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
//        Toast.makeText(this,"yay uncul",Toast.LENGTH_SHORT).show();

    }

    private boolean validate() {
        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        ti_dateBg = findViewById(R.id.textInputDateBg);
        ti_atBg = findViewById(R.id.textInputJamBg);
        ti_etBg = findViewById(R.id.textInputJamAkhirBg);
        ti_keteranganBg = findViewById(R.id.textInputKeteranganBg);

        keterangan_peminjaman_bangunan = (TextInputEditText) findViewById(R.id.keterangan_peminjaman_bangunan);
        String keterangan = keterangan_peminjaman_bangunan.getText().toString();
        et_date = (TextInputEditText) findViewById(R.id.et_date);
        String date = et_date.getText().toString();
        et_time= (TextInputEditText) findViewById(R.id.et_time);
        et_time_end= (TextInputEditText) findViewById(R.id.et_time_end);

        String a_time = et_time.getText().toString();
        String e_time = et_time_end.getText().toString();

        if(date.isEmpty()){
            ti_dateBg.setErrorEnabled(true);
            ti_dateBg.setError("Tidak Boleh Kosong");
        }else{
            ti_dateBg.setErrorEnabled(false);
            ti_dateBg.setError(null);
        }

        if(a_time.isEmpty()){
            ti_atBg.setErrorEnabled(true);
            ti_atBg.setError("Tidak Boleh Kosong");
        }else{
            ti_atBg.setErrorEnabled(false);
            ti_atBg.setError(null);
        }

        if(e_time.isEmpty()){
            ti_etBg.setErrorEnabled(true);
            ti_etBg.setError("Tidak Boleh Kosong");
        }else{
            if(!checktime()){
                ti_etBg.setErrorEnabled(true);
                ti_etBg.setError("Jam tidak valid");
            }else{
                ti_etBg.setErrorEnabled(false);
                ti_etBg.setError(null);
            }
        }

        if(keterangan.isEmpty()){
            ti_keteranganBg.setErrorEnabled(true);
            ti_keteranganBg.setError("Tidak Boleh Kosong");
        }else{
            ti_keteranganBg.setErrorEnabled(false);
            ti_keteranganBg.setError(null);
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

        et_time= (TextInputEditText) findViewById(R.id.et_time);
        et_time_end= (TextInputEditText) findViewById(R.id.et_time_end);

        String a_time = et_time.getText().toString();
        String e_time = et_time_end.getText().toString();

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

    public void TambahPeminjamanBangunan(View view) {
//        data();

        if(validate()){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        et_date = (TextInputEditText) findViewById(R.id.et_date);
        et_time = (TextInputEditText) findViewById(R.id.et_time);
        keterangan_peminjaman_bangunan = (TextInputEditText) findViewById(R.id.keterangan_peminjaman_bangunan);

        String tanggal = et_date.getText().toString();
        String jam = et_time.getText().toString();
        String jam_end = et_time_end.getText().toString();
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

        //SweetAlert
        SweetAlertDialog sweetDialog = new SweetAlertDialog(FormPeminjamanBangunanActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        Call<FormPeminjamanBangunanResponse> call = client.getFormPeminjamanBangunan("Bearer "+accessToken,id,building_id,nama_bangunan,gambar_bangunan,tanggal,jam,jam_end,keterangan);
        call.enqueue(new Callback<FormPeminjamanBangunanResponse>() {
            @Override
            public void onResponse(Call<FormPeminjamanBangunanResponse> call, Response<FormPeminjamanBangunanResponse> response) {
                sweetDialog.dismiss();
                if(response.body() != null && response.isSuccessful()) {
                    new SweetAlertDialog(FormPeminjamanBangunanActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Berhasil")
                            .setContentText("Peminjaman Bangunan Berhasil!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(FormPeminjamanBangunanActivity.this, OngoingActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();

//                    Toast.makeText(FormPeminjamanBangunanActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(FormPeminjamanBangunanActivity.this, OngoingActivity.class);
//                    startActivity(intent);
//                    finish();
                }else{
//                    Toast.makeText(getApplicationContext(), "Gagal Sini", Toast.LENGTH_SHORT).show();
                    new SweetAlertDialog(FormPeminjamanBangunanActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Gagal")
                            .setContentText("Peminjaman Bangunan Gagal")
                            .show();
                }



            }

            @Override
            public void onFailure(Call<FormPeminjamanBangunanResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                sweetDialog.dismiss();
                new SweetAlertDialog(FormPeminjamanBangunanActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Koneksi Gagal")
                        .show();
//                Toast.makeText(AddLeaveActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        }else{

        }
    }
}