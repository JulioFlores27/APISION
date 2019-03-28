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

public class PedidoEntinteAdapter extends ArrayAdapter <PedidoEntinte> {

    public PedidoEntinteAdapter(Activity context, ArrayList<PedidoEntinte> pedido_entinte) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, pedido_entinte);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.pedido_entinte, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        PedidoEntinte currentPedidoEntinte = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView NoPedidoTextView = (TextView) listItemView.findViewById(R.id.NoPedido_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView


      NoPedidoTextView.setText(String.valueOf(currentPedidoEntinte.getmNoPedido()));



        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView ProductoEntinteTextView = (TextView) listItemView.findViewById(R.id.Producto_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        ProductoEntinteTextView.setText(currentPedidoEntinte.getmProducto());



        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView ObservacionesTextView = (TextView) listItemView.findViewById(R.id.Observaciones_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        ObservacionesTextView.setText(currentPedidoEntinte.getmObservaciones());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView EtapaTextView = (TextView) listItemView.findViewById(R.id.Etapa1_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        EtapaTextView.setText(currentPedidoEntinte.getmEtapa1());

//        // Find the TextView in the list_item.xml layout with the ID version_name
//        TextView TipoEnvaseTextView = (TextView) listItemView.findViewById(R.id.descripcion_envase_surtido_text_view);
//        // Get the version name from the current AndroidFlavor object and
//        // set this text on the name TextView
//        EnvaseSurtidoTextView.setText(currentSurtido.getDescripcionEnvaseSurtido());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView TipoEnvaseTextView = (TextView) listItemView.findViewById(R.id.TipoEnvase_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        TipoEnvaseTextView.setText(currentPedidoEntinte.getmCantidad()+" "+currentPedidoEntinte.getmTipoEnvase());


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView PersonaAsignadaTextView = (TextView) listItemView.findViewById(R.id.PersonaAsignada_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        PersonaAsignadaTextView.setText((currentPedidoEntinte.getmPersonaAsignada()));

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView FechaAsignacionTextView = (TextView) listItemView.findViewById(R.id.FechaAsignacion_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        FechaAsignacionTextView.setText(currentPedidoEntinte.getmFechaAsignacion());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView FechaCapturaTextView = (TextView) listItemView.findViewById(R.id.FechaCaptura_entinte);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        FechaCapturaTextView.setText(currentPedidoEntinte.getmFechaCaptura());





        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }



}
