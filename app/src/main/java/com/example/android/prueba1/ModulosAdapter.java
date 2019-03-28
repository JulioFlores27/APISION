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

public class ModulosAdapter extends ArrayAdapter <Modulos> {

    public ModulosAdapter(Activity context, ArrayList<Modulos> modulos) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, modulos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.modulos, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Modulos currentModulo = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView moduloTextView = (TextView) listItemView.findViewById(R.id.nombreModulo_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        moduloTextView.setText(currentModulo.getNombreModulo());


        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.icono);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(currentModulo.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
