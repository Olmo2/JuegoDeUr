package com.olmo.JuegoDeUr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.olmo.JuegoDeUr.Bean.Ficha;
import com.olmo.JuegoDeUr.Bean.Jugador;
import com.olmo.JuegoDeUr.Bean.Tablero;
import com.olmo.JuegoDeUr.Bean.Turno;
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
    private int turnos;
   private Map<Ficha, ImageView> selectorFichasNegras, selectorFichasBlancas;
    /**
     * 0:Ancho, 1:Alto
     */
    private int[] dimensionesTablero = new int[2];

    private final int[] casaNegro = new int[]{4, 0};
    private final int[] casaBlanco = new int[]{4, 2};

    private ConstraintLayout tableroView;
    /**
     * A->Negro, B->Blanco
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

    /**Ficha auxiliar*/
    private ImageView fichaMover;

    private TextView textViewInfoNegro, textViewInfoBlanco,textViewFichasNegro,textViewFichasBlanco;
    private Button buttonPausa;
    private boolean dados, ficha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        /** INICIALIZACIONES */
        thread = new ThreadJuego();
        tableroView = findViewById(R.id.tableroView);

        textViewInfoNegro = findViewById(R.id.textViewInfoNegro);
        textViewInfoBlanco = findViewById(R.id.textViewInfoBlanco);
        textViewFichasNegro = findViewById(R.id.textViewFichasNegro);
        textViewFichasBlanco = findViewById(R.id.textViewFichasBlanco);
        selectorFichasNegras=new HashMap<>();
        selectorFichasBlancas=new HashMap<>();
        turnos=0;

        dadosNegro = findViewById(R.id.dadosNegro);
        dadosNegro.setOnClickListener(this);

        dadosBlanco = findViewById(R.id.dadosBlanco);
        dadosBlanco.setOnClickListener(this);

        ficha1N = findViewById(R.id.ficha1N); ficha1N.setOnClickListener(this);
        ficha2N = findViewById(R.id.ficha2N); ficha2N.setOnClickListener(this);
        ficha3N = findViewById(R.id.ficha3N); ficha3N.setOnClickListener(this);
        ficha4N = findViewById(R.id.ficha4N); ficha4N.setOnClickListener(this);
        ficha5N = findViewById(R.id.ficha5N); ficha5N.setOnClickListener(this);
        ficha6N = findViewById(R.id.ficha6N); ficha6N.setOnClickListener(this);
        ficha7N = findViewById(R.id.ficha7N); ficha7N.setOnClickListener(this);

        ficha1B = findViewById(R.id.ficha1B); ficha1B.setOnClickListener(this);
        ficha2B = findViewById(R.id.ficha2B); ficha2B.setOnClickListener(this);
        ficha3B = findViewById(R.id.ficha3B); ficha3B.setOnClickListener(this);
        ficha4B = findViewById(R.id.ficha4B); ficha4B.setOnClickListener(this);
        ficha5B = findViewById(R.id.ficha5B); ficha5B.setOnClickListener(this);
        ficha6B = findViewById(R.id.ficha6B); ficha6B.setOnClickListener(this);
        ficha7B = findViewById(R.id.ficha7B); ficha7B.setOnClickListener(this);

        tablero = new Tablero();
        util = new Utilidades();
        jB = new Jugador();
        jN = new Jugador();
        turno = new Turno(0, false);
        enCasaB = enCasaN = 0;
        tirada = fich = 0;
        dados = ficha = false;

        tablero.setRecorridoBlanco(util.generarRecorrido(1));
        tablero.setRecorridoNegro(util.generarRecorrido(2));

        System.out.println(tablero.getRecorridoBlanco().size());

        jB.setColor(true);
        jB.setFichas(util.generarFichas(jB.getColor()));

        selectorFichasBlancas.put(jB.getFichas().get(0),ficha1B);
        selectorFichasBlancas.put(jB.getFichas().get(1),ficha2B);
        selectorFichasBlancas.put(jB.getFichas().get(2),ficha3B);
        selectorFichasBlancas.put(jB.getFichas().get(3),ficha4B);
        selectorFichasBlancas.put(jB.getFichas().get(4),ficha5B);
        selectorFichasBlancas.put(jB.getFichas().get(5),ficha6B);
        selectorFichasBlancas.put(jB.getFichas().get(6),ficha7B);

        jN.setColor(false);
        jN.setFichas(util.generarFichas(jN.getColor()));

        selectorFichasNegras.put(jN.getFichas().get(0),ficha1N);
        selectorFichasNegras.put(jN.getFichas().get(1),ficha2N);
        selectorFichasNegras.put(jN.getFichas().get(2),ficha3N);
        selectorFichasNegras.put(jN.getFichas().get(3),ficha4N);
        selectorFichasNegras.put(jN.getFichas().get(4),ficha5N);
        selectorFichasNegras.put(jN.getFichas().get(5),ficha6N);
        selectorFichasNegras.put(jN.getFichas().get(6),ficha7N);

        System.out.println("Empieza el juego");
        textViewInfoNegro.setText("Empieza el juego");
        textViewInfoBlanco.setText("Empieza el juego");

        dimensionesTablero[0] = tableroView.getWidth();
        dimensionesTablero[1] = tableroView.getHeight();

        thread.start();
    }

    /**
     * @param turno Booleano con el color del jugador del turno
     * @return Integer
     */
    public Boolean juego(Turno turno) {
        if(turnos==0){
            dimensionesTablero[0] = tableroView.getWidth();
            dimensionesTablero[1] = tableroView.getHeight();

            util.setCoordenadasIniciales(jB,selectorFichasBlancas);
            util.setCoordenadasIniciales(jN,selectorFichasNegras);

        }



        System.out.println("Ancho: " + dimensionesTablero[0]);
        System.out.println("alto: " + dimensionesTablero[1]);
        Boolean roseta = false;
        dados = false;
        ficha = false;
        runOnUiThread(() -> texto(turno.getColor(), "Te toca Crack " + "Tira los dados"));
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
            runOnUiThread(() -> texto(turno.getColor(), "Pierdes turno pringao :P"));
            try {
                thread.sleep(2500);
            } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        } else{

            if (turno.getColor()) {
                if(  (!util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 1) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 2) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 3) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 4) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 5) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 6) &&
                        !util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada, 7))) {
                    runOnUiThread(() -> texto(turno.getColor(), "No puedes mover ninguna ficha, :P"));
                    try {
                        thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    do {
                        runOnUiThread(() -> texto(turno.getColor(), "Te ha salido un " + tirada + " Elige una ficha"));
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
                                toast = Toast.makeText(getApplicationContext(), "¡Destino ocupado!", Toast.LENGTH_SHORT);
                                toast.show();
                            });
                        }
                    } while (!util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada,
                            fich));

                    roseta = util.moverFicha(jB, jN, fich - 1, tirada, tablero.getRecorridoBlanco(),
                            tablero.getRecorridoNegro());
                    /** Accion de comer sobre negras*/
                    if(jB.getFichas().get(fich - 1).getPosicion()>14){
                        runOnUiThread(() -> {
                            util.posicionarFicha(fichaMover, dimensionesTablero,
                                    jB.getFichas().get(fich - 1),
                                    5,
                                    2 );
                            fichaMover.setVisibility(View.GONE);
                        });
                    }else{
                        if (util.comida) {
                        runOnUiThread(() -> util.posicionarFicha(selectorFichasNegras.get(util.fichaComida), dimensionesTablero,
                                util.fichaComida,
                                casaNegro[0],
                                casaNegro[1]));
                    }
                        runOnUiThread(() -> util.posicionarFicha(fichaMover, dimensionesTablero,
                                jB.getFichas().get(fich - 1),
                                tablero.getRecorridoBlanco().get(jB.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[0],
                                tablero.getRecorridoBlanco().get(jB.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[1]));
                    }
                }
            } else {
                if ((!util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 1) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 2) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 3) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 4) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 5) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 6) &&
                        !util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada, 7))) {
                    runOnUiThread(() -> texto(turno.getColor(), "No puedes mover ninguna ficha, :P"));
                    try {
                        thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    do {
                        runOnUiThread(() -> texto(turno.getColor(), "Te ha salido un " + tirada + " Elige una ficha"));
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
                                toast = Toast.makeText(getApplicationContext(), "¡Destino ocupado!", Toast.LENGTH_SHORT);
                                toast.show();
                            });
                        }
                    } while (!util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada,
                            fich));
                    roseta = util.moverFicha(jN, jB, fich - 1, tirada, tablero.getRecorridoNegro(),
                            tablero.getRecorridoBlanco());
                    /** Accion de comer sobre blancas*/
                    if(jN.getFichas().get(fich - 1).getPosicion()>14){
                        runOnUiThread(() -> {
                            util.posicionarFicha(fichaMover, dimensionesTablero,
                                    jB.getFichas().get(fich - 1),
                                    5,
                                    0 );

                            fichaMover.setVisibility(View.GONE);
                        });
                    }else{
                        if (util.comida) {
                        runOnUiThread(() -> util.posicionarFicha(selectorFichasBlancas.get(util.fichaComida), dimensionesTablero,
                                util.fichaComida,
                                casaBlanco[0],
                                casaBlanco[1]));
                    }
                    runOnUiThread(() -> util.posicionarFicha(fichaMover, dimensionesTablero,
                            jN.getFichas().get(fich - 1),
                            tablero.getRecorridoNegro().get(jN.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[0],
                            tablero.getRecorridoNegro().get(jN.getFichas().get(fich - 1).getPosicion()).getCoordenadas()[1]));
                    }
                }
                runOnUiThread(() -> {
                    textViewFichasBlanco.setText("En casa " + enCasaB);
                    textViewFichasNegro.setText("En casa " + enCasaN);
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
            textViewInfoNegro.setText("A ti no te toca :(");
            textViewInfoBlanco.setText(str);
        } else {
            textViewInfoBlanco.setText("A ti no te toca :(");
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

        }
    }

    class ThreadJuego extends Thread {
        @Override
        public void run() {
            while (enCasaB != 7 && enCasaN != 7) {
                runOnUiThread(() -> {
                    textViewFichasNegro.setText("Negras en casa " + enCasaN);
                    textViewFichasBlanco.setText("Blancas en casa " + enCasaB);
                });
                if (!juego(turno)) {
                    turno.setColor(!turno.getColor());
                    util.comida=false;
                }else{
                    runOnUiThread(() -> {
                            toast = Toast.makeText(getApplicationContext(),"Turno Extra", Toast.LENGTH_SHORT);
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
                if(enCasaB==7){
                    textViewInfoBlanco.setText("¡Victoria!");
                    textViewInfoNegro.setText("Derrota :(");
                }else{
                    textViewInfoNegro.setText("¡Victoria!");
                    textViewInfoBlanco.setText("Derrota :(");
                }
            });
        }
    }
}