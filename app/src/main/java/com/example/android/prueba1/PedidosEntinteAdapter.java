package com.example.android.prueba1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PedidosEntinteAdapter extends ArrayAdapter <PedidosEntinte>{

    public PedidosEntinteAdapter(Activity context, ArrayList<PedidosEntinte> pedidosEntinte) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, pedidosEntinte);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.pedidos_entinte, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        PedidosEntinte currentPedidoEntinte = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView pedidoEntinteTextView = (TextView) listItemView.findViewById(R.id.numeroDePedidoEntinte_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        pedidoEntinteTextView.setText(currentPedidoEntinte.getNumerodePedidoEntinte());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView vendedorEntinteTextView = (TextView) listItemView.findViewById(R.id.vendedorEntinte_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        vendedorEntinteTextView.setText(currentPedidoEntinte.getVendedorEntinte());

//        // Find the TextView in the list_item.xml layout with the ID version_number
//        TextView correoTextView = (TextView) listItemView.findViewById(R.id.correo_text_view);
//        // Get the version number from the current AndroidFlavor object and
//        // set this text on the number TextView
//        correoTextView.setText(currentPedido.getCorreo());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView clienteEntinteTextView = (TextView) listItemView.findViewById(R.id.clienteEntinte_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        clienteEntinteTextView.setText(currentPedidoEntinte.getEntonador());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView fechaPrometidaEntinteTextView = (TextView) listItemView.findViewById(R.id.fechaPrometidaEntinte_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        fechaPrometidaEntinteTextView.setText(currentPedidoEntinte.getFechaPrometidaEntinte());

//        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
//        // Get the image resource ID from the current AndroidFlavor object and
//        // set the image to iconView
//        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
