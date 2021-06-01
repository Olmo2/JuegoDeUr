package com.olmo.JuegoDeUr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.olmo.JuegoDeUr.Bean.Jugador;
import com.olmo.JuegoDeUr.Bean.Tablero;
import com.olmo.JuegoDeUr.Bean.Turno;
import com.olmo.JuegoDeUr.Service.Utilidades;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * DECLARACIONES
     */
    private Tablero tablero;
    private Turno turno;
    private Utilidades util;
    private Jugador jB;
    private Jugador jN;
    private Integer tirada, fich, ocupantes, posicion;
    private Integer enCasaB, enCasaN;
    /**
     * 0:Ancho, 1:Alto
     */
    private int[] dimensionesTablero = new int[2];
    private ConstraintLayout tableroView;
    /**
     * A->Negro, B->Blanco
     */
    private Button buttonDadosA, buttonDadosB;
    private TextView textViewInfoA, textViewInfoB;
    private boolean dados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** INICIALIZACIONES */
        tableroView = findViewById(R.id.tableroView);

        textViewInfoA = findViewById(R.id.textViewInfoA);
        textViewInfoB = findViewById(R.id.textViewInfoB);

        buttonDadosA = findViewById(R.id.buttonDadosA);
        buttonDadosA.setOnClickListener(this);

        buttonDadosB = findViewById(R.id.buttonDadosB);
        buttonDadosB.setOnClickListener(this);

        dimensionesTablero[0] = tableroView.getMaxWidth();
        dimensionesTablero[1] = tableroView.getMaxHeight();

        tablero = new Tablero();
        util = new Utilidades();
        jB = new Jugador();
        jN = new Jugador();
        turno = new Turno(0, false);
        enCasaB = enCasaN = 0;
        tirada = fich = 0;
        dados = false;

        tablero.setRecorridoBlanco(util.generarRecorrido(1));
        tablero.setRecorridoNegro(util.generarRecorrido(1));

        System.out.println(tablero.getRecorridoBlanco().size());

        jB.setColor(true);
        jB.setFichas(util.generarFichas(jB.getColor()));

        jN.setColor(false);
        jN.setFichas(util.generarFichas(jN.getColor()));

        // System.out.println("Empieza el juego");
        textViewInfoA.setText("Empieza el juego");
        textViewInfoB.setText("Empieza el juego");

        while (enCasaB != 7 && enCasaN != 7) {
            textViewInfoA.setText("Negras en casa " + enCasaN);
            textViewInfoB.setText("Blancas en casa " + enCasaB);
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
        // System.out.println("Fin del Juego");
        textViewInfoA.setText("Fin del Juego");
        textViewInfoB.setText("Fin del Juego");
    }

    public Boolean juego(Turno turno) {

        Boolean roseta = false;


        texto(turno.getColor(),"Te toca Crack " + "Tira los dados");
//        if (turno.getColor()) {
//            textViewInfoA.setText("A ti no tonto");
//            textViewInfoB.setText("Te toca Crack " + "Tira los dados");
//            // System.out.println("Le toca al Blanco ");
//        } else {
//            textViewInfoB.setText("A ti no tonto");
//            textViewInfoA.setText("Te toca Crack " + "Tira los dados");
//            // System.out.println("Le toca al Negro ");
//        }

        // System.out.println("Tira los dados");
        //   resp= sc.next()
        do {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!dados);
        //  tirada= this.util.tirarDados();

        texto(turno.getColor(),"Te ha salido un " + tirada);
//        if (turno.getColor()) {
//            textViewInfoA.setText("A ti no tonto");
//            textViewInfoB.setText("Te ha salido un " + tirada);
//            // System.out.println("Le toca al Blanco ");
//        } else {
//            textViewInfoB.setText("A ti no tonto");
//            textViewInfoA.setText("Te ha salido un " + tirada);
//            // System.out.println("Le toca al Negro ");
//        }
        //   System.out.println("Te ha salido un " + tirada);
        if (tirada == 0) {
            texto(turno.getColor(),"Pierdes turno pringao");
            //System.err.println("Pierdes turno pringao");
        } else {

            if (turno.getColor()) {
                do {
                  /*Aqui tamos*/  texto(turno.getColor(),"Elige Ficha 0-6");
                  //  System.out.println("Elige Ficha 0-6");
                    //  fich = sc.nextInt();
                } while (!util.evaluarDestino(jB, tablero.getRecorridoBlanco(), tablero.getRecorridoNegro(), tirada,
                        fich));

                roseta = util.moverFicha(jB, jN, fich, tirada, tablero.getRecorridoBlanco(),
                        tablero.getRecorridoNegro());

            } else {

                do {
                    System.out.println("Elige Ficha 0-6");
                    //  fich = sc.nextInt();
                } while (!util.evaluarDestino(jN, tablero.getRecorridoNegro(), tablero.getRecorridoBlanco(), tirada,
                        fich));

                roseta = util.moverFicha(jN, jB, fich, tirada, tablero.getRecorridoNegro(),
                        tablero.getRecorridoBlanco());
            }
            System.out.println("******* B L A N C O *******");
            for (int i = 0; i < jB.getFichas().size(); i++) {
                System.out.println(jB.getFichas().get(i));
            }

            System.out.println("******* N E G R O *******");
            for (int i = 0; i < jN.getFichas().size(); i++) {
                System.out.println(jN.getFichas().get(i));
            }
        }

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
            case (R.id.buttonDadosA):
                if (!turno.getColor()) {
                    tirada = this.util.tirarDados();
                    dados = true;
                }
                break;

            case (R.id.buttonDadosB):
                if (turno.getColor()) {
                    tirada = this.util.tirarDados();
                    dados = true;
                }
                break;

        }
    }
}