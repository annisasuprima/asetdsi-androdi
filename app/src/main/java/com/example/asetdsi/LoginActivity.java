package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.model.AuthClass;
import com.example.asetdsi.model.AuthData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button BtnLogin;
    private TextView idCreateAccTxt;
    String remember_token;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//Hide Action Bar
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        //TextView
//        idCreateAccTxt = findViewById(R.id.idCreateAccTxt);
//        idCreateAccTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RegisterActivity();
//            }
//        });

        //FireBase Registration Token
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(
                new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(task.isSuccessful()){
                            String token = task.getResult();
                            Log.d("fcm-token",token);
                            remember_token = token;
                        }

                    }
                }
        );

    }

    public void HomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void RegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void checkLogin(View view) {
        TextInputEditText usernamelogin;
        TextInputEditText passwordlogin;

        usernamelogin = (TextInputEditText) findViewById(R.id.usernamelogin);
        passwordlogin = (TextInputEditText) findViewById(R.id.passwordlogin);

        String username = usernamelogin.getText().toString();
        String password = passwordlogin.getText().toString();

        //Buat Object Client Retrofit


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);
//        Log.d("fcmtoken",remember_token);

        //SweetAlert
        SweetAlertDialog sweetDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        Call<AuthClass> call = client.checkLogin(username, password,remember_token);

        call.enqueue(new Callback<AuthClass>() {
            @Override
            public void onResponse(Call<AuthClass> call, Response<AuthClass> response) {
                AuthClass authClass = response.body();
                if(authClass != null && response.isSuccessful()) {
                    AuthData data = authClass.getData();
                    String token = data.getToken();
                    String name = data.getName();
                    String email = data.getEmail();
                    String nim = data.getNim();
                    String username = data.getUsername();

                    SharedPreferences preferences =
                            getSharedPreferences("com.example.asetdsi.PREFS", MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("ACCESS_TOKEN",token);
                    editor.putString("NAMA", name);
                    editor.putString("NIM", nim);
                    editor.putString("EMAIL",email);
                    editor.putString("USERNAME",username);
//
//                    editor.apply();
                    editor.commit();

//                    Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Gagal")
                            .setContentText("Login Gagal")
                            .show();

                }


            }

            @Override
            public void onFailure(Call<AuthClass> call, Throwable t) {
                sweetDialog.dismiss();
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Koneksi Gagal")
                        .show();
            }
        });
    }



}