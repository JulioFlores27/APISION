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

import com.example.android.prueba1.ExampleDialog;
import com.example.android.prueba1.ExampleDialog2;
import com.example.android.prueba1.ExampleDialog2;
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

public class SurtirMPActivity extends AppCompatActivity implements View.OnClickListener, ExampleDialog2.ExampleDialogListener {

    private Button agregarSurtido;
    private Double cantidad_surtida_MP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surtir_mp);

        //Botón modificar un Surtido

        agregarSurtido = findViewById(R.id.agregar_surtido);
        cantidad_surtida_MP = 0.0;

        //Crear un array de módulos

        final ArrayList<Surtido> surtidos = new ArrayList<Surtido>();

        //pedidos.add("Entregas");
        //pedidos.add("18050001");
        //pedidos.add(new Pedidos('18050001, Alejandro Hernández, alejandro.hernandez@nervion.com.mx, Cliente 1, 43256'));
        surtidos.add(new Surtido("1-1-9","A0574","Kilo", "2006050628","4.8", "0" ));
        surtidos.add(new Surtido("1-1-10","R0369","Kilo", "2006111296","120" ,"0"));
        surtidos.add(new Surtido("1-1-2","A0598","Kilo", "2007050474","0.152" , "0"));
        surtidos.add(new Surtido("8-1-4","P8521","Kilo", "2004030264","56.5" , "0"));
        //surtidos.add(new Surtido("8-1-5","SOLVENTE S-121","BOTE 4", "63900","2" , "0"));
        //surtidos.add(new Surtido("10-2-2","REACTOR R-75","BOTE 2", "63985","6" , "0"));

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

                double cantidadNueva = (Double.parseDouble(esteSurtido.getCantidadContadaSurtido()));

                double cantidadPedida = (Double.parseDouble(esteSurtido.getCantidadSurtido()));

                if (cantidadNueva < cantidadPedida){

                    openDialog();
                   // applyText();

                    Toast.makeText(SurtirMPActivity.this,position2.toString(),Toast.LENGTH_LONG).show();

                    cantidadNueva = cantidad_surtida_MP;
                   // String cantidadNuevaString = cantidadNueva.get

                    esteSurtido.setmCantidadContada(String.valueOf(cantidadNueva));
                    listView.setAdapter(surtidoadapter);

                } else {

                    Toast.makeText(SurtirMPActivity.this, "Producto completo", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }



    public final void openDialog() {
        ExampleDialog2 exampleDialog = new ExampleDialog2();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void applyText(String codigo_barras) {
        Double cantidad_surtida_MP_usuario = Double.parseDouble(codigo_barras);
        cantidad_surtida_MP = cantidad_surtida_MP_usuario;


    }


//    @Override
//    public void actualizar_cantidad_surtida_mp(String cantidad_surtida_mp) {
//        //barcodeValue.setText(codigo_barras);
//
//
//            String [] parts = cantidad_surtida_mp.split("-");
//            String part1 = parts[0];
//            String part2 = parts[1];
//            String part3 = parts[2];
//            String part4 = parts[3];
//            String part5 = parts[4];
//            String part6 = parts[5];
//
//
//            String producto_string = part1+"-"+part2+"-"+part3;
//
////            barcodeValue.setText(codigo_barras);
//
//
//
//
//    }

}
