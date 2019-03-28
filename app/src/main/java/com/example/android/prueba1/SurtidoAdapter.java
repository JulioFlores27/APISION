package com.example.android.prueba1;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SurtidoAdapter extends ArrayAdapter <Surtido>{

    public SurtidoAdapter(Activity context, ArrayList<Surtido> surtidos) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, surtidos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.surtidos, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Surtido currentSurtido = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView UbicacionSurtidoTextView = (TextView) listItemView.findViewById(R.id.ubicacion_surtido_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        UbicacionSurtidoTextView.setText(currentSurtido.getUbicacionSurtido());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView ProductoSurtidoTextView = (TextView) listItemView.findViewById(R.id.descripcion_producto_surtido_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        ProductoSurtidoTextView.setText(currentSurtido.getDescripcionProductoSurtido());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView EnvaseSurtidoTextView = (TextView) listItemView.findViewById(R.id.descripcion_envase_surtido_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        EnvaseSurtidoTextView.setText(currentSurtido.getDescripcionEnvaseSurtido());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView LoteSurtidoTextView = (TextView) listItemView.findViewById(R.id.lote_surtido_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        LoteSurtidoTextView.setText(currentSurtido.getLoteSurtido());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView CantidadSurtidoTextView = (TextView) listItemView.findViewById(R.id.cantidad_surtido_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        CantidadSurtidoTextView.setText(currentSurtido.getCantidadSurtido());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView CantidadContadaSurtidoTextView = (TextView) listItemView.findViewById(R.id.cantidad_contada_surtido_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        CantidadContadaSurtidoTextView.setText((currentSurtido.getCantidadContadaSurtido())+"/");





        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
