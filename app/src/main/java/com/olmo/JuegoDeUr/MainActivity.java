package com.olmo.JuegoDeUr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.olmo.JuegoDeUr.Bean.*;
import com.olmo.JuegoDeUr.Service.Utilidades;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private Tablero tablero;
    private Turno turno;
    private Utilidades util;
    private Jugador jB;
    private Jugador jN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* DECLARACIONES */
        tablero = new Tablero();
        util = new Utilidades();
        jB = new Jugador();
        jN = new Jugador();
        turno = new Turno(0, false);
        Integer enCasaB = 0, enCasaN = 0;
        /* INICIALIZACIONES */
        tablero.setRecorridoBlanco(util.generarRecorrido(1));
        tablero.setRecorridoNegro(util.generarRecorrido(1));

        System.out.println(tablero.getRecorridoBlanco().size());

        jB.setColor(true);
        jB.setFichas(util.generarFichas(jB.getColor()));

        jN.setColor(false);
        jN.setFichas(util.generarFichas(jN.getColor()));

        System.out.println("Empieza el juego");

        while (enCasaB != 7 && enCasaN != 7) {
            System.out.println("Blancas en casa " + enCasaB + "Negras en casa " + enCasaN);
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
        System.out.println("Fin del Juego");

    }

    public  Boolean juego(Turno turno) {

        Boolean roseta = false;
        Integer tirada, fich = null, ocupantes, posicion;

        if (turno.getColor()) {
            System.out.println("Le toca al Blanco ");
        } else {
            System.out.println("Le toca al Negro ");
        }

        System.out.println("Tira los dados");
     //   resp= sc.next();
        tirada= this.util.tirarDados();

        System.out.println("Te ha salido un " + tirada);
        if (tirada == 0) {
            System.err.println("Pierdes turno pringao");
        } else {

            if (turno.getColor()) {
                do {
                    System.out.println("Elige Ficha 0-6");
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
}