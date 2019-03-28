package com.example.android.prueba1.barcodereader.ui.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.prueba1.Modulos;
import com.example.android.prueba1.ModulosAdapter;
import com.example.android.prueba1.Surtido;
import com.example.android.prueba1.SurtidoAdapter;
import com.example.android.prueba1.barcodereader.BarcodeActivity;

import com.example.android.prueba1.barcodereader.BarcodeCaptureActivity;
import com.example.android.prueba1.barcodereader.ui.camera.SurtirActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import com.example.android.prueba1.R;

public class SurtirActivity extends AppCompatActivity {

    private Button agregarSurtido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surtir);

        //Botón modificar un Surtido

        agregarSurtido = findViewById(R.id.agregar_surtido);

        //Crear un array de módulos

        final ArrayList<Surtido> surtidos = new ArrayList<Surtido>();

        //pedidos.add("Entregas");
        //pedidos.add("18050001");
        //pedidos.add(new Pedidos('18050001, Alejandro Hernández, alejandro.hernandez@nervion.com.mx, Cliente 1, 43256'));
        surtidos.add(new Surtido("1-1-1","POLYNER 75 BLANCO","BOTE 4", "62451","5", "0" ));
        surtidos.add(new Surtido("1-1-1","POLYNER 75 BLANCO","BOTE 4", "62452","1" ,"0"));
        surtidos.add(new Surtido("1-1-2","POLYNER 75 NEGRO","BOTE 1", "63985","3" , "0"));
        surtidos.add(new Surtido("8-1-4","SOLVENTE S-121","BOTE 1", "63900","4" , "0"));
        surtidos.add(new Surtido("8-1-5","SOLVENTE S-121","BOTE 4", "63900","2" , "0"));
        surtidos.add(new Surtido("10-2-2","REACTOR R-75","BOTE 2", "63985","6" , "0"));

        final SurtidoAdapter surtidoadapter = new SurtidoAdapter(this, surtidos);

        final ListView listView = findViewById(R.id.lvSurtidos);

        listView.setAdapter(surtidoadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Surtido position2 = surtidos.get((int) id);
                Surtido id2 = surtidos.get(position);

                // When clicked, show a toast with the TextView text, http://fundoocode.net/android-listview-checkbox-example-onitemclicklistener-and-onclicklistener/
                Surtido esteSurtido = (Surtido) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(),"Clicked on Row: " + esteModulo.getNombreModulo(), Toast.LENGTH_LONG).show();
                String CantidadDeEstePedido = esteSurtido.getCantidadContadaSurtido();
                //esteSurtido

                int cantidadNueva = (Integer.parseInt(esteSurtido.getCantidadContadaSurtido()));

                int cantidadPedida = (Integer.parseInt(esteSurtido.getCantidadSurtido()));

                if (cantidadNueva < cantidadPedida){

                    cantidadNueva = cantidadNueva + 1;
                    //String cantidadNuevaString = cantidadNueva.get

                    esteSurtido.setmCantidadContada(String.valueOf(cantidadNueva));
                    listView.setAdapter(surtidoadapter);

                } else {

                    Toast.makeText(SurtirActivity.this, "Producto completo", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
