package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.example.asetdsi.model.ChangePasswordClass;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePasswordActivity extends AppCompatActivity {
Toolbar toolbar2;
Button BtnChangePassword;
ActionBar actionBar;
TextInputEditText old_password, new_password, confirm_new_password;
public String passwordlamaC, passwordbaru1C, passwordbaru2C;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");
        String passwordlama = preferences.getString("PASSWORD","");

        String API_BASE_URL = "http://192.168.0.104:8000/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        old_password = (TextInputEditText) findViewById(R.id.old_password);
        new_password= (TextInputEditText) findViewById(R.id.new_password);
        confirm_new_password = (TextInputEditText) findViewById(R.id.confirm_new_password);


        //Button
        BtnChangePassword = findViewById(R.id.BtnChangePassword);
        BtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordlamaC = old_password.getText().toString();
                passwordbaru1C = new_password.getText().toString();
                passwordbaru2C = confirm_new_password.getText().toString();
                updatePassword();
                SettingActivity();

            }
        });

    }
    public void updatePassword()
    {
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

        Call<ChangePasswordClass> call = client.getChangePassword("Bearer "+accessToken, passwordlamaC, passwordbaru1C, passwordbaru2C);
        call.enqueue(new Callback<ChangePasswordClass>(){

            @Override
            public void onResponse(Call<ChangePasswordClass> call, Response<ChangePasswordClass> response) {
//                MyPasswordResponse changepassword = response.body();

                if(response.body() != null && response.isSuccessful())
                {
                    Intent intent = new Intent(ChangePasswordActivity.this, SettingActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Mantap Sudah berubah yaa", Toast.LENGTH_SHORT).show();

                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ChangePasswordClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void SettingActivity(){
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