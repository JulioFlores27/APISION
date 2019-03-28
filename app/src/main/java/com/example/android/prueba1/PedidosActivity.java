package com.example.android.prueba1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        //Crear un array de módulos

        final ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();

        //pedidos.add("Entregas");
        //pedidos.add("18050001");
        //pedidos.add(new Pedidos('18050001, Alejandro Hernández, alejandro.hernandez@nervion.com.mx, Cliente 1, 43256'));
        pedidos.add(new Pedidos("18050000", "Yayo Ibarrola", "yayo@nervion.com.mx", "Cliente 1", "27/06/2018"));
        pedidos.add(new Pedidos("18050001", "Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Cliente 1", "27/06/2018"));
        pedidos.add(new Pedidos("18050002", "Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Cliente 2", "28/06/2018"));
        pedidos.add(new Pedidos("18050003", "Alfredo Santos", "alfredo.santos@nervion.com.mx", "Cliente 3", "27/06/2018"));
        pedidos.add(new Pedidos("18050004", "Verónica Mendoza", "veronica.mendoza@nerion.com.mx", "Cliente 4", "28/06/2018"));
        pedidos.add(new Pedidos("18050005", "Francisco Vázquez", "francisco.vazquez@nervion.com.mx", "Cliente 5", "28/06/2018"));
        pedidos.add(new Pedidos("18050006", "Alejandro Hernández", "alejandro.hernandez@nervion.com.mx", "Cliente 6", "28/06/2018"));
        pedidos.add(new Pedidos("18050007", "Patricia Zermeño", "patricia.zermeno@nervion.com.mx", "Cliente 7", "28/06/2018"));
        pedidos.add(new Pedidos("18050008", "Alfredo Santos", "alfredo.santos@nervion.com.mx", "Cliente 8", "28/06/2018"));
        pedidos.add(new Pedidos("18050009", "Verónica Mendoza", "veronica.mendoza@nerion.com.mx", "Cliente 9", "28/06/2018"));
        pedidos.add(new Pedidos("18050010", "Francisco Vázquez", "francisco.vazquez@nervion.com.mx", "Cliente 10", "28/06/2018"));









        PedidosAdapter adapter = new PedidosAdapter(this, pedidos);


        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pedidos position2 = pedidos.get((int) id);
                Pedidos id2 = pedidos.get(position);

                //Toast.makeText(PedidosActivity.this,"Clicked!"+position2+id2, Toast.LENGTH_SHORT).show();



                // When clicked, show a toast with the TextView text, http://fundoocode.net/android-listview-checkbox-example-onitemclicklistener-and-onclicklistener/
                Pedidos estePedido = (Pedidos) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Clicked on Row: " + estePedido.getCliente()+ "Pedido: " + estePedido.getNumerodePedido(),
                        Toast.LENGTH_LONG).show();
                String numeroDeEstePedido = estePedido.getNumerodePedido();
                String correoDeEstePedido = estePedido.getCorreo();

                // Abrir mapas
                Intent maps = new Intent(PedidosActivity.this, MapsActivity.class);
                maps.putExtra("estePedido",numeroDeEstePedido);
                maps.putExtra("esteCorreo",correoDeEstePedido);
                startActivity(maps);

            }
        });



    }

}




//        for (int i = 0; i<modulos.size(); i++){
//            TextView wordView = new TextView(this);
//            wordView.setText(modulos.get(i));
//            rootView.addView(wordView);
//        }