package com.olmo.JuegoDeUr.Bean;

import android.media.Image;

public class Ficha {

    private Boolean promocionada;
    private Integer posicion;
    /**
     * True -> en el tablero
     * False -> en casa AKA salvadas(?
     */
    private Boolean enJuego;
    /**
     * True -> Blanco
     * False -> Negro
     */
    private Boolean color;
    private Image sprite;

    public Ficha() {

    }

    public Ficha(Boolean promocionada, Integer posicion, Boolean enJuego, Boolean color) {
        this.promocionada = promocionada;
        this.posicion = posicion;
        this.enJuego = enJuego;
        this.color = color;
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

    @Override
    public String toString() {
        return "Ficha{" +
                "promocionada=" + promocionada +
                ", casilla=" + posicion +
                ", enJuego=" + enJuego +
                '}';
    }
}
