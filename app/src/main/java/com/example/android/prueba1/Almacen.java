package com.example.android.prueba1;

public class Almacen {
    private int id, rack, fila, columna, lotemp;
    private Double cantidad;

    private String envase, tienda;
    private String materiaprima, persona, observaciones, fechahora;

    public Almacen(){ }

    public Almacen(int id, int rack, int fila, int columna, int lotemp, double cantidad, String materiaprima, String envase, String tienda, String persona, String observaciones, String fechahora) {
        this.id = id;
        this.rack = rack;
        this.fila = fila;
        this.columna = columna;
        this.lotemp = lotemp;
        this.cantidad = cantidad;
        this.materiaprima = materiaprima;
        this.envase = envase;
        this.tienda = tienda;
        this.persona = persona;
        this.observaciones = observaciones;
        this.fechahora = fechahora;
    }


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

    public int getLotemp() {
        return lotemp;
    }

    public void setLotemp(int lotemp) {
        this.lotemp = lotemp;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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

    public String getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(String materiaprima) {
        this.materiaprima = materiaprima;
    }



    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getObservaciones() {
        return observaciones;
    }



    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    @Override
    public String toString() {
        return "Almacen{" +
                "id=" + id +
                ", rack=" + rack +
                ", fila=" + fila +
                ", columna=" + columna +
                ", lotemp=" + lotemp +
                ", cantidad=" + cantidad +
                ", envase='" + envase + '\'' +
                ", tienda='" + tienda + '\'' +
                ", materiaprima='" + materiaprima + '\'' +
                ", persona='" + persona + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", fechahora='" + fechahora + '\'' +
                '}';
    }


    //    @Override
//    public String toString() {
//        return id + " " + rack + fila + columna + " " + materiaprima+  " " + lotemp + " "+ envase + " " + cantidad + " " + persona + observaciones + fechahora;
//    }
}
