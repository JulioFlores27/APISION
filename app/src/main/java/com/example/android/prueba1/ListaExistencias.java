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

public class ListaExistencias extends ArrayAdapter<Almacen> {

    private Context contexto;
    private ArrayList<Almacen> listitems;

    public ListaExistencias(Activity context, ArrayList<Almacen> almacen) {
        super(context, 0, almacen);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_lista_existencias, parent, false);
        }
        Almacen almacen = getItem(position);

        TextView t1 = (TextView) listItemView.findViewById(R.id.prod_existencia);
        t1.setText(almacen.getMateriaprima());
        TextView t2 = (TextView) listItemView.findViewById(R.id.envase_existencia);
        t2.setText(String.valueOf(almacen.getEnvase()));
        TextView t3 = (TextView) listItemView.findViewById(R.id.cantidad_existencia);
        t3.setText(String.valueOf(almacen.getCantidad()));
        return listItemView;
    }
}
