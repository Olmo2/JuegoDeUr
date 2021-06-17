package com.olmo.JuegoDeUr;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PantallaCarga extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.carga);
        mp.start();
        ImageView gif = findViewById(R.id.imageViewGif);
        Glide.with(this).load(R.drawable.carga).into(gif);

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