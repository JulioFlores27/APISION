package com.example.android.prueba1;

public class Entrega {

    private int Solicitud;
    private String Nombre_Entrega;
    private double Latitud;
    private double Longitud;
    private String Fecha;
    private String Hora;

    public Entrega() {
    }

    public Entrega(int solicitud, String nombre_Entrega, double latitud, double longitud, String fecha, String hora) {
        Solicitud = solicitud;
        Nombre_Entrega = nombre_Entrega;
        Latitud = latitud;
        Longitud = longitud;
        Fecha = fecha;
        Hora = hora;
    }

    public int getSolicitud() {
        return Solicitud;
    }

    public void setSolicitud(int solicitud) {
        Solicitud = solicitud;
    }

    public String getNombre_Entrega() {
        return Nombre_Entrega;
    }

    public void setNombre_Entrega(String usuario) {
        Nombre_Entrega = Nombre_Entrega;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    @Override
    public String toString() {
        return Nombre_Entrega;
    }
}
