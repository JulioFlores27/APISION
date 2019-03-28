package com.example.android.prueba1;

public class Seleccion {

    private int Registro;
    private String Nombre;
    private String Funcion;
    private String Sustrato;
    private String Base;

    public Seleccion(int registro, String nombre, String funcion, String sustrato, String base) {
        Registro = registro;
        Nombre = nombre;
        Funcion = funcion;
        Sustrato = sustrato;
        Base = base;
    }

    public Seleccion() {

    }

    public int getRegistro() {
        return Registro;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getFuncion() {
        return Funcion;
    }

    public String getSustrato() {
        return Sustrato;
    }

    public String getBase() {
        return Base;
    }

    public void setRegistro(int registro) {
        Registro = registro;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setFuncion(String funcion) {
        Funcion = funcion;
    }

    public void setSustrato(String sustrato) {
        Sustrato = sustrato;
    }

    public void setBase(String base) {
        Base = base;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
