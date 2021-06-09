package com.olmo.JuegoDeUr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.olmo.JuegoDeUr.Bean.Jugador;
import com.olmo.JuegoDeUr.Bean.Tablero;
import com.olmo.JuegoDeUr.Bean.Turno;
import com.olmo.JuegoDeUr.Service.Utilidades;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * DECLARACIONES
     */
    private final Integer DADOS=0;
    private final Integer TIRADA = 1;
    private ThreadJuego thread;
    private Tablero tablero;
    private Turno turno;
    private Utilidades util;
    private Jugador jB;
    private Jugador jN;
    private Integer tirada, fich, ocupantes, posicion;
    private Integer enCasaB, enCasaN;
    /** 0:Ancho, 1:Alto*/
    private int[] dimensionesTablero = new int[2];
    private ConstraintLayout tableroView;
    /**
     * A->Negro, B->Blanco*/
    private Button buttonDadosA, buttonDadosB;
    /**Botones Negro*/
    private Button button1A,button2A,button3A,button4A,button5A,button6A,button7A;
    /**Botones Blanco*/
    private Button button1B,button2B,button3B,button4B,button5B,button6B,button7B;
    private ImageView ficha1B,fichaMover;

    private TextView textViewInfoA, textViewInfoB;
    private boolean dados,ficha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        /** INICIALIZACIONES */
        thread = new ThreadJuego();
        tableroView = findViewById(R.id.tableroView);

        textViewInfoA = findViewById(R.id.textViewInfoNegro);
        textViewInfoB = findViewById(R.id.textViewInfoBlanco);

        buttonDadosA = findViewById(R.id.buttonDadosA);
        buttonDadosA.setOnClickListener(this);

        buttonDadosB = findViewById(R.id.buttonDadosB);
        buttonDadosB.setOnClickListener(this);

        button1A = findViewById(R.id.button1A); button1A.setOnClickListener(this);
        button2A = findViewById(R.id.button2A); button2A.setOnClickListener(this);
        button3A = findViewById(R.id.button3A); button3A.setOnClickListener(this);
        button4A = findViewById(R.id.button4A); button4A.setOnClickListener(this);
        button5A = findViewById(R.id.button5A); button5A.setOnClickListener(this);
        button6A = findViewById(R.id.button6A); button6A.setOnClickListener(this);
        button7A = findViewById(R.id.button7A); button7A.setOnClickListener(this);

        button1B = findViewById(R.id.button1B); button1B.setOnClickListener(this);
        button2B = findViewById(R.id.button2B); button2B.setOnClickListener(this);
        button3B = findViewById(R.id.button3B); button3B.setOnClickListener(this);
        button4B = findViewById(R.id.button4B); button4B.setOnClickListener(this);
        button5B = findViewById(R.id.button5B); button5B.setOnClickListener(this);
        button6B = findViewById(R.id.button6B); button6B.setOnClickListener(this);
        button7B = findViewById(R.id.button7B); button7B.setOnClickListener(this);
        ficha1B = findViewById(R.id.ficha1B); ficha1B.setOnClickListener(this);

        tablero = new Tablero();
        util = new Utilidades();
        jB = new Jugador();
        jN = new Jugador();
        turno = new Turno(0, false);
        enCasaB = enCasaN = 0;
        tirada = fich = 0;
        dados =ficha= false;

        tablero.setRecorridoBlanco(util.generarRecorrido(1));
        tablero.setRecorridoNegro(util.generarRecorrido(1));

        System.out.println(tablero.getRecorridoBlanco().size());

        jB.setColor(true);
        jB.setFichas(util.generarFichas(jB.getColor()));

        jN.setColor(false);
        jN.setFichas(util.generarFichas(jN.getColor()));

         System.out.println("Empieza el juego");
        textViewInfoA.setText("Empieza el juego");
        textViewInfoB.setText("Empieza el juego");





        textViewInfoA.setText("Fin del Juego");
        textViewInfoB.setText("Fin del Juego");


    thread.start();
    }

    /**
     * @param turno Booleano con el color del jugador del turno
     * @return Integer */
    public Boolean juego(Turno turno){
        dimensionesTablero[0] = tableroView.getWidth();
        dimensionesTablero[1] = tableroView.getHeight();
        System.out.println("Ancho: " + dimensionesTablero[0]);
        System.out.println("alto: " + dimensionesTablero[1]);
        Boolean roseta = false;
        do{
            try {
                thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!dados);
        dados=false;
        ficha=false;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                texto(turno.getColor(),"Te toca Crack " + "Tira los dados");
            }
        });
        // System.out.println("Tira los dados");
        //   resp= sc.next()
        do{
            try {
                thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!dados);
        dados=false;
        ficha=false;
         tirada= this.util.tirarDados();

        //Estado  tirada AKA 0

        //   System.out.println("Te ha salido un " + tirada);
        if (tirada == 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    texto(turno.getColor(),"Pierdes turno pringao");
                }
            });

            //System.err.println("Pierdes turno pringao");
        } else {

            if (turno.getColor()) {
                do {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            texto(turno.getColor(),"Te ha salido un " + tirada + " Elige una ficha");
                        }
                    });
                    do{
                        try {
                            thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (!ficha);
                    dados=false;
                    ficha=false;
                  //  System.out.println("Elige Ficha 0-6");
                    //  fich = sc.nextInt();
                } while (!util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada,
                        fich));

                roseta = util.moverFicha(jB, jN, fich-1, tirada, tablero.getRecorridoBlanco(),
                        tablero.getRecorridoNegro());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        util.posicionarFicha(fichaMover,dimensionesTablero,
                                jB.getFichas().get(fich-1),
                                tablero.getRecorridoBlanco().get(jB.getFichas().get(fich-1).getPosicion()).getCoordenadas()[0],
                                tablero.getRecorridoBlanco().get(jB.getFichas().get(fich-1).getPosicion()).getCoordenadas()[1]);
                    }
                });

            } else {

                do {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            texto(turno.getColor(),"Te ha salido un " + tirada + " Elige una ficha");
                        }
                    });

                    do{
                        try {
                            thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (!ficha);
                    dados=false;
                    ficha=false;
                    //System.out.println("Elige Ficha 0-6");
                    //  fich = sc.nextInt();
                } while (!util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada,
                        fich));

                roseta = util.moverFicha(jN, jB, fich-1, tirada, tablero.getRecorridoNegro(),
                        tablero.getRecorridoBlanco());


            }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("******* B L A N C O *******");

                        textViewInfoB.setText("B L A N C O");
                        for (int i = 0; i < jB.getFichas().size(); i++) {
                            System.out.println(jB.getFichas().get(i));
                            textViewInfoB.append(jB.getFichas().get(i).toString());
                        }
                    }
                });

                //System.out.println(jB.getFichas().get(i));




                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("******* N E G R O *******");
                        textViewInfoA.setText("NEGRO");
                        for (int i = 0; i < jN.getFichas().size(); i++) {
                            System.out.println(jN.getFichas().get(i));
                            textViewInfoA.append(jN.getFichas().get(i).toString());
                        }
                    }
                });

                //System.out.println(jN.getFichas().get(i));

        }

        do{
            try {
                thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!dados);
        dados=false;
        ficha=false;
        return roseta;
        /* Compro */
    }

    public void texto(boolean color, String str) {
        if (color) {
            textViewInfoA.setText("A ti no tonto");
            textViewInfoB.setText(str);
            // System.out.println("Le toca al Blanco ");
        } else {
            textViewInfoB.setText("A ti no tonto");
            textViewInfoA.setText(str);
            // System.out.println("Le toca al Negro ");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**Botones Tirar Dados*/
            case (R.id.buttonDadosA):
                if (!turno.getColor()) {
                    tirada = this.util.tirarDados();
                    dados = true;
                }else
                dados=false;
                break;

            case (R.id.buttonDadosB):
                if (turno.getColor()) {
                    tirada = this.util.tirarDados();
                    dados = true;
                }else
                dados=false;
                break;

                /**Fichas Negro*/
            case (R.id.button1A):
                if (!turno.getColor()) {
                    fich = 1;
                    ficha = true;
                }else
                ficha=false;
                break;
            case (R.id.button2A):
                if (!turno.getColor()) {
                    fich = 2;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button3A):
                if (!turno.getColor()) {
                    fich = 3;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button4A):
                if (!turno.getColor()) {
                    fich = 4;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button5A):
                if (!turno.getColor()) {
                    fich = 5;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button6A):
                if (!turno.getColor()) {
                    fich = 6;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button7A):
                if (!turno.getColor()) {
                    fich = 7;
                    ficha = true;
                }else
                ficha =false;
                break;

                /**Fichas Blanco*/
            case (R.id.button1B):
                if (turno.getColor()) {
                    fich = 1;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button2B):
                if (turno.getColor()) {
                    fich = 2;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button3B):
                if (turno.getColor()) {
                    fich = 3;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button4B):
                if (turno.getColor()) {
                    fich = 4;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button5B):
                if (turno.getColor()) {
                    fich = 5;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button6B):
                if (turno.getColor()) {
                    fich = 6;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.button7B):
                if (turno.getColor()) {
                    fich = 7;
                    ficha = true;
                }else
                ficha =false;
                break;
            case (R.id.ficha1B):
                if (turno.getColor()) {
                    fich = 1;
                    fichaMover=ficha1B;
                    ficha = true;
                }else
                    ficha =false;
                break;
        }
    }


    class ThreadJuego extends Thread{


        @Override
        public void run() {
            while (enCasaB != 7 && enCasaN != 7) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewInfoA.setText("Negras en casa " + enCasaN);
                        textViewInfoB.setText("Blancas en casa " + enCasaB);
                    }
                });
                // System.out.println("Blancas en casa " + enCasaB + "Negras en casa " + enCasaN);
                if (!juego(turno)) {
                    turno.setColor(!turno.getColor());
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
        }
    }
}