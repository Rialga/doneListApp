package com.example.donelistapp.Activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donelistapp.Models.AktivitasResponse;
import com.example.donelistapp.R;
import com.example.donelistapp.Services.RestClient;
import com.example.donelistapp.Services.TokenPrefManager;
import com.example.donelistapp.Services.UserPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    TextView textView;
    int id;
    String token;
    String nama;
    UserPrefManager userPrefManager;
    TokenPrefManager tokenPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userPrefManager = new UserPrefManager(this);
        tokenPrefManager = new TokenPrefManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                startHome();
            }
        };

        token = tokenPrefManager.getToken();
        id = userPrefManager.getId();
        final EditText aktivitas = findViewById(R.id.et_aktivitas);

        nama = userPrefManager.getNama();


        Button btnCreate = findViewById(R.id.btn_create);
        textView = (TextView) findViewById(R.id.tv_heading);
        textView.setText("What you Gonna do "+ nama+" ?");

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aktivitasValue = aktivitas.getText().toString();

//                Toast.makeText(FormActivity.this, aktivitasValue + id, Toast.LENGTH_SHORT).show();

                Call<AktivitasResponse> postData = RestClient.getService().postAktivitas(token,id,aktivitasValue);

                postData.enqueue(new Callback<AktivitasResponse>() {
                    @Override
                    public void onResponse(Call<AktivitasResponse> call, Response<AktivitasResponse> response) {
                        startHome();
                    }

                    @Override
                    public void onFailure(Call<AktivitasResponse> call, Throwable t) {
                        Toast.makeText(FormActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

            }


        });



    }

    public void startHome(){
        Intent intent = new Intent(FormActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}