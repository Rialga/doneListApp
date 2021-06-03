package com.example.donelistapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donelistapp.Models.LoginDataItem;
import com.example.donelistapp.Models.LoginResponse;
import com.example.donelistapp.R;
import com.example.donelistapp.Services.RestClient;
import com.example.donelistapp.Services.TokenPrefManager;
import com.example.donelistapp.Services.UserPrefManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=\\S+$)" +
                    ".{8,}" +
                    "$");
    Context mContext;
    UserPrefManager userPrefManager;
    TokenPrefManager tokenPrefManager;
    private List<LoginDataItem> userData = new ArrayList<>();
    EditText emailField;
    EditText passwordField;
    EditText namaField;

    LoginActivity login = new LoginActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userPrefManager = new UserPrefManager(this);
        tokenPrefManager = new TokenPrefManager(this);


        namaField = findViewById(R.id.et_nama);
        emailField = findViewById(R.id.et_email);
        passwordField = findViewById(R.id.et_password);

        Button btnDaftar = findViewById(R.id.btn_daftar);


        btnDaftar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (!validateEmailAndName() || !validatePassword()) {
                    return;
                }
                register();
            }
        });
    }


    private void register() {
        String nama = namaField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        Intent homePage = new Intent(RegisterActivity.this, HomeActivity.class);
        RestClient.getService().createRequest(nama, email, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    RestClient.getService().loginRequest(email, password).enqueue(new Callback<LoginResponse>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                userData = response.body().getData();
                                String nama = userData.get(0).getNama();
                                String token = userData.get(0).getAccessToken();
                                userPrefManager.saveName(nama);
                                userPrefManager.saveId(userData.get(0).getId());
                                tokenPrefManager.saveToken(token);

                                Log.i("Response", response.message());

                                startActivity(homePage);
                                finish();
                            } else {
                                try {
                                    Toast.makeText(RegisterActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    try {
                        Toast.makeText(RegisterActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateEmailAndName() {
        String email = emailField.getText().toString().trim();
        String nama = namaField.getText().toString().trim();

        if (nama.isEmpty()) {
            namaField.setError("Field Nama tidak Boleh Kosong");
            return false;
        } else if (email.isEmpty()) {
            emailField.setError("Field Email tidak Boleh Kosong");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Masukkan Email yang Valid");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        String password = passwordField.getText().toString().trim();

        if (password.isEmpty()) {
            passwordField.setError("Field Password tidak Boleh Kosong");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordField.setError("Password hrus mengandung angka dan kapital ");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}