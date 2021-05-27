package com.example.donelistapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donelistapp.Models.LoginResponse;
import com.example.donelistapp.R;
import com.example.donelistapp.Services.RestClient;
import com.example.donelistapp.Services.TokenPrefManager;
import com.example.donelistapp.Services.UserPrefManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Context mContext;
    UserPrefManager userPrefManager;
    TokenPrefManager tokenPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userPrefManager = new UserPrefManager(this);
        tokenPrefManager = new TokenPrefManager(this);

        final EditText etNama = findViewById(R.id.et_nama);
        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.et_password);

        Button btnDaftar = findViewById(R.id.btn_daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                Intent homePage = new Intent(RegisterActivity.this,HomeActivity.class);
                RestClient.getService().createRequest(nama,email,password).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            RestClient.getService().loginRequest(email,password).enqueue(new Callback<LoginResponse>() {
                                @Override
                                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, response.body().getAccessToken(), Toast.LENGTH_SHORT).show();
                                        Log.i("Response", response.message());
                                        String nama = response.body().getNama();
                                        String token = response.body().getAccessToken();
                                        userPrefManager.saveName(nama);
                                        userPrefManager.saveId(response.body().getId());
                                        tokenPrefManager.saveToken(token);
                                        startActivity(homePage);
                                        finish();
                                    }else{
                                        Toast.makeText(mContext, "Username/Password salah!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<LoginResponse> call, Throwable t) {
                                    Toast.makeText(mContext, "Username/Password salah!"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "register Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}