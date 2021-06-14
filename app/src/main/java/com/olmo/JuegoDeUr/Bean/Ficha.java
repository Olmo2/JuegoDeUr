package com.olmo.JuegoDeUr.Bean;

import android.media.Image;

public class Ficha {

    private Boolean promocionada;
    private Integer posicion;

    /**
     * True -> en el tablero
     * False -> en casa
     */
    private Boolean enJuego;
    /**
     * True -> Blanco
     * False -> Negro
     */
    private Boolean color;

    private Image sprite;
    private int[] coordenadas = new int[2];
    private float[] coordenadasIniciales = new float[2];

    public Ficha() {

    }

    public Ficha(Boolean promocionada, Integer posicion, Boolean enJuego, Boolean color,int[] coordenadas) {
        this.promocionada = promocionada;
        this.posicion = posicion;
        this.enJuego = enJuego;
        this.color = color;
        this.coordenadas=coordenadas;
    }

    public Boolean getPromocionada() {
        return promocionada;
    }

    public void setPromocionada(Boolean promocionada) {
        this.promocionada = promocionada;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Boolean getEnJuego() {
        return enJuego;
    }

    public void setEnJuego(Boolean enJuego) {
        this.enJuego = enJuego;
    }

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public float[] getCoordenadasIniciales() { return coordenadasIniciales; }

    public void setCoordenadasIniciales(float[] coordenadasIniciales) {this.coordenadasIniciales = coordenadasIniciales; }

    @Override
    public String toString() {
        return "Ficha{" +
                "promocionada=" + promocionada +
                ", casilla=" + posicion +
                ", enJuego=" + enJuego +
                '}';
    }
}
