package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.model.AuthData;
import com.example.asetdsi.model.SettingClass;
import com.example.asetdsi.model.SettingData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingActivity extends AppCompatActivity {
    private TextView stChangePw;
    private TextView stLogout;
    private Button BtnEditProfile;

    public String name;
    public String nim;
    public String email;
    public String username;
    BottomNavigationView bottomNavigationView;
    TextView nama_mhs;
    TextView nim_mhs;
    TextView email_mhs;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //initialize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.setting);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ongoing:
                        startActivity(new Intent(getApplicationContext()
                                ,OngoingActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.history:
                        startActivity(new Intent(getApplicationContext()
                                , HistoryActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext()
                                ,SettingActivity.class));

                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }


        });

        nim_mhs = findViewById(R.id.nim_mhs);
        nama_mhs = findViewById(R.id.nama_mhs);
        email_mhs = findViewById(R.id.email_mhs);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);
        Call<SettingClass> call = client.getSetting("Bearer "+accessToken );
        call.enqueue(new Callback<SettingClass>() {


            @Override
            public void onResponse(Call<SettingClass> call, Response<SettingClass> response) {

                SettingClass settingClass = response.body();
//                Log.d("pesan",t.getMessage());

                if(settingClass != null && response.isSuccessful()){
                    SettingData settingData = settingClass.getData();
                    name = settingData.getName();
                    nim = settingData.getNim();
                    email = settingData.getEmail();
                    username = settingData.getUsername();

                    // set reference to the text view
                    nama_mhs = (TextView) findViewById(R.id.nama_mhs);
                    nim_mhs = (TextView) findViewById(R.id.nim_mhs);
                    email_mhs = (TextView) findViewById(R.id.email_mhs);

                    // set the string from sp as text of the textview
                    nama_mhs.setText(name);
                    nim_mhs.setText(nim);
                    email_mhs.setText(email);

                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }

//                try {
//                    if(response.body()!=null)
//                        Toast.makeText(SettingActivity.this,response.body().getMessage(), Toast.LENGTH_LONG).show();
//                    if(response.errorBody()!=null)
//                        Toast.makeText(SettingActivity.this," response message "+response.errorBody().string(),Toast.LENGTH_LONG).show();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }

            }

            @Override
            public void onFailure(Call<SettingClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });



        //Button
        BtnEditProfile = findViewById(R.id.BtnEditProfile);
        BtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this, ProfileditActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("nim",nim);
                intent.putExtra("email",email);
                intent.putExtra("username",username);
                startActivity(intent);
                ProfileditActivity();

            }
        });

        //Text
        stChangePw = findViewById(R.id.stChangePw);
        stChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePasswordActivity();
            }
        });

        stLogout = findViewById(R.id.stLogout);
        stLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
//                Toast.makeText(getApplicationContext(), "ANDA SUDAH KELUAR", Toast.LENGTH_SHORT).show();

            }
        });




    }

    public void ProfileditActivity(){
        Intent intent = new Intent(this, ProfileditActivity.class);
        startActivity(intent);
    }
    public void ChangePasswordActivity(){
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void LogOut(){

        SharedPreferences preferences =getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
//        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();

        new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setContentText("Yakin Ingin Logout?")
                .setCancelText("Batal")
                .setConfirmText("Ya")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        sweetAlertDialog.cancel();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();

//        new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.SUCCESS_TYPE)
//                .setTitleText("Berhasil!")
//                .setContentText("Peminjaman Barang Berhasil!")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .show();

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
}