package com.olmo.JuegoDeUr.Bean;


import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private Turno turno;
    private List<Ficha> fichas = new ArrayList<>();
    /*True -> Blanco
     * False -> Negro*/
    private Boolean color;

    public Jugador(Turno turno, List<Ficha> fichas, Boolean color) {
        this.turno = turno;
        this.fichas = fichas;
        this.color = color;
    }

    public Jugador() {

    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "turno=" + turno +
                ", fichas=" + fichas +
                ", color=" + color +
                '}';
    }
}
