package com.example.android.prueba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.prueba1.barcodereader.ui.camera.SurtirActivity;

import java.util.ArrayList;

public class Entinte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entinte);


        final ArrayList<PedidoEntinte> entinte = new ArrayList<PedidoEntinte>();

        //pedidos.add("Entregas");
        //pedidos.add("18050001");
        //pedidos.add(new Pedidos('18050001, Alejandro Hernández, alejandro.hernandez@nervion.com.mx, Cliente 1, 43256'));
        entinte.add(new PedidoEntinte(1,18110439,"POLYNER 75 COLOR PASTEL","BEIGE SEGUN MUESTRA", "CUBETA 10",3, "10/10/2018", "11/10/2018", "Por verificar", "0", "Geovannie" ));
        entinte.add(new PedidoEntinte(2,18110440,"POLYNER 75 GRIS ENTONADO","GRIS RATA", "BOTE 4",4, "11/10/2018", "", "Pendiente", "0", "" ));
        entinte.add(new PedidoEntinte(3,18110448,"ACRYNER 15 AZUL ENTONADO","AZUL ALBERCA", "CUBETA 20",1, "13/10/2018", "13/10/2018", "Por verificar", "0", "Julio" ));
        entinte.add(new PedidoEntinte(4,18110449,"NERVION 50 AMARILLO ENTONADO","AMARILLO TRAFICO", "CUBETA 20",1, "13/10/2018", "13/10/2018", "Por verificar", "0", "Toño" ));
        entinte.add(new PedidoEntinte(5,18110455,"POLYNER 75 GRIS ENTONADO","GRIS CEMEX", "BOTE 1",4, "15/10/2018", "", "Pendiente", "0", "" ));



        final PedidoEntinteAdapter entinteadapter = new PedidoEntinteAdapter(this, entinte);

        final ListView listView = findViewById(R.id.lvPedidoEntinte);

        listView.setAdapter(entinteadapter);





    }
}
