package com.example.android.prueba1;

public class Surtido {

//    Representa una partida de una remisión para surtir

    private String mUbicacion;
    private String mDescripcionProducto;
    private String mDescripcionEnvase;
    private String mLote;
    private String mCantidad;
    private String mCantidadContada;

    public void setmCantidadContada(String mCantidadContada) {
        this.mCantidadContada = mCantidadContada;
    }

    public String getUbicacionSurtido() {
        return mUbicacion;
    }

    public String getDescripcionProductoSurtido() {
        return mDescripcionProducto;
    }

    public String getDescripcionEnvaseSurtido() {
        return mDescripcionEnvase;
    }

    public String getLoteSurtido() {
        return mLote;
    }

    public String getCantidadSurtido() {
        return mCantidad;
    }

    public String getCantidadContadaSurtido() {
        return mCantidadContada;
    }

    public Surtido(String ubicacion, String descripcion_producto, String descripcion_envase, String lote, String cantidad, String cantidad_contada) {
        this.mUbicacion = ubicacion;
        this.mDescripcionProducto = descripcion_producto;
        this.mDescripcionEnvase = descripcion_envase;
        this.mLote = lote;
        this.mCantidad = cantidad;
        this.mCantidadContada = cantidad_contada;
    }
}
