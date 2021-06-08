package com.olmo.JuegoDeUr.Bean.casilla;

import com.olmo.JuegoDeUr.Bean.Ficha;

import java.util.List;

public class Roseta extends Casilla {

    public Roseta() {
        super();
        setTipo(1);
    }

    public Roseta(Integer posicion, List<Ficha> ocupantes, int[] coordenadas) {
        super(posicion, ocupantes,coordenadas);
        setTipo(1);
    }

    public void repetirTurno(){

    }



}
