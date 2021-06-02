package com.example.donelistapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donelistapp.Models.AktivitasDataItem;
import com.example.donelistapp.Models.LoginResponse;
import com.example.donelistapp.R;
import com.example.donelistapp.Models.LoginDataItem;
import com.example.donelistapp.Services.RestClient;
import com.example.donelistapp.Services.TokenPrefManager;
import com.example.donelistapp.Services.UserPrefManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Context mContext;
    UserPrefManager userPrefManager;
    TokenPrefManager tokenPrefManager;
    private List<LoginDataItem> userData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPrefManager = new UserPrefManager(this);
        tokenPrefManager = new TokenPrefManager(this);
        final EditText emailField = findViewById(R.id.et_email);
        final EditText passwordField = findViewById(R.id.et_password);
        Button loginBtn = findViewById(R.id.btn_login);
        mContext    = this;

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                Intent homePage = new Intent(LoginActivity.this,HomeActivity.class);
                RestClient.getService().loginRequest(email,password).enqueue(new Callback<LoginResponse>() {
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

//                            Toast.makeText(LoginActivity.this, response.body().getAccessToken(), Toast.LENGTH_SHORT).show();
                            Log.i("Response", response.message());

                            startActivity(homePage);
                            finish();
                        }else{
                            try {
                                Toast.makeText(mContext, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}
