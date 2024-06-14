package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.model.RegisterClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText inputNim;
    TextInputEditText inputNama;
    TextInputEditText inputEmail;
    TextInputEditText inputUsername;
    TextInputEditText inputPassword;
    String fcmtoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hide Action Bar
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }




    }


    public void Register(View view) {

        inputNim = (TextInputEditText) findViewById(R.id.inputNim);
        inputNama = (TextInputEditText) findViewById(R.id.inputNama);
        inputEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        inputUsername = (TextInputEditText) findViewById(R.id.inputUsername);
        inputPassword = (TextInputEditText) findViewById(R.id.inputPassword);

        String nim = inputNim.getText().toString();
        String name = inputNama.getText().toString();
        String email = inputEmail.getText().toString();
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();
//        String remember_token =




        //Buat Object Client Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        //SweetAlert
        SweetAlertDialog sweetDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        Call<RegisterClass> call = client.registerResponse(nim, name, email, username, password);
        call.enqueue(new Callback<RegisterClass>() {
            @Override
            public void onResponse(Call<RegisterClass> call, Response<RegisterClass> response) {
                if(response.body() != null && response.isSuccessful()) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Berhasil!")
                            .setContentText("Register Berhasil")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();
                }else{
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Gagal")
                            .setContentText("Register Gagal")
                            .show();
                }

            }

            @Override
            public void onFailure(Call<RegisterClass> call, Throwable t) {
                sweetDialog.dismiss();
                new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Koneksi Gagal")
                        .show();
            }
        });

    }
}