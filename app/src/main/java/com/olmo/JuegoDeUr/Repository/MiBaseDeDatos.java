package com.olmo.JuegoDeUr.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.olmo.JuegoDeUr.Bean.Partida;

import java.util.ArrayList;

public class MiBaseDeDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "Agenda.sqlite3";
    private static final int VERSION_BD = 2;
    private static final String CREAR_TABLA1 =
            "CREATE TABLE "+ ContratoJuego.Partidas.TABLA1 + "(" +
                    ContratoJuego.Partidas.COLUMNA1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ContratoJuego.Partidas.COLUMNA2 + " TEXT," +
                    ContratoJuego.Partidas.COLUMNA4 + " TEXT," +
                    ContratoJuego.Partidas.COLUMNA3 +" INTEGER)";
    private SQLiteDatabase bd;

    public MiBaseDeDatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
        bd = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREAR_TABLA1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS " + ContratoJuego.Partidas.TABLA1);
        onCreate(bd);
    }

    public void insertarPartida(String nombre,String contenido,Integer turnos){
        ContentValues cv = new ContentValues();
        cv.put(ContratoJuego.Partidas.COLUMNA2, nombre);
        cv.put(ContratoJuego.Partidas.COLUMNA3, contenido);
        cv.put(ContratoJuego.Partidas.COLUMNA4, turnos);
        bd.insert(ContratoJuego.Partidas.TABLA1, null, cv);
    }

    public void actualizarPartida(int id, String nombre, String contenido,Integer turnos){
        ContentValues cv = new ContentValues();
        cv.put(ContratoJuego.Partidas.COLUMNA2, nombre);
        cv.put(ContratoJuego.Partidas.COLUMNA3, contenido);
        cv.put(ContratoJuego.Partidas.COLUMNA4, turnos);
        bd.update(ContratoJuego.Partidas.TABLA1, cv, ContratoJuego.Partidas.COLUMNA1 + " = " + id,null);
    }

    public void borrarPartida(int id) {
        bd.delete(ContratoJuego.Partidas.TABLA1, ContratoJuego.Partidas.COLUMNA1 + " = " + id, null);
    }

    public void cerrar() {
        bd.close();
    }

    public ArrayList<Partida> obtenerPartidas(){
        ArrayList<Partida> partidas=new ArrayList<Partida>();
        String sentenciaSql = "SELECT " + ContratoJuego.Partidas.COLUMNA1 + ", " +
                ContratoJuego.Partidas.COLUMNA2 + ", " + ContratoJuego.Partidas.COLUMNA3 + ", " + ContratoJuego.Partidas.COLUMNA4 +
                " FROM " + ContratoJuego.Partidas.TABLA1;
        Cursor c = bd.rawQuery(sentenciaSql, null);

        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                int id=c.getInt(c.getColumnIndex(ContratoJuego.Partidas.COLUMNA1));
                String jugador1 = c.getString(c.getColumnIndex(ContratoJuego.Partidas.COLUMNA2));
                String jugador2 = c.getString(c.getColumnIndex(ContratoJuego.Partidas.COLUMNA3));
               int turnos = c.getInt(c.getColumnIndex(ContratoJuego.Partidas.COLUMNA4));
                Partida partida =new Partida(id,jugador1,jugador2,turnos);
                partidas.add(partida);
            } while (c.moveToNext());
        }
        c.close();
        return partidas;
    }

}

