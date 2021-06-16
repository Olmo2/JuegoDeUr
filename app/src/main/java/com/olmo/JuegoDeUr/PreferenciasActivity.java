package com.olmo.JuegoDeUr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class PreferenciasActivity extends AppCompatActivity implements View.OnClickListener {

    Switch sonido;
    RadioGroup groupFichas,groupTablero;
    RadioButton rojoVerde,blancoNegro,tableroAzul,tableroNegro;
    SharedPreferences preferences;
    String colorFichaKey,colorTableroKey,sonidoKey;
    Button buttonGuadar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        colorFichaKey="colorFichaKey";
        colorTableroKey="colorTableroKey";
        sonidoKey = "sonidoKey";

        buttonGuadar = findViewById(R.id.buttonGuadar);
        buttonGuadar.setOnClickListener(this);

        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        sonido = findViewById(R.id.switchSonido);

        groupFichas = findViewById(R.id.groupFichas);
        rojoVerde = findViewById(R.id.radioButtonRojoVerde);
        blancoNegro = findViewById(R.id.radioButtonBlancoNegro);

        groupTablero = findViewById(R.id.groupTablero);
        tableroAzul = findViewById(R.id.radioButtonTableroAzul);
        tableroNegro = findViewById(R.id.radioButtonTableroNegro);

        if(preferences.getBoolean(sonidoKey,true)){
            sonido.setChecked(true);
        }
        if(preferences.getBoolean(colorFichaKey,true)){
            rojoVerde.setChecked(false);
            blancoNegro.setChecked(true);
        } else {
            rojoVerde.setChecked(true);
            blancoNegro.setChecked(false);
        }

        if(preferences.getBoolean(colorTableroKey,true)){
            tableroAzul.setChecked(true);
            tableroNegro.setChecked(false);
        } else {
            tableroAzul.setChecked(false);
            tableroNegro.setChecked(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.buttonGuadar):
                SharedPreferences.Editor editor =preferences.edit();

                editor.putBoolean(sonidoKey, sonido.isChecked());
                editor.putBoolean(colorFichaKey, rojoVerde.isChecked());
                editor.putBoolean(colorTableroKey,tableroAzul.isChecked());
                editor.commit();

                Intent intent = new Intent( PreferenciasActivity.this, MenuPrincipal.class);
                startActivity(intent);
                break;
        }
    }
}