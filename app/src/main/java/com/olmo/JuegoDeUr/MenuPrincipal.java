package com.olmo.JuegoDeUr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.olmo.JuegoDeUr.Repository.MiBaseDeDatos;

import org.w3c.dom.Text;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener {

    Button buttonJugar,buttonPartidas,buttonSalir,buttonPreferencias;
    Intent intent;
    EditText jugador1,jugador2;
    TextView turnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        buttonJugar = findViewById(R.id.buttonJugar); buttonJugar.setOnClickListener(this);
        buttonPartidas = findViewById(R.id.buttonPartidas); buttonPartidas.setOnClickListener(this);
        buttonSalir = findViewById(R.id.buttonSalir); buttonSalir.setOnClickListener(this);
        buttonPreferencias = findViewById(R.id.buttonPreferencias); buttonPreferencias.setOnClickListener(this);



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
                MiBaseDeDatos bd = new MiBaseDeDatos(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Get the layout inflater
                LayoutInflater inflater = this.getLayoutInflater();
                builder.setTitle("REGISTRAR PARTIDA");
                final View inflator = inflater.inflate(R.layout.add_partida, null);
                jugador1 = (EditText) inflator.findViewById(R.id.jugador1);
                jugador2 = (EditText) inflator.findViewById(R.id.jugador2);
                turnos = (TextView) inflator.findViewById(R.id.turnos);
                turnos.setText("73");
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflator)
                        .setPositiveButton("Añadir",new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                bd.insertarPartida(jugador1.getText().toString(), jugador2.getText().toString(),Integer.parseInt(turnos.getText().toString()));

                            }
                        })
                        .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog =  builder.create();
                dialog.show();
              /*  AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincipal.this);
                LayoutInflater inflater = MenuPrincipal.this.getLayoutInflater();
                alerta.setTitle("NUEVA PARTIDA");
                alerta.setView(inflater.inflate(R.layout.add_partida, null))
                        .setPositiveButton("Ok",null)
                        .setNegativeButton("Cancel", null);
                alerta.create();
                alerta.show();*/
                break;
        }
    }
}