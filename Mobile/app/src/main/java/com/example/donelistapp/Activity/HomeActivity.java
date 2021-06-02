package com.example.donelistapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donelistapp.Adapter.AktivitasAdapter;
import com.example.donelistapp.Models.AktivitasDataItem;
import com.example.donelistapp.R;
import com.example.donelistapp.Models.AktivitasResponse;
import com.example.donelistapp.Services.RestClient;
import com.example.donelistapp.Services.TokenPrefManager;
import com.example.donelistapp.Services.UserPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    int id;
    String nama, accessToken ;
    UserPrefManager userPrefManager;
    TokenPrefManager tokenPrefManager;
    private RecyclerView rvAktivitas;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lmAtktivitas;
    private List<AktivitasDataItem> listItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userPrefManager = new UserPrefManager(this);
        tokenPrefManager = new TokenPrefManager(this);

        if( tokenPrefManager.getToken().equals("")) {
            startLandingPageActivity();
            finish();
        }
        accessToken = tokenPrefManager.getToken();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);


            id = userPrefManager.getId();
            nama = userPrefManager.getNama();

            rvAktivitas = findViewById(R.id.rv_aktivitas);
            lmAtktivitas = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rvAktivitas.setLayoutManager(lmAtktivitas);

            requestListAktivitas();
            textView = (TextView) findViewById(R.id.tv_name);
            textView.setText("Hi " + nama + " !");

            FloatingActionButton btnAddAktivitas = findViewById(R.id.btn_tambah);
            Button btnLogout = findViewById(R.id.btn_logout);

            Intent formPage = new Intent(HomeActivity.this, FormActivity.class);

            btnAddAktivitas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(formPage);
                    finish();
                }
            });


            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accessToken = ""; id = 0; nama = "";
                    userPrefManager.saveName(nama);
                    userPrefManager.saveId(id);
                    tokenPrefManager.saveToken(accessToken);

//                    Toast.makeText(HomeActivity.this, nama + id + accessToken, Toast.LENGTH_SHORT).show();

                    startLandingPageActivity();
                }
            });

    }

    private void requestListAktivitas() {
        Call<AktivitasResponse> tampilData = RestClient.getService().getAktivitas(accessToken,id);

        tampilData.enqueue(new Callback<AktivitasResponse>() {
            @Override
            public void onResponse(Call<AktivitasResponse> call, Response<AktivitasResponse> response) {
                if(response.isSuccessful()) {
                    String message = response.body().getMessage();

                    Toast.makeText(HomeActivity.this, "Message: " + message, Toast.LENGTH_SHORT).show();

                    listItem = response.body().getData();

                    adapter = new AktivitasAdapter(HomeActivity.this, listItem);
                    rvAktivitas.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(HomeActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AktivitasResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Gagal Menghubungi Server :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startLandingPageActivity() {
        Intent intent = new Intent(HomeActivity.this, LandingPageActivity.class);
        startActivity(intent);
    }
}