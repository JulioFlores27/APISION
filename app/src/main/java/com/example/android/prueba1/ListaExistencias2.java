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

public class ListaExistencias2 extends ArrayAdapter<Existencia> {

    private Context contexto;
    private ArrayList<Existencia> listitems;

    public ListaExistencias2(Activity context, ArrayList<Existencia> existencia) {
        super(context, 0, existencia);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_lista_existencias, parent, false);
        }
        Existencia existencia = getItem(position);

        TextView t1 = (TextView) listItemView.findViewById(R.id.prod_existencia);
        t1.setText(existencia.getProducto());
        TextView t2 = (TextView) listItemView.findViewById(R.id.envase_existencia);
        t2.setText(String.valueOf(existencia.getEnvase()));
        TextView t3 = (TextView) listItemView.findViewById(R.id.cantidad_existencia);
        t3.setText(String.valueOf(existencia.getCantidad()));
        return listItemView;
    }
}
