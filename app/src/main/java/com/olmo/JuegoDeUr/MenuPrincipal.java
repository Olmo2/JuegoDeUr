package com.olmo.JuegoDeUr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener {

    Button buttonJugar,buttonPartidas,buttonSalir,buttonPreferencias;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        buttonJugar = findViewById(R.id.buttonJugar); buttonJugar.setOnClickListener(this);
        buttonPartidas = findViewById(R.id.buttonPartidas); buttonPartidas.setOnClickListener(this);
        buttonSalir = findViewById(R.id.buttonSalir); buttonSalir.setOnClickListener(this);
        buttonPreferencias = findViewById(R.id.buttonJugar); buttonPreferencias.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.buttonJugar):
                 intent = new Intent(MenuPrincipal.this, JuegoActivity.class);
                startActivity(intent);
                break;
            case (R.id.buttonPartidas):
                 intent = new Intent(MenuPrincipal.this, PartidasActivity.class);
                startActivity(intent);
                break;
            case (R.id.buttonSalir):
                this.finishAffinity();
                break;
            case (R.id.buttonPreferencias):

                break;
        }
    }
}