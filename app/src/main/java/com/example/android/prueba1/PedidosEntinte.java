package com.example.android.prueba1;

/*Representa un pedido en forma de objeto para ser entregado por almacén PT. Contiene
número de pedido, nombre del vendedor, correo del vendedor, cliente, fecha de entrega prometida
 */

import java.util.Date;

public class PedidosEntinte {

    private String mNumerodePedidoEntinte;
    private String mVendedorEntinte;
    private String mCorreoEntinte;
    private String mEntonador;
    private String mFechaPrometidaEntinte;

    public PedidosEntinte (String numeroPedidoEntinte, String vendedorEntinte, String correoEntinte, String entonador, String fechaPrometidaEntinte){
        mNumerodePedidoEntinte = numeroPedidoEntinte;
        mVendedorEntinte = vendedorEntinte;
        mCorreoEntinte = correoEntinte;
        mEntonador = entonador;
        mFechaPrometidaEntinte = fechaPrometidaEntinte;
    }

    // Obtener el número del pedido

    public String getNumerodePedidoEntinte (){
        return mNumerodePedidoEntinte;
    }

    public String getVendedorEntinte (){
        return mVendedorEntinte;
    }

    public String getCorreoEntinte (){
        return mCorreoEntinte;
    }

    public String getEntonador (){
        return mEntonador;
    }

    public void  setEntonador(String n){this.mEntonador=n;}


    public String getFechaPrometidaEntinte (){
        return mFechaPrometidaEntinte;
    }





}