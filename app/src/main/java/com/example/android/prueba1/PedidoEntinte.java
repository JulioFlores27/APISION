package com.example.android.prueba1;

public class PedidoEntinte {

    private int mID;
    private int mNoPedido;
    private String mProducto;
    private String mObservaciones;
    private String mTipoEnvase;
    private String mEtapa1;
    private String mFechaAprobacion;
    private String mPersonaAsignada;

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    @Override
    public String toString() {
        return "PedidoEntinte{" +
                "mNoPedido=" + mNoPedido +
                '}';
    }

    public int getmNoPedido() {
        return mNoPedido;
    }

    public void setmNoPedido(int mNoPedido) {
        this.mNoPedido = mNoPedido;
    }

    public String getmProducto() {
        return mProducto;
    }

    public void setmProducto(String mProducto) {
        this.mProducto = mProducto;
    }

    public String getmObservaciones() {
        return mObservaciones;
    }

    public void setmObservaciones(String mObservaciones) {
        this.mObservaciones = mObservaciones;
    }

    public String getmTipoEnvase() {
        return mTipoEnvase;
    }

    public void setmTipoEnvase(String mTipoEnvase) {
        this.mTipoEnvase = mTipoEnvase;
    }

    public int getmCantidad() {
        return mCantidad;
    }

    public void setmCantidad(int mCantidad) {
        this.mCantidad = mCantidad;
    }

    public String getmFechaCaptura() {
        return mFechaCaptura;
    }

    public void setmFechaCaptura(String mFechaCaptura) {
        this.mFechaCaptura = mFechaCaptura;
    }

    public String getmFechaAsignacion() {
        return mFechaAsignacion;
    }

    public void setmFechaAsignacion(String mFechaAsignacion) {
        this.mFechaAsignacion = mFechaAsignacion;
    }

    public String getmEtapa1() {
        return mEtapa1;
    }

    public void setmEtapa1(String mEtapa1) {
        this.mEtapa1 = mEtapa1;
    }

    public String getmFechaAprobacion() {
        return mFechaAprobacion;
    }

    public void setmFechaAprobacion(String mFechaAprobacion) {
        this.mFechaAprobacion = mFechaAprobacion;
    }

    public String getmPersonaAsignada() {
        return mPersonaAsignada;
    }

    public void setmPersonaAsignada(String mPersonaAsignada) {
        this.mPersonaAsignada = mPersonaAsignada;
    }

    private int mCantidad;
    private String mFechaCaptura;
    private String mFechaAsignacion;

    public PedidoEntinte(int mID, int mNoPedido, String mProducto, String mObservaciones, String mTipoEnvase, int mCantidad, String mFechaCaptura, String mFechaAsignacion, String mEtapa1, String mFechaAprobacion, String mPersonaAsignada) {
        this.mID = mID;
        this.mNoPedido = mNoPedido;
        this.mProducto = mProducto;
        this.mObservaciones = mObservaciones;
        this.mTipoEnvase = mTipoEnvase;
        this.mCantidad = mCantidad;
        this.mFechaCaptura = mFechaCaptura;
        this.mFechaAsignacion = mFechaAsignacion;
        this.mEtapa1 = mEtapa1;
        this.mFechaAprobacion = mFechaAprobacion;
        this.mPersonaAsignada = mPersonaAsignada;
    }



}
