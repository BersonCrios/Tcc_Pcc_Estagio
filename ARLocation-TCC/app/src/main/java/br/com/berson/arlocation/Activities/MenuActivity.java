package br.com.berson.arlocation.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import br.com.berson.arlocation.R;
import br.com.berson.arlocation.Utils.CustomSharedPreference;

public class MenuActivity extends AppCompatActivity{
    Button camera, maps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        camera = findViewById(R.id.camera);
        maps = findViewById(R.id.maps);

        Log.e("Token User Logged", CustomSharedPreference.getToken());

        camera.setOnClickListener(v->{
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        });

        maps.setOnClickListener(v->{
            Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
            startActivity(intent);
        });
    }
}
