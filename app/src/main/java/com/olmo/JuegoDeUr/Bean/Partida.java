package com.olmo.JuegoDeUr.Bean;

public class Partida {

    private int id;
    private  String jugador1;
    private String jugador2;
    private int turnos;

    public Partida(int id,String jugador1,String jugador2,int turnos){
        this.id = id;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.turnos = turnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", jugador1='" + jugador1 + '\'' +
                ", jugador2='" + jugador2 + '\'' +
                ", turnos=" + turnos +
                '}';
    }
}
