package br.com.berson.arlocation.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.berson.arlocation.Models.Locais;
import br.com.berson.arlocation.Models.Local;
import br.com.berson.arlocation.Models.UserLogged;
import br.com.berson.arlocation.Models.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MethodsApi {
    @POST("users/auth")
    Call<UserLogged> doLogin(@Body UserLogin user);
    @GET("locals/")
    Call<Locais> getLocais();
}
