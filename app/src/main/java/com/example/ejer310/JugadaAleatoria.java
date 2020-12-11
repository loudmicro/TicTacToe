package com.example.ejer310;


import android.widget.ImageButton;

import java.util.Random;

public class JugadaAleatoria implements AndroidJugada{

    private Random rand;

    public  JugadaAleatoria(){
        rand = new Random();
    }

    @Override
    public boolean jugar(ImageButton[] botonera) {


        int num = rand.nextInt(9);

        if (botonera[num].isEnabled()){
            botonera[num].setImageResource(R.drawable.android_icon);
            botonera[num].setTag("android");
            botonera[num].setEnabled(false);
            return true;
        }

        return false;



    }


}
