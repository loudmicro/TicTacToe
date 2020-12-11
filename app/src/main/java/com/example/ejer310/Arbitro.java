package com.example.ejer310;

import android.widget.ImageButton;

public class Arbitro {


    /**
     * @param botonera la botonera[9] en la que comprobaremos los resultados
     * @return "nadie" si no hay ganador, el nombre del ganador, o "empate" si no quedan jugadas disponibles
     */
    public String comprobar(ImageButton[] botonera){

        if( !comprobar_linea(botonera).equals("nadie") ){
            return comprobar_linea(botonera);
        }else if(!comprobar_columna(botonera).equals("nadie")){
            return comprobar_columna(botonera);
        }else if(!comprobar_diagonales(botonera).equals("nadie")){
            return comprobar_diagonales(botonera);
        }

        return "nadie";
    }

    private String comprobar_linea(ImageButton[] botonera){
        String resul = "nadie";

        for (int i = 0; i < 9; i+=3) {
            if (botonera[i].getTag().equals( botonera[i+1].getTag())  && botonera[i].getTag().equals( botonera[i+2].getTag() )  ){
                resul = botonera[i].getTag().toString();
                break;
            }
        }

        return resul;
    }
    private String comprobar_columna(ImageButton[] botonera){
        String resul = "nadie";

        for (int i = 0; i < 3; i++) {
            if (botonera[i].getTag().equals( botonera[i+3].getTag())  && botonera[i].getTag().equals( botonera[i+6].getTag() )  ){
                resul = botonera[i].getTag().toString();
                break;
            }
        }

        return resul;
    }
    private String comprobar_diagonales(ImageButton[] botonera){
        String resul = "nadie";


        if (botonera[0].getTag().equals( botonera[4].getTag())  && botonera[0].getTag().equals( botonera[8].getTag() )  ){
            resul = botonera[0].getTag().toString();
        }else if (botonera[6].getTag().equals( botonera[4].getTag())  && botonera[6].getTag().equals( botonera[2].getTag() )  ){
            resul = botonera[6].getTag().toString();
        }

        return resul;
    }


}
