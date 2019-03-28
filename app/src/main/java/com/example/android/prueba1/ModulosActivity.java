package com.example.android.prueba1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.prueba1.barcodereader.BarcodeActivity;

import com.example.android.prueba1.barcodereader.BarcodeCaptureActivity;
import com.example.android.prueba1.barcodereader.ui.camera.SurtirActivity;
import com.example.android.prueba1.barcodereader.ui.camera.SurtirMPActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ModulosActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulos);

        //Firebase instance
        mAuth = FirebaseAuth.getInstance();

        //Crear un array de módulos

        final ArrayList<Modulos> modulos = new ArrayList<Modulos>();

        //pedidos.add("Entregas");
        //pedidos.add("18050001");
        //pedidos.add(new Pedidos('18050001, Alejandro Hernández, alejandro.hernandez@nervion.com.mx, Cliente 1, 43256'));

        modulos.add(new Modulos("Entregas",R.mipmap.target_78406));

//        modulos.add(new Modulos("Entinte",R.mipmap.glasses_3d));
        modulos.add(new Modulos("Vigilancia",R.mipmap.binoculars_78394));
        modulos.add(new Modulos("Visitas comerciales",R.mipmap.handshake_78379));
        modulos.add(new Modulos("Exportaciones",R.mipmap.paperplane_78358));
        modulos.add(new Modulos("Producción",R.mipmap.cogwheel_77907));
        modulos.add(new Modulos("Surtido",R.mipmap.shoppingcart_77905));


        modulos.add(new Modulos("Inventarios",R.mipmap.clipboard));


//        modulos.add(new Modulos("Materia prima Javier",R.mipmap.clipboard));
        modulos.add(new Modulos("Seleccionador",R.mipmap.search_77922));
        modulos.add(new Modulos("Laboratorio",R.mipmap.flask_77920));
        modulos.add(new Modulos("Sucursales",R.mipmap.worldwide_78374));
//        //modulos.add(new Modulos("Cerrar sesión",R.mipmap.padlock_77917));
//        //modulos.add(new Modulos("E prueba",R.mipmap.entrega));
        ModulosAdapter adapter = new ModulosAdapter(this, modulos);


        GridView gridView = (GridView) findViewById(R.id.listModulos);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Modulos position2 = modulos.get((int) id);
                Modulos id2 = modulos.get(position);


                //Toast.makeText(ModulosActivity.this,"Clicked!"+position2+id2, Toast.LENGTH_SHORT).show();



                // When clicked, show a toast with the TextView text, http://fundoocode.net/android-listview-checkbox-example-onitemclicklistener-and-onclicklistener/
                Modulos esteModulo = (Modulos) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(),"Clicked on Row: " + esteModulo.getNombreModulo(), Toast.LENGTH_LONG).show();
                int iconoDeEstePedido = esteModulo.getImageResourceId();


                // Abrir actividad

                if (esteModulo.getNombreModulo()=="Entregas"){
                    Intent pedidosPrueba = new Intent(ModulosActivity.this, MapsActivity.class);
                    startActivity(pedidosPrueba);}
//
//                if (esteModulo.getNombreModulo()=="Entregas"){
//                Intent pedidos = new Intent(ModulosActivity.this, PedidosActivity.class);
//                startActivity(pedidos);}

                if (esteModulo.getNombreModulo()=="Vigilancia"){
                    Intent barcode = new Intent(ModulosActivity.this, BarcodeActivity.class);
                    startActivity(barcode);}

                if (esteModulo.getNombreModulo()=="Cerrar sesión"){

                    FirebaseAuth.getInstance().signOut();
                    sendToStart(); }

                if (esteModulo.getNombreModulo()=="Producción"){
                    Intent pedidosEntinte = new Intent(ModulosActivity.this, PedidosEntinteActivity.class);
                    startActivity(pedidosEntinte);}

//                if (esteModulo.getNombreModulo()=="Surtido"){
//                    Intent surtirPedidos = new Intent(ModulosActivity.this, SurtirActivity.class);
//                    startActivity(surtirPedidos);}

                if (esteModulo.getNombreModulo()=="Surtido"){
                    Intent seleccionarPedido = new Intent(ModulosActivity.this, SeleccionarPedidoActivity.class);
                    startActivity(seleccionarPedido);}


                if (esteModulo.getNombreModulo()=="Exportaciones"){
                    Intent pruebaDB = new Intent(ModulosActivity.this, Main2Activity.class);
                    startActivity(pruebaDB);}

                if (esteModulo.getNombreModulo()=="Visitas comerciales"){
                    Intent comerciales = new Intent(ModulosActivity.this, Maps2Activity.class);
                    startActivity(comerciales);}

                if (esteModulo.getNombreModulo()=="Ventas"){
                    Intent pruebaDB2 = new Intent(ModulosActivity.this, Main3Activity.class);
                    startActivity(pruebaDB2);}

                if (esteModulo.getNombreModulo()=="Seleccionador"){
                    Intent seleccionador = new Intent(ModulosActivity.this, SeleccionadorActivity.class);
                    startActivity(seleccionador);}

                if (esteModulo.getNombreModulo()=="Materia prima Javier"){
                    Intent materiaPrima = new Intent(ModulosActivity.this, SurtirMPActivity.class);
                    startActivity(materiaPrima);}

//                if (esteModulo.getNombreModulo()=="Materia prima Julio Hugo"){
//                    Intent materiaPrima2 = new Intent(ModulosActivity.this, MateriaPrima.class);
//                    startActivity(materiaPrima2);}

                if (esteModulo.getNombreModulo()=="Inventarios"){
                    Intent inventarios = new Intent(ModulosActivity.this, Main4Activity.class);
                    startActivity(inventarios);}

                if (esteModulo.getNombreModulo()=="Entinte"){
                    Intent Entinte = new Intent(ModulosActivity.this, Entinte.class);
                    startActivity(Entinte);}
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            sendToStart();
        }

    }

    private void sendToStart() {
        Intent startIntent = new Intent(ModulosActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

}





