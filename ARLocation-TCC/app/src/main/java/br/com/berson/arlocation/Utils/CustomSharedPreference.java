package br.com.berson.arlocation.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;


public class CustomSharedPreference {
    private static SharedPreferences mSharedPref = null;
    private  static Gson gson = new Gson();

    public static void init(Context context){
        if (mSharedPref == null)
            mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void clearData(){
        mSharedPref.edit().remove("token").apply();
    }
    public static void clearSharedPreferences(){
        mSharedPref.edit().clear().commit();
    }


    public static void setToken(String value){
        mSharedPref.edit().putString("token",value).apply();
    }
    public static String getToken(){
        return mSharedPref.getString("token","");
    }
}
