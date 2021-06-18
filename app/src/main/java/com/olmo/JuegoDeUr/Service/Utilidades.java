package com.olmo.JuegoDeUr.Service;


import android.animation.ObjectAnimator;
import android.widget.ImageView;

import com.olmo.JuegoDeUr.Bean.Ficha;
import com.olmo.JuegoDeUr.Bean.Jugador;
import com.olmo.JuegoDeUr.Bean.casilla.Casa;
import com.olmo.JuegoDeUr.Bean.casilla.Casilla;
import com.olmo.JuegoDeUr.Bean.casilla.Roseta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Utilidades {

    public boolean comida = false;
    public Ficha fichaComida;
    public Boolean ocupantes = true, ocupantes2 = true;

    /**
     * Genera ún numero entre 0 & 4
     */
    public Integer tirarDados() {
        Integer n;
        Integer resul = 0;
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            n = r.nextInt(4);
            switch (n) {
                case 0:
                case 1:
                    resul += 0;
                    break;
                case 2:
                case 3:
                    resul++;
                    break;
            }
        }
        return resul;
    }

    /**
     * @param color el color de las fichas
     * @return lista de {@link Ficha} generadas
     */
    public List<Ficha> generarFichas(Boolean color) {
        Ficha f;
        List<Ficha> listaFichas = new ArrayList<>();

        if (color) {
            for (int i = 0; i < 7; i++) {
                f = new Ficha(false, 0, true, color, new int[]{4, 2});
                listaFichas.add(f);
            }
        } else {
            for (int i = 0; i < 7; i++) {
                f = new Ficha(false, 0, true, color, new int[]{4, 0});
                listaFichas.add(f);
            }
        }
        return listaFichas;
    }

    /**
     * @param recorrido un número de identificacion de que recorrido se ha de generar
     * @return lista de {@link Casilla} el recorrido generado
     */
    public List<Casilla> generarRecorrido(int recorrido) {
        Casilla c;
        List<Ficha> lista;
        List<Casilla> listaCasillas = new ArrayList<>();
        switch (recorrido) {
            /**
             * Recorrido sencillo 1-3 Casa
             *  4,8,14 Roseta
             *  4-6, 8-12 Casa
             *  case 1 blanco
             *  case 2 negro
             */
            case 1:
                c = new Casilla(0, lista = new ArrayList<>(), new int[]{4, 2});
                listaCasillas.add(c);

                c = new Casa(1, lista = new ArrayList<>(), new int[]{3, 2});
                listaCasillas.add(c);

                c = new Casa(2, lista = new ArrayList<>(), new int[]{2, 2});
                listaCasillas.add(c);

                c = new Casa(3, lista = new ArrayList<>(), new int[]{1, 2});
                listaCasillas.add(c);

                /* ROSETA */
                c = new Roseta(4, lista = new ArrayList<>(), new int[]{0, 2});
                listaCasillas.add(c);

                c = new Casa(5, lista = new ArrayList<>(), new int[]{0, 1});
                listaCasillas.add(c);

                c = new Casa(6, lista = new ArrayList<>(), new int[]{1, 1});
                listaCasillas.add(c);

                c = new Casa(7, lista = new ArrayList<>(), new int[]{2, 1});
                listaCasillas.add(c);

                /* ROSETA */
                c = new Roseta(8, lista = new ArrayList<>(), new int[]{3, 1});
                listaCasillas.add(c);

                c = new Casa(9, lista = new ArrayList<>(), new int[]{4, 1});
                listaCasillas.add(c);

                c = new Casa(10, lista = new ArrayList<>(), new int[]{5, 1});
                listaCasillas.add(c);

                c = new Casa(11, lista = new ArrayList<>(), new int[]{6, 1});
                listaCasillas.add(c);

                c = new Casa(12, lista = new ArrayList<>(), new int[]{7, 1});
                listaCasillas.add(c);

                c = new Casa(13, lista = new ArrayList<>(), new int[]{7, 2});
                listaCasillas.add(c);

                /* ROSETA */
                c = new Roseta(14, lista = new ArrayList<>(), new int[]{6, 2});
                listaCasillas.add(c);

                break;

            case 2:
                c = new Casilla(0, lista = new ArrayList<>(), new int[]{4, 0});
                listaCasillas.add(c);

                c = new Casa(1, lista = new ArrayList<>(), new int[]{3, 0});
                listaCasillas.add(c);

                c = new Casa(2, lista = new ArrayList<>(), new int[]{2, 0});
                listaCasillas.add(c);

                c = new Casa(3, lista = new ArrayList<>(), new int[]{1, 0});
                listaCasillas.add(c);

                /* ROSETA */
                c = new Roseta(4, lista = new ArrayList<>(), new int[]{0, 0});
                listaCasillas.add(c);

                c = new Casa(5, lista = new ArrayList<>(), new int[]{0, 1});
                listaCasillas.add(c);

                c = new Casa(6, lista = new ArrayList<>(), new int[]{1, 1});
                listaCasillas.add(c);

                c = new Casa(7, lista = new ArrayList<>(), new int[]{2, 1});
                listaCasillas.add(c);

                /* ROSETA */
                c = new Roseta(8, lista = new ArrayList<>(), new int[]{3, 1});
                listaCasillas.add(c);

                c = new Casa(9, lista = new ArrayList<>(), new int[]{4, 1});
                listaCasillas.add(c);

                c = new Casa(10, lista = new ArrayList<>(), new int[]{5, 1});
                listaCasillas.add(c);

                c = new Casa(11, lista = new ArrayList<>(), new int[]{6, 1});
                listaCasillas.add(c);

                c = new Casa(12, lista = new ArrayList<>(), new int[]{7, 1});
                listaCasillas.add(c);

                c = new Casa(13, lista = new ArrayList<>(), new int[]{7, 0});
                listaCasillas.add(c);

                /* ROSETA */
                c = new Roseta(14, lista = new ArrayList<>(), new int[]{6, 0});
                listaCasillas.add(c);

                break;
        }

        return listaCasillas;
    }

    /**
     * Evalua si se puede ono mover hasta el destino
     *
     * @param j1         {@link Jugador} Jugador que juega el turno
     * @param recorrido1 lista de {@link Casilla} del jugador que juega el turno
     * @param recorrido2 lista de {@link Casilla} del jugador que no juega el turno
     * @param tirada     número resultado de la tirada
     * @param ficha      índice de la ficha
     * @return un booleano con el resultado de la evaluacion
     * true-> si
     * false->no
     */
    public Boolean evaluarDestino(Jugador j1, List<Casilla> recorrido1, List<Casilla> recorrido2, Integer tirada, Integer ficha) {

        Ficha f = j1.getFichas().get(ficha - 1);
        Integer posicion = f.getPosicion();
        Integer destino = posicion + tirada;
        ocupantes = true;
        ocupantes2 = true;

        if (destino < 15) {
            ocupantes = recorrido1.get(destino).getOcupantes().isEmpty();
            ocupantes2 = recorrido2.get(destino).getOcupantes().isEmpty();
        } else if (destino == 15) {
            return true;
        } else {
            return false;
        }

        if (!ocupantes) {
            /**Ocupada, devuelve false*/
            return ocupantes;
        } else if ((!ocupantes2 && recorrido2.get(destino).getTipo() == 1) && destino > 5 && destino < 13) {
            return ocupantes2;
        }
        /**Libre devuelve true*/
        return ocupantes;

    }

    /**
     * Acción de mover una ficha
     *
     * @param j1         jugador que juega el turno
     * @param j2         El otro jugador
     * @param ficha      ficha elegida para moverse
     * @param tirada     numero que ha salido en los dados
     * @param recorrido1 recorrido que se esta jugando, el del j1
     * @param recorrido2 recorrido que se esta jugando, el del j2
     */
    public Boolean moverFicha(Jugador j1, Jugador j2, Integer ficha, Integer tirada,
                              List<Casilla> recorrido1, List<Casilla> recorrido2) {
        Ficha f = j1.getFichas().get(ficha);
        Integer posicion = f.getPosicion();
        Boolean ocupantes = true, ocupantes2 = true, roseta = false;
        Integer destino = posicion + tirada;

        if (destino == 15) {
            f.setEnJuego(false);
            recorrido1.get(f.getPosicion()).getOcupantes().remove(recorrido1.get(f.getPosicion()).getOcupantes().size() - 1);
            f.setPosicion(destino);
        } else if (destino < 15) {
            /**ocupantes del destino del mismo color*/
            ocupantes = recorrido1.get(destino).getOcupantes().isEmpty();
            ocupantes2 = recorrido2.get(destino).getOcupantes().isEmpty();
           if (destino > 4 && destino < 12 && !ocupantes2) {
                comerFicha(destino, j2.getFichas(), recorrido2);
                comida = true;

                if (posicion == 0) {

                    f.setPosicion(tirada);
                    recorrido1.get(f.getPosicion()).getOcupantes().add(f);

                } else if (posicion > 0) {

                    recorrido1.get(f.getPosicion()).getOcupantes().remove(recorrido1.get(f.getPosicion()).getOcupantes().size() - 1);
                    f.setPosicion(destino);
                    recorrido1.get(f.getPosicion()).getOcupantes().add(f);
                }


            } else if (ocupantes) {
                /**Movimiento normal*/
                if (recorrido1.get(destino).getTipo() == 1) {
                    roseta = true;

                } else {
                    roseta = false;
                }
                if (posicion == 0) {
                    f.setPosicion(tirada);
                    recorrido1.get(f.getPosicion()).getOcupantes().add(f);
                } else if (posicion > 0) {
                    recorrido1.get(f.getPosicion()).getOcupantes().remove(recorrido1.get(f.getPosicion()).getOcupantes().size() - 1);
                    f.setPosicion(destino);
                    recorrido1.get(f.getPosicion()).getOcupantes().add(f);
                }

            }

        }
        return roseta;

    }

    /**
     * Acción de comer una ficha
     *
     * @param destino       número de la {@link Casilla} a la que se va a mover la ficha.
     * @param listaFichas   lista de {@link Ficha} a la que pertenece la {@link Ficha}.
     * @param listaCasillas lista de {@link Casilla} por la que se mueve la {@link Ficha}.
     */
    public void comerFicha(Integer destino, List<Ficha> listaFichas, List<Casilla> listaCasillas) {
        for (int i = 0; i < listaFichas.size(); i++) {
            if (listaFichas.get(i).getPosicion() == destino) {
                listaFichas.get(i).setPosicion(0);
                fichaComida = listaFichas.get(i);
                listaCasillas.get(destino).getOcupantes().remove(listaCasillas.get(destino).getOcupantes().size() - 1);
            }
        }
    }

    /**
     * @param ficha              la {@link ImageView} que se va a posicionar.
     * @param dimensionesTablero dimensiones del tablero.
     * @param f                  la {@link Ficha} correspondiente al {@link ImageView}.
     * @param x                  coordenada x en el tablero.
     * @param y                  coordenada y en el tablero.
     */
    public void posicionarFicha(ImageView ficha, int[] dimensionesTablero, Ficha f, int x, int y) {
        System.out.println(dimensionesTablero[0]);
        System.out.println(dimensionesTablero[1]);

        ObjectAnimator object1 = ObjectAnimator.ofFloat(ficha, "x", (dimensionesTablero[0] / 8) * (x + 0.25f));
        object1.setDuration(1000);
        object1.start();
        object1 = ObjectAnimator.ofFloat(ficha, "y", (dimensionesTablero[1] / 3) * (y + 0.25f));
        object1.setDuration(1000);
        object1.start();

        f.setCoordenadas(new int[]{x, y});
    }

    /**
     * Asigna las coordenadas de inicia.
     *
     * @param j        {@link Jugador} actual.
     * @param selector {@link Map} de {@link Ficha}, asociadas con un {@link ImageView}.
     */
    public void setCoordenadasIniciales(Jugador j, Map<Ficha, ImageView> selector) {
        for (int i = 0; i < j.getFichas().size(); i++) {
            j.getFichas().get(i).setCoordenadasIniciales(new float[]{selector.get(j.getFichas().get(i)).getX(), selector.get(j.getFichas().get(i)).getY()});
        }
    }

    /**
     * Posiciona inicialmente las fichas en el tablero.
     *
     * @param j                  {@link Jugador} actual.
     * @param selector           {@link Map} de {@link Ficha}, asociadas con un {@link ImageView}.
     * @param dimensionesTablero dimensiones del tablero.
     * @param casa               coordenadas de la casilla inicial.
     */
    public void colocarFichasInicio(Jugador j, Map<Ficha, ImageView> selector, int[] dimensionesTablero, int[] casa) {
        for (int i = 0; i < j.getFichas().size(); i++) {
            selector.get(j.getFichas().get(i)).setX((dimensionesTablero[0] / 8) * (casa[0] + 0.25f));
            selector.get(j.getFichas().get(i)).setY((dimensionesTablero[1] / 3) * (casa[1] + 0.25f));
        }
    }

    /**
     * Define el color de las fichas.
     *
     * @param j        {@link Jugador} actual.
     * @param selector {@link Map} de {@link Ficha}, asociadas con un {@link ImageView}.
     * @param color    la clave del Drawable que se asigna.
     */
    public void setColorFichas(Map<Ficha, ImageView> selector, int color, Jugador j) {
        for (int i = 0; i < selector.size(); i++) {
            selector.get(j.getFichas().get(i)).setImageResource(color);
        }

    }

}
