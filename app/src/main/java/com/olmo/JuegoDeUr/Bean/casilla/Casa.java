package com.olmo.JuegoDeUr.Bean.casilla;

import com.olmo.JuegoDeUr.Bean.Ficha;

import java.util.List;

public class Casa extends Casilla {

    public Casa() {
        super();
        setTipo(2);
    }

    public Casa(Integer posicion, List<Ficha> ocupantes,int[] coordenadas) {
        super(posicion, ocupantes,coordenadas);
        setTipo(2);
    }
}
