package com.example.donelistapp.Services;

import android.content.Context;

public class UserPrefManager {
    private static final String SP_TDL_APP = "spTDLAPP";
    private static final String SP_NAME = null;
    private static final int SP_ID = 0;

    private android.content.SharedPreferences sp;
    private android.content.SharedPreferences.Editor spEditor;

    public UserPrefManager(Context context){
        sp = context.getSharedPreferences(SP_TDL_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }


    public void saveName(String name){
        spEditor.putString(SP_NAME, name);
        spEditor.commit();
    }
    public void saveId(Integer id){
        spEditor.putInt(String.valueOf(SP_ID),id);
        spEditor.commit();
    }

    public String getNama(){
        return sp.getString(SP_NAME, "");
    }
    public int getId(){
        return sp.getInt(String.valueOf(SP_ID), 0);
    }

}
