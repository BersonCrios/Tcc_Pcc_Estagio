package br.com.berson.arlocation.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.berson.arlocation.Controllers.RetrofitInit;
import br.com.berson.arlocation.Models.User;
import br.com.berson.arlocation.Models.UserLogged;
import br.com.berson.arlocation.Application.App;

import br.com.berson.arlocation.Models.UserLogin;
import br.com.berson.arlocation.R;
import br.com.berson.arlocation.Utils.CustomSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {

    public EditText campoUsername, campoSenha;
    public Button btnLogin;
    public TextView erroMsgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        CustomSharedPreference.init(this);

        myWidgets();
    }

    public void myWidgets(){
        campoUsername = findViewById(R.id.campoUser);
        campoSenha = findViewById(R.id.campoSenha);
        btnLogin = findViewById(R.id.btnLogin);
        erroMsgn = findViewById(R.id.erroMsgn);

        btnLogin.setOnClickListener(
                v -> {
                    UserLogin user = new UserLogin(campoUsername.getText().toString().trim(), campoSenha.getText().toString().trim());
                    doLogin(user);
                }
        );
    }


    public void doLogin(final UserLogin user){
        Call<UserLogged> call = RetrofitInit.acessar().doLogin(user);
        call.enqueue(new Callback<UserLogged>() {
            @Override
            public void onResponse(Call<UserLogged> call, Response<UserLogged> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(ActivityLogin.this, MenuActivity.class);
                    CustomSharedPreference.setToken(response.body().getToken());
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(ActivityLogin.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogged> call, Throwable t) {
                Log.e("Failute", t.getMessage());
            }
        });
    }
}
