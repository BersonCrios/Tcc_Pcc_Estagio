package br.com.berson.arlocation.Controllers;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import br.com.berson.arlocation.Utils.Constants;
import br.com.berson.arlocation.Utils.CustomSharedPreference;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    private static Retrofit api = null;

    public static Retrofit getAdapter() {
        if (api == null) {
            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.MINUTES);
            httpClient.readTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.MINUTES);
            httpClient.callTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.MINUTES);
            httpClient.writeTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.MINUTES);
            httpClient.addInterceptor(chain -> {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", CustomSharedPreference.getToken())
                        .build();
                return chain.proceed(request);
            });
            api = new Retrofit.Builder()
                    .baseUrl(Constants.URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return api;
    }

    public static MethodsApi acessar() {
        return getAdapter().create(MethodsApi.class);
    }

}
