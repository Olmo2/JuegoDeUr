package com.olmo.JuegoDeUr.Bean.casilla;

import com.olmo.JuegoDeUr.Bean.Ficha;

import java.util.List;

public class Tesoro extends Casilla {


    public Tesoro(Integer posicion, List<Ficha> ocupantes,int[] coordenadas) {
        super(posicion, ocupantes,coordenadas);
        setTipo(5);
    }



    public void promocion(){

    }


}
