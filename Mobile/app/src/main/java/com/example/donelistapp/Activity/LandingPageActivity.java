package com.example.donelistapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.donelistapp.R;
import com.example.donelistapp.Services.TokenPrefManager;
import com.example.donelistapp.Services.UserPrefManager;

public class LandingPageActivity extends AppCompatActivity {

    UserPrefManager userPrefManager;
    TokenPrefManager tokenPrefManager;
    String accessToken;
    Button btnLogin, btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userPrefManager = new UserPrefManager(this);
        tokenPrefManager = new TokenPrefManager(this);

        accessToken = tokenPrefManager.getToken();
        if(! tokenPrefManager.getToken().equals("")) {
            startActivity(new Intent(LandingPageActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_landing_page);

            btnLogin = findViewById(R.id.btn_loginpage);
            btnRegister = findViewById(R.id.btn_daftarpage);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LandingPageActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LandingPageActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

}