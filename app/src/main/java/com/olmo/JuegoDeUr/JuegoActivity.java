package com.olmo.JuegoDeUr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.olmo.JuegoDeUr.Bean.Ficha;
import com.olmo.JuegoDeUr.Bean.Jugador;
import com.olmo.JuegoDeUr.Bean.Tablero;
import com.olmo.JuegoDeUr.Bean.Turno;
import com.olmo.JuegoDeUr.Repository.MiBaseDeDatos;
import com.olmo.JuegoDeUr.Service.Utilidades;

import java.util.HashMap;
import java.util.Map;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * DECLARACIONES
     */
    private ThreadJuego thread;
    private Tablero tablero;
    private Turno turno;
    private Utilidades util;
    private Jugador jB;
    private Jugador jN;
    private Integer tirada, fich, ocupantes, posicion;
    private Integer enCasaB, enCasaN;
    private Toast toast;
    private int contadorTurnos;
    private Map<Ficha, ImageView> selectorFichasNegras, selectorFichasBlancas;
    private MediaPlayer mp;
    /**
     * 0:Ancho, 1:Alto
     */
    private int[] dimensionesTablero = new int[2];
    private final int[] casaNegro = new int[]{4, 0};
    private final int[] casaBlanco = new int[]{4, 2};

    private ConstraintLayout tableroView;
    /**
     * N->Negro, B->Blanco
     */
    private ImageView dadosNegro, dadosBlanco;
    /**
     * Fichas Negro
     */
    private ImageView ficha1N, ficha2N, ficha3N, ficha4N, ficha5N, ficha6N, ficha7N;
    /**
     * Fichas Blanco
     */
    private ImageView ficha1B, ficha2B, ficha3B, ficha4B, ficha5B, ficha6B, ficha7B;

    /**
     * Ficha auxiliar
     */
    private ImageView fichaMover;

    private TextView textViewInfoNegro, textViewInfoBlanco, textViewFichasNegro, textViewFichasBlanco;
    private Button buttonPausa;

    private EditText jugador1, jugador2;
    private TextView turnos;
    private boolean dados, ficha;
    private SharedPreferences preferences;
    private String colorFichaKey, colorTableroKey, sonidoKey;
    private View layout;
    private TextView txt;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        /** INICIALIZACIONES */
        thread = new ThreadJuego();
        tableroView = findViewById(R.id.tableroView);

        textViewInfoNegro = findViewById(R.id.textViewInfoNegro);
        textViewInfoBlanco = findViewById(R.id.textViewInfoBlanco);
        textViewFichasNegro = findViewById(R.id.textViewFichasNegro);
        textViewFichasBlanco = findViewById(R.id.textViewFichasBlanco);
        selectorFichasNegras = new HashMap<>();
        selectorFichasBlancas = new HashMap<>();
        contadorTurnos = 0;

        dadosNegro = findViewById(R.id.dadosNegro);
        dadosNegro.setOnClickListener(this);

        dadosBlanco = findViewById(R.id.dadosBlanco);
        dadosBlanco.setOnClickListener(this);

        buttonPausa = findViewById(R.id.buttonPausa);
        buttonPausa.setOnClickListener(this);

        ficha1N = findViewById(R.id.ficha1N);
        ficha1N.setOnClickListener(this);
        ficha2N = findViewById(R.id.ficha2N);
        ficha2N.setOnClickListener(this);
        ficha3N = findViewById(R.id.ficha3N);
        ficha3N.setOnClickListener(this);
        ficha4N = findViewById(R.id.ficha4N);
        ficha4N.setOnClickListener(this);
        ficha5N = findViewById(R.id.ficha5N);
        ficha5N.setOnClickListener(this);
        ficha6N = findViewById(R.id.ficha6N);
        ficha6N.setOnClickListener(this);
        ficha7N = findViewById(R.id.ficha7N);
        ficha7N.setOnClickListener(this);

        ficha1B = findViewById(R.id.ficha1B);
        ficha1B.setOnClickListener(this);
        ficha2B = findViewById(R.id.ficha2B);
        ficha2B.setOnClickListener(this);
        ficha3B = findViewById(R.id.ficha3B);
        ficha3B.setOnClickListener(this);
        ficha4B = findViewById(R.id.ficha4B);
        ficha4B.setOnClickListener(this);
        ficha5B = findViewById(R.id.ficha5B);
        ficha5B.setOnClickListener(this);
        ficha6B = findViewById(R.id.ficha6B);
        ficha6B.setOnClickListener(this);
        ficha7B = findViewById(R.id.ficha7B);
        ficha7B.setOnClickListener(this);

        tablero = new Tablero();
        util = new Utilidades();
        jB = new Jugador();
        jN = new Jugador();
        turno = new Turno(0, true);
        enCasaB = enCasaN = 0;
        tirada = fich = 0;
        dados = ficha = false;

        tablero.setRecorridoBlanco(util.generarRecorrido(1));
        tablero.setRecorridoNegro(util.generarRecorrido(2));

        jB.setColor(true);
        jB.setFichas(util.generarFichas(jB.getColor()));

        selectorFichasBlancas.put(jB.getFichas().get(0), ficha1B);
        selectorFichasBlancas.put(jB.getFichas().get(1), ficha2B);
        selectorFichasBlancas.put(jB.getFichas().get(2), ficha3B);
        selectorFichasBlancas.put(jB.getFichas().get(3), ficha4B);
        selectorFichasBlancas.put(jB.getFichas().get(4), ficha5B);
        selectorFichasBlancas.put(jB.getFichas().get(5), ficha6B);
        selectorFichasBlancas.put(jB.getFichas().get(6), ficha7B);

        jN.setColor(false);
        jN.setFichas(util.generarFichas(jN.getColor()));

        selectorFichasNegras.put(jN.getFichas().get(0), ficha1N);
        selectorFichasNegras.put(jN.getFichas().get(1), ficha2N);
        selectorFichasNegras.put(jN.getFichas().get(2), ficha3N);
        selectorFichasNegras.put(jN.getFichas().get(3), ficha4N);
        selectorFichasNegras.put(jN.getFichas().get(4), ficha5N);
        selectorFichasNegras.put(jN.getFichas().get(5), ficha6N);
        selectorFichasNegras.put(jN.getFichas().get(6), ficha7N);

        textViewInfoNegro.setText("Empieza el juego");
        textViewInfoBlanco.setText("Empieza el juego");

        dimensionesTablero[0] = tableroView.getWidth();
        dimensionesTablero[1] = tableroView.getHeight();

        mp = MediaPlayer.create(this, R.raw.dados);

        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        colorFichaKey = "colorFichaKey";
        colorTableroKey = "colorTableroKey";
        sonidoKey = "sonidoKey";

        if (preferences.getBoolean(colorFichaKey, true)) {
            runOnUiThread(() -> {
                util.setColorFichas(selectorFichasBlancas, R.drawable.white, jB);
                util.setColorFichas(selectorFichasNegras, R.drawable.black, jN);
            });
        } else {
            runOnUiThread(() -> {
                util.setColorFichas(selectorFichasBlancas, R.drawable.green, jB);
                util.setColorFichas(selectorFichasNegras, R.drawable.red, jN);
            });
        }

        if (preferences.getBoolean(colorTableroKey, true)) {
            runOnUiThread(() -> tableroView.setBackgroundResource(R.drawable.board));
        } else {
            runOnUiThread(() -> tableroView.setBackgroundResource(R.drawable.board_black));
        }

        toast = new Toast(getApplicationContext());

        thread.start();
    }

    /**
     * @param turno Booleano con el color del jugador del turno
     * @return Integer
     */
    public Boolean juego(Turno turno) {

        dimensionesTablero[0] = tableroView.getWidth();
        dimensionesTablero[1] = tableroView.getHeight();

        util.setCoordenadasIniciales(jB, selectorFichasBlancas);
        util.setCoordenadasIniciales(jN, selectorFichasNegras);

        Boolean roseta = false;
        dados = false;
        ficha = false;
        runOnUiThread(() -> texto(turno.getColor(), "Te toca Crack"));
        do {
            try {
                thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!dados);
        dados = false;
        ficha = false;
        tirada = this.util.tirarDados();

        if (tirada == 0) {
            runOnUiThread(() -> {
                texto(turno.getColor(), tirada.toString());
                if (turno.getColor()) {
                    inflater = getLayoutInflater();
                    layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                    //Configure message
                    txt = (TextView) layout.findViewById(R.id.toast_msg);
                    toast.setMargin(0, 0);
                } else {
                    inflater = getLayoutInflater();
                    layout = inflater.inflate(R.layout.toast_layout_reverse, (ViewGroup) findViewById(R.id.toast_layout_root_reverse));
                    //Configure message
                    txt = (TextView) layout.findViewById(R.id.toast_msg_reverse);
                    toast.setMargin(0, 0.73f);
                }
                txt.setText("¡Pierdes turno pringao!");
                //Configure toast and display
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            });

            try {
                thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            /**si turno color == true Blanco*/
            if (turno.getColor()) {
                if ((!util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 1) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 2) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 3) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 4) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 5) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 6) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 7))) {
                    runOnUiThread(() -> {
                        inflater = getLayoutInflater();
                        layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                        //Configure message
                        txt = (TextView) layout.findViewById(R.id.toast_msg);
                        toast.setMargin(0, 0);
                        txt.setText("¡No puedes mover, pierdes turno!");
                        //Configure toast and display
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                    });
                    try {
                        thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    do {
                        runOnUiThread(() -> texto(turno.getColor(), tirada.toString()));
                        do {
                            try {
                                thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        while (!ficha);
                        dados = false;
                        ficha = false;
                        if (!util.ocupantes) {
                            runOnUiThread(() -> {
                                inflater = getLayoutInflater();
                                layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                                //Configure message
                                txt = (TextView) layout.findViewById(R.id.toast_msg);
                                toast.setMargin(0, 0);
                                txt.setText("¡Destino ocupado!");
                                //Configure toast and display
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(layout);
                                toast.show();
                            });
                        }
                    } while (!util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada,
                            fich));

                    roseta = util.moverFicha(jB, jN, fich - 1, tirada, tablero.getRecorridoBlanco(),
                            tablero.getRecorridoNegro());
                    /**Llegada a casa*/
                    if (jB.getFichas().get(fich - 1).getPosicion() > 14) {
                        runOnUiThread(() -> {
                            util.posicionarFicha(fichaMover, dimensionesTablero,
                                    jB.getFichas().get(fich - 1), 5, 2);
                            fichaMover.setVisibility(View.GONE);
                        });
                    } else {
                        /** Accion de comer sobre negras*/
                        if (util.comida) {
                            runOnUiThread(() -> {
                                util.posicionarFicha(selectorFichasNegras.get(util.fichaComida), dimensionesTablero,
                                        util.fichaComida,
                                        casaNegro[0],
                                        casaNegro[1]);

                                inflater = getLayoutInflater();
                                layout = inflater.inflate(R.layout.toast_layout_reverse, (ViewGroup) findViewById(R.id.toast_layout_root_reverse));
                                //Configure message
                                txt = (TextView) layout.findViewById(R.id.toast_msg_reverse);
                                toast.setMargin(0, 0.73f);
                                txt.setText("¡Pa Casa!");
                                //Configure toast and display
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(layout);
                                toast.show();
                            });
                        }
                        runOnUiThread(() -> util.posicionarFicha(fichaMover, dimensionesTablero,
                                jB.getFichas().get(fich - 1),
                                tablero.getRecorridoBlanco().get(jB.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[0],
                                tablero.getRecorridoBlanco().get(jB.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[1]));
                    }
                }
                /**si turno color == false Negro*/
            } else {
                if ((!util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 1) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 2) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 3) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 4) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 5) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 6) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 7))) {
                    runOnUiThread(() -> {
                        texto(turno.getColor(), tirada.toString());

                        inflater = getLayoutInflater();
                        layout = inflater.inflate(R.layout.toast_layout_reverse, (ViewGroup) findViewById(R.id.toast_layout_root_reverse));
                        //Configure message
                        txt = (TextView) layout.findViewById(R.id.toast_msg_reverse);
                        toast.setMargin(0, 0.73f);
                        txt.setText("¡No puedes mover, pierdes turno!");
                        //Configure toast and display
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                    });
                    try {
                        thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    do {
                        runOnUiThread(() -> texto(turno.getColor(), tirada.toString()));
                        do {
                            try {
                                thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        while (!ficha);
                        dados = false;
                        ficha = false;
                        if (!util.ocupantes) {
                            runOnUiThread(() -> {
                                inflater = getLayoutInflater();
                                layout = inflater.inflate(R.layout.toast_layout_reverse, (ViewGroup) findViewById(R.id.toast_layout_root_reverse));
                                //Configure message
                                txt = (TextView) layout.findViewById(R.id.toast_msg_reverse);
                                toast.setMargin(0, 0.73f);
                                txt.setText("¡Destino Ocupado!");
                                //Configure toast and display
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(layout);
                                toast.show();
                            });
                        }
                    } while (!util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada,
                            fich));
                    roseta = util.moverFicha(jN, jB, fich - 1, tirada, tablero.getRecorridoNegro(),
                            tablero.getRecorridoBlanco());
                    /**Llegada a casa*/
                    if (jN.getFichas().get(fich - 1).getPosicion() > 14) {
                        runOnUiThread(() -> {
                            util.posicionarFicha(fichaMover, dimensionesTablero,
                                    jB.getFichas().get(fich - 1), 4, 0);
                            fichaMover.setVisibility(View.GONE);
                        });
                    } else {
                        /** Accion de comer sobre blancas*/
                        if (util.comida) {
                            runOnUiThread(() -> {
                                util.posicionarFicha(selectorFichasBlancas.get(util.fichaComida), dimensionesTablero,
                                        util.fichaComida,
                                        casaBlanco[0],
                                        casaBlanco[1]);
                                inflater = getLayoutInflater();
                                layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                                //Configure message
                                txt = (TextView) layout.findViewById(R.id.toast_msg);
                                toast.setMargin(0, 0);
                                txt.setText("¡Pa Casa!");
                                //Configure toast and display
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(layout);
                                toast.show();
                            });
                        }
                        runOnUiThread(() -> util.posicionarFicha(fichaMover, dimensionesTablero,
                                jN.getFichas().get(fich - 1),
                                tablero.getRecorridoNegro().get(jN.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[0],
                                tablero.getRecorridoNegro().get(jN.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[1]));
                    }
                }
                runOnUiThread(() -> {
                    textViewFichasBlanco.setText("En casa: " + enCasaB);
                    textViewFichasNegro.setText("En casa: " + enCasaN);
                });
            }
        }
        dados = false;
        ficha = false;
        return roseta;
        /* Compro */
    }

    public void texto(boolean color, String str) {
        if (color) {
            textViewInfoNegro.setText(":(");
            textViewInfoBlanco.setText(str);
        } else {
            textViewInfoBlanco.setText(":(");
            textViewInfoNegro.setText(str);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**Botones Tirar Dados*/
            case (R.id.dadosNegro):

                if (!turno.getColor()) {
                    tirada = this.util.tirarDados();
                    dados = true;
                } else
                    dados = false;
                break;

            case (R.id.dadosBlanco):
                if (turno.getColor()) {
                    tirada = this.util.tirarDados();
                    dados = true;
                } else
                    dados = false;
                break;

            /**Fichas Negro*/
            case (R.id.ficha1N):
                if (!turno.getColor()) {
                    fich = 1;
                    fichaMover = ficha1N;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha2N):
                if (!turno.getColor()) {
                    fich = 2;
                    fichaMover = ficha2N;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha3N):
                if (!turno.getColor()) {
                    fich = 3;
                    fichaMover = ficha3N;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha4N):
                if (!turno.getColor()) {
                    fich = 4;
                    fichaMover = ficha4N;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha5N):
                if (!turno.getColor()) {
                    fich = 5;
                    fichaMover = ficha5N;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha6N):
                if (!turno.getColor()) {
                    fich = 6;
                    fichaMover = ficha6N;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha7N):
                if (!turno.getColor()) {
                    fich = 7;
                    fichaMover = ficha7N;
                    ficha = true;
                } else ficha = false;
                break;

            /**Fichas Blanco*/
            case (R.id.ficha1B):
                if (turno.getColor()) {
                    fich = 1;
                    fichaMover = ficha1B;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha2B):
                if (turno.getColor()) {
                    fich = 2;
                    fichaMover = ficha2B;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha3B):
                if (turno.getColor()) {
                    fich = 3;
                    fichaMover = ficha3B;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha4B):
                if (turno.getColor()) {
                    fich = 4;
                    fichaMover = ficha4B;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha5B):
                if (turno.getColor()) {
                    fich = 5;
                    fichaMover = ficha5B;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha6B):
                if (turno.getColor()) {
                    fich = 6;
                    fichaMover = ficha6B;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.ficha7B):
                if (turno.getColor()) {
                    fich = 7;
                    fichaMover = ficha7B;
                    ficha = true;
                } else ficha = false;
                break;
            case (R.id.buttonPausa):
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Get the layout inflater
                LayoutInflater inflater = this.getLayoutInflater();
                builder.setTitle("MENÚ DE PAUSA");
                final View inflator = inflater.inflate(R.layout.menu_pausa, null);
                builder.setView(inflator)
                        .setNegativeButton("Volver al juego", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                Button menu = inflator.findViewById(R.id.menuPrincipal);
                menu.setOnClickListener(this::onClick);
                Button salir = inflator.findViewById(R.id.salirDelJuego);
                salir.setOnClickListener(this::onClick);
                Button preferencias = inflator.findViewById(R.id.menuPreferencias);
                preferencias.setOnClickListener(this::onClick);
                dialog.show();
                break;
            case (R.id.menuPrincipal):
                Intent menuPrincipal = new Intent(JuegoActivity.this, MenuPrincipal.class);
                startActivity(menuPrincipal);
                break;
            case (R.id.salirDelJuego):
                this.finishAffinity();
                break;

            case (R.id.menuPreferencias):
                Intent menuPreferencias = new Intent(JuegoActivity.this, PreferenciasActivity.class);
                startActivity(menuPreferencias);
                break;

        }
        if (dados && preferences.getBoolean(sonidoKey, true)) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp = MediaPlayer.create(this, R.raw.dados);
            mp.start();
        } else if (ficha && preferences.getBoolean(sonidoKey, true)) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp = MediaPlayer.create(this, R.raw.ficha);
            mp.start();
        }
    }

    class ThreadJuego extends Thread {
        @Override
        public void run() {
            util.colocarFichasInicio(jB, selectorFichasBlancas, dimensionesTablero, casaBlanco);
            util.colocarFichasInicio(jN, selectorFichasNegras, dimensionesTablero, casaNegro);
            while (enCasaB != 7 && enCasaN != 7) {
                runOnUiThread(() -> {
                    textViewFichasNegro.setText("En casa " + enCasaN);
                    textViewFichasBlanco.setText("En casa " + enCasaB);
                });
                if (!juego(turno)) {
                    turno.setColor(!turno.getColor());
                    util.comida = false;
                    contadorTurnos++;
                } else {
                    runOnUiThread(() -> {
                        toast = Toast.makeText(getApplicationContext(), "Turno Extra", Toast.LENGTH_SHORT);
                        toast.show();
                    });
                }
                enCasaB = 0;
                enCasaN = 0;
                for (int i = 0; i < jB.getFichas().size(); i++) {
                    if (!jB.getFichas().get(i).getEnJuego()) {
                        enCasaB++;
                    } else if (!jN.getFichas().get(i).getEnJuego()) {
                        enCasaN++;
                    }
                }

            }
            runOnUiThread(() -> {
                if (enCasaB == 7) {
                    textViewInfoBlanco.setText("¡Victoria!");
                    textViewInfoNegro.setText("Derrota :(");
                } else {
                    textViewInfoNegro.setText("¡Victoria!");
                    textViewInfoBlanco.setText("Derrota :(");
                }


                MiBaseDeDatos bd = new MiBaseDeDatos(JuegoActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(JuegoActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = JuegoActivity.this.getLayoutInflater();
                builder.setTitle("REGISTRAR PARTIDA");
                final View inflator = inflater.inflate(R.layout.add_partida, null);
                jugador1 = (EditText) inflator.findViewById(R.id.jugador1);
                jugador2 = (EditText) inflator.findViewById(R.id.jugador2);
                turnos = (TextView) inflator.findViewById(R.id.turnos);
                turnos.setText(contadorTurnos);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflator)
                        .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                bd.insertarPartida(jugador1.getText().toString(), jugador2.getText().toString(), Integer.parseInt(turnos.getText().toString()));
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            });
        }
    }
}