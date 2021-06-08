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
    private int[] coordenadas =new int[2];

    public Casilla(Integer posicion, List<Ficha> ocupantes,int[] coordenadas) {
        this.posicion = posicion;
        this.ocupantes = ocupantes;
        this.coordenadas = coordenadas;

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

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public String toString() {
        return "Casilla{" +
                "posicion=" + posicion +
                ", ocupantes=" + ocupantes +
                '}';
    }
}
