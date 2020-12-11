package com.example.ejer310;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnBoton1;
    private int[] idBotones = {R.id.btnBoton1,R.id.btnBoton2,R.id.btnBoton3,R.id.btnBoton4,R.id.btnBoton5,R.id.btnBoton6,R.id.btnBoton7,R.id.btnBoton8,R.id.btnBoton9};;
    private ImageButton[] botonera;
    private ToggleButton start_stop;
    private AndroidJugada c3p0;
    private RadioButton rb_nivel1;
    private Arbitro arbitro;
    private  String resul;
    private TextView prueba;
    private int movimientosDisponibles;
    private AlertDialog dialog;
    AlertDialog.Builder builder;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movimientosDisponibles = 9;


        botonera = new ImageButton[idBotones.length];
        start_stop = (ToggleButton) findViewById(R.id.btnStart);
        rb_nivel1 = (RadioButton) findViewById(R.id.rbNivel1);
        prueba = (TextView) findViewById(R.id.prueba);
        arbitro = new Arbitro();
        radioGroup = (RadioGroup) findViewById(R.id.rbGroup);




        //listener del toggle
        start_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (!start_stop.isChecked()){
                    //partida parada
                    emptyTag(botonera);
                    enable(botonera,false);
                    radioGroup.setVisibility(View.VISIBLE);


                }else{
                    enable(botonera,true);
                    radioGroup.setVisibility(View.GONE);

                }

            }
        });

        //Enlazo componentes con su vista
        for (int i = 0; i < idBotones.length ; i++) {
            botonera[i] = (ImageButton) findViewById(idBotones[i]);
            botonera[i].setOnClickListener(this);
            //botonera[i].setTag(String.valueOf(i));
        }

        //estado inicial de la botonera
        emptyTag(botonera);
        enable(botonera,false);

        builder = new AlertDialog.Builder(this);







    }

    @Override
    public void onClick(View view) {

        if (movimientosDisponibles < 1) {
            prueba.setText("NO HAY MAS MOVIMIENTOS");
            return;
        }

        int btn_id = view.getId();

        for (int i = 0; i < botonera.length ; i++) {

            if (botonera[i].getId() == btn_id){

                botonera[i].setImageResource(R.drawable.user_icon);
                botonera[i].setEnabled(false);
                botonera[i].setTag("user");
                break;
            }

        }
        movimientosDisponibles--;

        //despues de cada jugada llamamos al arbitro

        resul = arbitro.comprobar(botonera);
        if (resul.equals("user")){
            builder.setMessage("Has Ganado Campeón").setTitle("Ganador!!!");
            dialog = builder.create();
            dialog.show();
            enable(botonera,false);
            emptyTag(botonera);
            return;
        }

        prueba.setText(resul);
        /*
        if (resul.equals("nadie")){

            Toast.makeText(getApplicationContext(), resul, Toast.LENGTH_SHORT).show();

        }
        */


        //despues de jugar el humano juega automaticamente el droide

        if (rb_nivel1.isChecked())
            c3p0 = new JugadaAleatoria();
        else
            c3p0 = new JugadaDirecta();


        while (!c3p0.jugar(botonera)  && movimientosDisponibles >= 1){}
        resul = arbitro.comprobar(botonera);
        //prueba.setText(resul);
        movimientosDisponibles--;

        if (resul.equals("android")){
            builder.setMessage("Ha ganado Android").setTitle("Oooops!!!");
            dialog = builder.create();
            dialog.show();
            enable(botonera,false);
            emptyTag(botonera);
            return;
        }

        if (movimientosDisponibles < 1) {
            builder.setMessage("Te has quedado sin movimientos").setTitle("Jolines");
            dialog = builder.create();
            dialog.show();
            enable(botonera,false);
            emptyTag(botonera);
            return;
        }



    }

    private void emptyTag(ImageButton[] botonera){

        for (int i = 0; i < botonera.length; i++) {
            botonera[i].setTag("");
            botonera[i].setImageResource(0);
            botonera[i].setEnabled(true);
        }
        movimientosDisponibles = 9;

    }

    private void enable(ImageButton[] botonera, boolean estado){//activa o desactiva la botonera

        for (int i = 0; i < botonera.length; i++) {
            botonera[i].setEnabled(estado);
        }

    }
}