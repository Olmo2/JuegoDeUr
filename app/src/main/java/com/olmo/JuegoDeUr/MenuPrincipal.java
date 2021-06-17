package com.olmo.JuegoDeUr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.olmo.JuegoDeUr.Repository.MiBaseDeDatos;

import org.w3c.dom.Text;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener {

    Button buttonJugar,buttonPartidas,buttonSalir,buttonPreferencias;
    Intent juego, partidas,preferencias;
    String colorFichaKey,colorTableroKey,sonidoKey;
    ImageView gitHub;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        colorFichaKey="colorFichaKey";
        colorTableroKey="colorTableroKey";
        sonidoKey = "sonidoKey";

        buttonJugar = findViewById(R.id.buttonJugar); buttonJugar.setOnClickListener(this);
        buttonPartidas = findViewById(R.id.buttonPartidas); buttonPartidas.setOnClickListener(this);
        buttonSalir = findViewById(R.id.buttonSalir); buttonSalir.setOnClickListener(this);
        buttonPreferencias = findViewById(R.id.buttonPreferencias); buttonPreferencias.setOnClickListener(this);
        gitHub = findViewById(R.id.gitHub);gitHub.setOnClickListener(this);

        juego = new Intent(MenuPrincipal.this, JuegoActivity.class);
        partidas = new Intent(MenuPrincipal.this, PartidasActivity.class);
        preferencias = new Intent(MenuPrincipal.this, PreferenciasActivity.class);

        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.buttonJugar):
                startActivity(juego);
                break;
            case (R.id.buttonPartidas):
                startActivity(partidas);
                break;
            case (R.id.buttonSalir):
                this.finishAffinity();
                break;
            case (R.id.buttonPreferencias):
                startActivity(preferencias);
                break;
            case (R.id.gitHub):
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Olmo2/JuegoDeUr"));
                startActivity(browserIntent);
                break;
        }
    }
}