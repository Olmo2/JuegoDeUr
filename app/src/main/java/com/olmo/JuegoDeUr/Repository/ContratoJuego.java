package com.olmo.JuegoDeUr.Repository;

import android.provider.BaseColumns;

public final class ContratoJuego {

    private ContratoJuego() {}

    public static class Partidas implements BaseColumns {
        public static final String TABLA1= "partidas";
        public static final String COLUMNA1="_id";
        public static final String COLUMNA2= "juagador1";
        public static final String COLUMNA3= "jugador2";
        public static final String COLUMNA4= "turnos";
    }
}
