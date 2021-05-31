package com.olmo.JuegoDeUr.Bean;

public class Turno {

    private Integer numTurno;
    private Integer numDados;
    /**
     * True-> Blanco
     * False-> Negro
     * */
    private Boolean color;

    public Turno(Integer numTurno, Boolean color) {
        this.numTurno = numTurno;
        this.color = color;
    }

    public Integer getNumTurno() {
        return numTurno;
    }

    public void setNumTurno(Integer numTurno) {
        this.numTurno = numTurno;
    }

    public Integer getNumDados() {
        return numDados;
    }

    public void setNumDados(Integer numDados) {
        this.numDados = numDados;
    }


    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "Turno{" +
                "numTurno=" + numTurno +
                ", numDados=" + numDados +
                ", color=" + color +
                '}';
    }
}

