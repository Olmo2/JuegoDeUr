package com.olmo.JuegoDeUr.Bean.casilla;

import com.olmo.JuegoDeUr.Bean.Ficha;

import java.util.List;

public class Casilla {

    private Integer posicion;
    private List<Ficha> ocupantes;
    /*Tipo 1-> Roseta
     * Tipo 2-> Casa
     * Tipo 3-> Templo
     * Tipo 4-> Mercado
     * Tipo 5-> Tesoro
     * Tipo 6-> Salida*/
    private Integer tipo;

    public Casilla(Integer posicion, List<Ficha> ocupantes) {
        this.posicion = posicion;
        this.ocupantes = ocupantes;

    }



    public Casilla() {

    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public List<Ficha> getOcupantes() {
        return ocupantes;
    }

    public void setOcupantes(List<Ficha> ocupantes) {
        this.ocupantes = ocupantes;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Casilla{" +
                "posicion=" + posicion +
                ", ocupantes=" + ocupantes +
                '}';
    }
}
