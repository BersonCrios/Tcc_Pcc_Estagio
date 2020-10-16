package br.com.berson.artourism.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.berson.artourism.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    Button abrirCamera, abrirMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        abrirCamera = findViewById(R.id.btn_abrir_camera);
        abrirMaps = findViewById(R.id.btn_abrir_camera);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_abrir_camera){
            vaiParaCamera(v);
        }

        if(v.getId() == R.id.btn_abrir_camera){
            vaiParaMaps(v);
        }
    }

    public void vaiParaCamera(View v){
        Intent intent = new Intent(MenuActivity.this, CameraActivity.class);
        startActivity(intent);
    }

    public void vaiParaMaps(View v){
        Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}
