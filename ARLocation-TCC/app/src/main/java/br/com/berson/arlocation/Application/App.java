package br.com.berson.arlocation.Application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import java.util.HashMap;

import br.com.berson.arlocation.Utils.CustomSharedPreference;

public class App extends Application {
    public static App instance = null;
    private static Context mContext;

    private static final String TAG = App.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        CustomSharedPreference.init(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


    }

    public static Context getContext() {
        return mContext;
    }

    public static App getInstance() {
        return instance;
    }
}