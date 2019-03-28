package com.example.android.prueba1;

public class MateriaPrimaClass {
    private int ID;
    private int Rack;



    private int Fila;
    private int Columna;
    private String MateriaPrima;
    private int LoteMP;
    private double Cantidad;
    private String Persona;
    private String Observaciones;
    private String FechayHora;

    public MateriaPrimaClass() {
    }

    public MateriaPrimaClass(int ID, int rack, int fila, int columna, String materiaPrima, int loteMP, double cantidad, String persona, String observaciones, String fechayHora) {
        this.ID = ID;
        Rack = rack;
        Fila = fila;
        Columna = columna;
        MateriaPrima = materiaPrima;
        LoteMP = loteMP;
        Cantidad = cantidad;
        Persona = persona;
        Observaciones = observaciones;
        FechayHora = fechayHora;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRack() {
        return Rack;
    }

    public void setRack(int rack) {
        Rack = rack;
    }

    public int getFila() {
        return Fila;
    }

    public void setFila(int fila) {
        Fila = fila;
    }

    public int getColumna() {
        return Columna;
    }

    public void setColumna(int columna) {
        Columna = columna;
    }

    public String getMateriaPrima() {
        return MateriaPrima;
    }

    public void setMateriaPrima(String materiaPrima) {
        MateriaPrima = materiaPrima;
    }

    public int getLoteMP() {
        return LoteMP;
    }

    public void setLoteMP(int loteMP) {
        LoteMP = loteMP;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }

    public String getPersona() {
        return Persona;
    }

    public void setPersona(String persona) {
        Persona = persona;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public String getFechayHora() {
        return FechayHora;
    }

    public void setFechayHora(String fechayHora) {
        FechayHora = fechayHora;
    }

    @Override
    public String toString() {
        return MateriaPrima;
    }
}
