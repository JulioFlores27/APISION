package com.example.android.prueba1;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PedidosEntinteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_entinte);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Asignar pedidos a: ", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //Crear un array de módulos

         final ArrayList<PedidosEntinte> pedidosEntinte = new ArrayList<PedidosEntinte>();

        //pedidos.add("Entregas");
        //pedidos.add("18050001");
        //pedidos.add(new Pedidos('18050001, Alejandro Hernández, alejandro.hernandez@nervion.com.mx, Cliente 1, 43256'));
        pedidosEntinte.add(new PedidosEntinte("18050000", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: Marco Antonio", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050001", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050002", "Vendedor: Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Entonador: ", "28/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050003", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050004", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050005", "Vendedor: Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Entonador: ", "28/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050006", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050007", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050008", "Vendedor: Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Entonador: ", "28/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050009", "Vendedor: Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Entonador: ", "28/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050010", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050011", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050012", "Vendedor: Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Entonador: ", "28/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050013", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050014", "Vendedor: Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Entonador: ", "27/06/2018"));
        pedidosEntinte.add(new PedidosEntinte("18050015", "Vendedor: Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Entonador: ", "28/06/2018"));









        PedidosEntinteAdapter adapter = new PedidosEntinteAdapter(this, pedidosEntinte);


        final ListView listView = (ListView) findViewById(R.id.listEntinte);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                PedidosEntinte position2 = pedidosEntinte.get((int) id);
                PedidosEntinte id2 = pedidosEntinte.get(position);

                //Toast.makeText(PedidosActivity.this,"Clicked!"+position2+id2, Toast.LENGTH_SHORT).show();



                // When clicked, show a toast with the TextView text, http://fundoocode.net/android-listview-checkbox-example-onitemclicklistener-and-onclicklistener/
                PedidosEntinte estePedidoEntinte = (PedidosEntinte) parent.getItemAtPosition(position);
                estePedidoEntinte.setEntonador("Neri");
                Toast.makeText(getApplicationContext(),"Clicked on Row: " + estePedidoEntinte.getEntonador()+ "Pedido: " + estePedidoEntinte.getNumerodePedidoEntinte(),
                        Toast.LENGTH_LONG).show();
                String numeroDeEstePedido = estePedidoEntinte.getNumerodePedidoEntinte();
                String correoDeEstePedido = estePedidoEntinte.getCorreoEntinte();


                // Abrir mapas
//                Intent maps = new Intent(PedidosActivity.this, MapsActivity.class);
//                maps.putExtra("estePedido",numeroDeEstePedido);
//                maps.putExtra("esteCorreo",correoDeEstePedido);
//                startActivity(maps);

            }
        });



    }

}




//        for (int i = 0; i<modulos.size(); i++){
//            TextView wordView = new TextView(this);
//            wordView.setText(modulos.get(i));
//            rootView.addView(wordView);
//        }