package com.example.android.prueba1;

/*Representa un pedido en forma de objeto para ser entregado por almacén PT. Contiene
número de pedido, nombre del vendedor, correo del vendedor, cliente, fecha de entrega prometida
 */

import java.util.Date;

public class Pedidos {

    private String mNumerodePedido;
    private String mVendedor;
    private String mCorreo;
    private String mCliente;
    private String mFechaPrometida;

    public Pedidos (String numeroPedido, String vendedor, String correo, String cliente, String fechaPrometida){
        mNumerodePedido = numeroPedido;
        mVendedor = vendedor;
        mCorreo = correo;
        mCliente = cliente;
        mFechaPrometida = fechaPrometida;
    }

    // Obtener el número del pedido

    public String getNumerodePedido (){
        return mNumerodePedido;
    }

    public String getVendedor (){
        return mVendedor;
    }

    public String getCorreo (){
        return mCorreo;
    }

    public String getCliente (){
        return mCliente;
    }

    public String getFechaPrometida (){
        return mFechaPrometida;
    }



}
