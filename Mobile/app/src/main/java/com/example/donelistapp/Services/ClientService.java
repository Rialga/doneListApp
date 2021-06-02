package com.example.donelistapp.Services;

import com.example.donelistapp.Models.AktivitasResponse;
import com.example.donelistapp.Models.LoginDataItem;
import com.example.donelistapp.Models.LoginResponse;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClientService {
    @FormUrlEncoded
    @POST("auth/signup")
    Call<ResponseBody> createRequest(@Field("nama") String nama,
                                     @Field("email") String email,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/signin")
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password);

    @GET("activity?")
    Call<AktivitasResponse> getAktivitas(@Header("x-access-token") String accessToken,
                                        @Query("id") int id);

    @FormUrlEncoded
    @POST("activity")
    Call<AktivitasResponse> postAktivitas(@Header("x-access-token") String authToken,
                                     @Field("user_id") int user_id,
                                     @Field("activity_name") String activity_name);

    Converter<ResponseBody, Apierror> responseBodyConverter(Class<Apierror> apierrorClass, Annotation[] annotations);
}
