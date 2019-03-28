package com.example.android.prueba1;

public class Existencia {

    private int id, rack, fila, columna, lote;
    private Double cantidad;
    private String producto, usuario, observaciones, fecha, hora, envase, tienda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRack() {
        return rack;
    }

    public void setRack(int rack) {
        this.rack = rack;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEnvase() {
        return envase;
    }

    public void setEnvase(String envase) {
        this.envase = envase;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public Existencia(int id, int rack, int fila, int columna, int lote, Double cantidad, String producto, String usuario, String observaciones, String fecha, String hora, String envase, String tienda) {
        this.id = id;
        this.rack = rack;
        this.fila = fila;
        this.columna = columna;
        this.lote = lote;
        this.cantidad = cantidad;
        this.producto = producto;
        this.usuario = usuario;
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.hora = hora;
        this.envase = envase;
        this.tienda = tienda;
    }

    public Existencia() {
    }

    @Override
    public String toString() {
        return "Existencia{" +
                "id=" + id +
                ", rack=" + rack +
                ", fila=" + fila +
                ", columna=" + columna +
                ", lote=" + lote +
                ", cantidad=" + cantidad +
                ", producto='" + producto + '\'' +
                ", usuario='" + usuario + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", envase='" + envase + '\'' +
                ", tienda='" + tienda + '\'' +
                '}';
    }
}


