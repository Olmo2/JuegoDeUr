package com.olmo.JuegoDeUr.Bean.casilla;

import olmo.beans.Ficha;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Templo extends Casilla {

    /*Pila LIFO*/
    private Deque<Ficha> fichas;
    /*0 -> sin color
    * 1 -> Blanco
    * 2 -> Negro*/
    private Integer color;

    public Templo(Integer posicion, List<Ficha> ocupantes) {
        super(posicion, ocupantes);
        fichas=new ArrayDeque<>(4);
        color= 0;
        setTipo(3);
    }


    public Deque<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(Deque<Ficha> fichas) {
        this.fichas = fichas;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Templo{" +
                "fichas=" + fichas +
                ", color=" + color +
                '}';
    }
}
