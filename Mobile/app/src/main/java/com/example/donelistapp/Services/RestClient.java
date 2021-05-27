package com.example.donelistapp.Services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    // TODO menginisiasi MainInterface
    private static ClientService service;

    public static ClientService getService() {

        if (service == null) {
            // Membuat base URL
            String BASE_URL = "http://10.0.2.2:5000/api/";

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.client(httpClient.build()).build();

            service = retrofit.create(ClientService.class);
        }
        return service;
    }
}