package com.olmo.JuegoDeUr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.olmo.JuegoDeUr.Repository.MiBaseDeDatos;

public class PantallaCarga extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.carga);
        mp.start();
        ImageView gif = findViewById(R.id.imageViewGif);
        Glide.with(this).load(R.drawable.carga).into(gif);

        MiBaseDeDatos bd = new MiBaseDeDatos(PantallaCarga.this);
        if(bd.obtenerPartidas().isEmpty() ){
            bd.insertarPartida("Olmo", "Antonio Resines", 73);
            bd.insertarPartida("Rubén", "Olmo", 87);
            bd.insertarPartida("Laura", "Olmo", 24);
            bd.insertarPartida("Olmo", "Manolete", 69);

        }


        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(4000);  //Delay of 10 seconds
                } catch (Exception e) {

                } finally {
                    Intent i = new Intent(
                            PantallaCarga.this,
                            MenuPrincipal.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}