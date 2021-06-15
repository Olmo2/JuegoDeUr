package com.olmo.JuegoDeUr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.olmo.JuegoDeUr.Bean.Partida;
import com.olmo.JuegoDeUr.Repository.MiBaseDeDatos;
import com.olmo.JuegoDeUr.Service.ListaAdaptador;

import java.util.ArrayList;

public class PartidasActivity extends AppCompatActivity {


    private EditText jugador1, jugador2,turnos;
    private Button anterior, siguiente;
    public ArrayList<Partida> partidas;
    public Partida partida;
    public MiBaseDeDatos bd;
    private int posicion;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidas);
        inicializar();
    }

    private void inicializar() {
        bd = new MiBaseDeDatos(this);

        partidas = bd.obtenerPartidas();
        posicion = 0;
        list=findViewById(R.id.listView);
        list.setAdapter(new ListaAdaptador(this, R.layout.entrada, partidas){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView tvJ1 = (TextView) view.findViewById(R.id.tvJ1);
                    TextView tvJ2 = (TextView) view.findViewById(R.id.tvJ2);
                    if (tvJ1 != null && tvJ2 != null )
                        tvJ1.setText(((Partida) entrada).getJugador1());
                        tvJ2.setText(((Partida) entrada).getJugador2());


                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Partida elegido = (Partida) pariente.getItemAtPosition(posicion);

                CharSequence texto ="Ganador : "+ elegido.getJugador1() +"\nPerdedor : "+ elegido.getJugador2()+"\nTotal de turnos : "+ elegido.getTurnos();
              /*  Toast toast = Toast.makeText(com.olmo.listaanimales.MainActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();*/
                AlertDialog.Builder alerta = new AlertDialog.Builder(PartidasActivity.this).setNegativeButton("Borrar",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        bd.borrarPartida(elegido.getId());

                        inicializar();

                    }
                });
                alerta.setTitle("Partida número :" + elegido.getId());
                alerta.setMessage(texto);
                alerta.create();
                alerta.show();

               /* Intent intent = new Intent(ListaNotas.this, NotaInfo.class);
                intent.putExtra("nota", (Serializable) elegido);
                startActivity(intent);*/
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}