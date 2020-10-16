package br.com.berson.artourism.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.berson.artourism.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vaiParaMenu();
            }
        }, 2000);

    }

    private void vaiParaMenu() {
        Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
