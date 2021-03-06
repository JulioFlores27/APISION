package com.example.android.prueba1;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaUbicacionesContadas extends ArrayAdapter<Almacen> {

    private Context contexto;
    private ArrayList<Almacen> listitems;

    public ListaUbicacionesContadas(Activity context, ArrayList<Almacen> almacen) {
        super(context, 0, almacen);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_lista_ubicaciones_contadas, parent, false);
        }
        Almacen almacen = getItem(position);

        TextView t1 = (TextView) listItemView.findViewById(R.id.ubi_contada);
        t1.setText(String.valueOf(almacen.getRack()+"-"+almacen.getFila()+"-"+almacen.getColumna()));
        TextView t2 = (TextView) listItemView.findViewById(R.id.pers_contada);
        t2.setText(String.valueOf(almacen.getPersona()));
        TextView t3 = (TextView) listItemView.findViewById(R.id.fechayhora_contada);
        t3.setText(almacen.getFechahora());
        return listItemView;
    }
}
