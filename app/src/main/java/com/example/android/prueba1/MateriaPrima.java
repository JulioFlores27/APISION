package com.example.android.prueba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;


public class MateriaPrima extends AppCompatActivity {
    private EditText etMateriaPrima, etLote, etCantidad, etUbicacion;

   private AsyncHttpClient cliente;
    Button boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_prima);

        etMateriaPrima = (EditText) findViewById(R.id.matpri1);
        etLote = (EditText) findViewById(R.id.lote1);
        etCantidad = (EditText) findViewById(R.id.cantidad1);
        etUbicacion = (EditText) findViewById(R.id.ubicacion1);

        boton1 = (Button) findViewById(R.id.b1);

        cliente = new AsyncHttpClient();
        
//        boton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MateriaPrimaClass mp = new MateriaPrimaClass();
//                mp.setCantidad(2.4);
//                mp.setColumna(1);
//                mp.setFechayHora("2");
//                mp.setFila(1);
//                mp.setID(14);
//                mp.setLoteMP(2018040502);
//                mp.setMateriaPrima("R0098");
//                mp.setObservaciones("Entrada");
//                mp.setPersona("Javier");
//                mp.setRack(1);
//
//            }
//        });
    }

    private  void botonGuardar (){
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCantidad.getText().toString().isEmpty() || etLote.getText().toString().isEmpty() || etMateriaPrima.getText().toString().isEmpty() || etUbicacion.getText().toString().isEmpty()){

                    //Toast.makeText(MateriaPrima.this, )

                }

            }
        });
    }



}
