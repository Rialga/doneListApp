package com.example.donelistapp.Services;

import android.content.Context;

public class TokenPrefManager {

    private static final String SP_KEY = "spKey";
    private static final String SP_TOKEN = null;


    private android.content.SharedPreferences sp;
    private android.content.SharedPreferences.Editor spEditor;

    public TokenPrefManager(Context context){
        sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveToken(String token){
        spEditor.putString(SP_TOKEN, token);
        spEditor.commit();
    }


    public String getToken(){
        return sp.getString(SP_TOKEN, "");
    }


}
