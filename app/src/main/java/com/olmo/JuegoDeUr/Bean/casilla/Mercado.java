package com.olmo.JuegoDeUr.Bean.casilla;

import com.olmo.JuegoDeUr.Bean.Ficha;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Mercado extends Casilla {

    /*Pila LIFO*/
    private Deque<Ficha> fichas;


    public Mercado(Integer posicion, List<Ficha> ocupantes,int[] coordenadas) {
        super(posicion, ocupantes,coordenadas);
        fichas= new ArrayDeque<>(4);
        setTipo(4);
    }

    public Deque<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(Deque<Ficha> fichas) {
        this.fichas = fichas;
    }

    @Override
    public String toString() {
        return "Mercado{" +
                "fichas=" + fichas +
                '}';
    }
}
