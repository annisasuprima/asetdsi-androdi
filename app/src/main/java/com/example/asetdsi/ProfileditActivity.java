package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.model.EditProfileClass;
import com.example.asetdsi.model.EditProfileData;
import com.example.asetdsi.model.SettingData;
import com.google.android.material.textfield.TextInputEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileditActivity extends AppCompatActivity {
    private Button BtnSaveProfile;
    ActionBar actionBar;
    TextInputEditText edit_nama;
//    TextInputEditText edit_nim;
    TextInputEditText edit_email;
    TextInputEditText edit_username;
    String ynama,ynim,yemail,ytelepon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledit);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Intent intent = getIntent();
        String name =  intent.getStringExtra("name");
//        String nim =  intent.getStringExtra("nim");
        String email =  intent.getStringExtra("email");
        String username =  intent.getStringExtra("username");

        //Buat Object Client Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<EditProfileClass> call = client.getEditProfile("Bearer "+accessToken, name, email, username);
        call.enqueue(new Callback<EditProfileClass>() {
            @Override
            public void onResponse(Call<EditProfileClass> call, Response<EditProfileClass> response) {
                EditProfileClass editProfileClass = response.body();
                EditProfileData editProfileData = editProfileClass.getData();
                String name = editProfileData.getName();
                String nim = editProfileData.getNim();
                String email = editProfileData.getEmail();
                String username = editProfileData.getUsername();

                if(editProfileClass != null && response.isSuccessful()){

                    edit_nama = (TextInputEditText) findViewById(R.id.edit_nama);
//                    edit_nim = (TextInputEditText) findViewById(R.id.edit_nim);
                    edit_email = (TextInputEditText) findViewById(R.id.edit_email);
                    edit_username = (TextInputEditText) findViewById(R.id.edit_username);


//                  set the string from sp as text of the textview
                    edit_nama.setText(name);
//                    edit_nim.setText(nim);
                    edit_email.setText(email);
                    edit_username.setText(username);

                }else{
                    Toast.makeText(getApplicationContext(), "Gagal Sini", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<EditProfileClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });



//        //Button
        BtnSaveProfile = findViewById(R.id.BtnSaveProfile);
        BtnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                ynim = edit_nim.getText().toString();
                ynama = edit_nama.getText().toString();
                yemail = edit_email.getText().toString();
                ytelepon = edit_username.getText().toString();

                updateData();
//                SettingActivity();

            }
        });
    }

    public void updateData(){


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");

        //Buat Object Client Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        //SweetAlert
        SweetAlertDialog sweetDialog = new SweetAlertDialog(ProfileditActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//        Call<EditProfileClass> call = client.getEditProfile("Bearer "+accessToken,ynama, ynim, yemail, ytelepon);
        Call<EditProfileClass> call = client.getEditProfile("Bearer "+accessToken,ynama, yemail, ytelepon);
        call.enqueue(new Callback<EditProfileClass>() {
            @Override
            public void onResponse(Call<EditProfileClass> call, Response<EditProfileClass> response) {
                if(response.body() != null && response.isSuccessful()) {
                    new SweetAlertDialog(ProfileditActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Berhasil")
                            .setContentText("Edit Profile Berhasil!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(ProfileditActivity.this, SettingActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();
                }else{
                    new SweetAlertDialog(ProfileditActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Gagal")
                            .setContentText("Edit Profile Gagal")
                            .show();
                }

            }

            @Override
            public void onFailure(Call<EditProfileClass> call, Throwable t) {
                sweetDialog.dismiss();
                new SweetAlertDialog(ProfileditActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Koneksi Gagal")
                        .show();
            }
        });

    }

    public void  SettingActivity(){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
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