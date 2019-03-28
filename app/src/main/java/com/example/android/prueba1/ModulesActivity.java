package com.example.android.prueba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ModulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        //Crear un array de módulos

        ArrayList <String> modulos = new ArrayList<String>();
        modulos.add("Entregas");
        modulos.add("Producción");
        modulos.add("Vigilancia");

        //Encontrar la rootView

        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

        //Contador de whiles



        for (int i = 0; i<modulos.size(); i++){
            TextView wordView = new TextView(this);
            wordView.setText(modulos.get(i));
            rootView.addView(wordView);
        }

        

//        int i = 0;

//        while (i<modulos.size()) {
//
//            TextView wordView = new TextView(this);
//            wordView.setText(modulos.get(i));
//            rootView.addView(wordView);
//            i++;
//        }




    }
}
