package com.example.android.prueba1;

public class Modulos {

/*Representa un módulo en forma de objeto. Contiene ícono del módulo y
nombre del módulo,
 */
    private String mNombreModulo;
    private int mImageResourceId;


    public Modulos (String nombreModulo, int imageResourceId){
        mNombreModulo = nombreModulo;
        mImageResourceId = imageResourceId;
        }

    // Obtener el número del pedido

    public String getNombreModulo (){
        return mNombreModulo;
    }

    public int getImageResourceId (){
        return mImageResourceId;
    }


}

