package com.example.ejer310;

import android.content.res.Resources;
import android.widget.ImageButton;

import java.util.Random;

public class JugadaDirecta implements AndroidJugada{

    private int bloquear[][] = {
            {1,3,4},            //0
            {0,3,4,5,2},        //1
            {1,4,5},            //2
            {0,1,4,7,6},        //3
            {0,1,2,3,5,6,7,8},  //4
            {2,1,4,7,8},        //5
            {3,4,7},            //6
            {6,3,4,5,8},        //7
            {4,5,7}
    };

    public JugadaDirecta() {
    }

    @Override
    public boolean jugar(ImageButton[] botonera) {

        Random rand = new Random();
        int num = rand.nextInt(9);

        if (botonera[4].isEnabled()){
            botonera[4].setImageResource(R.drawable.android_icon);
            botonera[4].setTag("android");
            botonera[4].setEnabled(false);
            return true;
        }

        return bloquear(botonera);
    }

    private boolean bloquear(ImageButton[] botonera){

        for (int i = 0; i < botonera.length ; i++) {
            if (botonera[i].getTag().equals("user")){
                for (int j = 0; j < bloquear[i].length; j++) {
                    if (botonera[bloquear[i][j]].isEnabled()){

                        botonera[bloquear[i][j]].setImageResource(R.drawable.android_icon);
                        botonera[bloquear[i][j]].setTag("android");
                        botonera[bloquear[i][j]].setEnabled(false);
                        return true;

                    }
                }
            }
        }
        return false;

    }


}
