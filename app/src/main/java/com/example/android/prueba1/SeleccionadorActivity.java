package com.example.android.prueba1;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.prueba1.barcodereader.BarcodeActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SeleccionadorActivity extends AppCompatActivity {

    private Spinner spinner_base;
    private Spinner spinner_funcion;
    //private Spinner spinner_sector;
    private Spinner spinner_sustrato;
    private Button button_buscar_producto;
    private ListView lvProductos;

    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionador);

        spinner_base = (Spinner) findViewById(R.id.spinner_bases);
        spinner_funcion = (Spinner) findViewById(R.id.spinner_funciones);
       // spinner_sector = (Spinner) findViewById(R.id.spinner_sectores);
        spinner_sustrato = (Spinner) findViewById(R.id.spinner_sustratos);
        button_buscar_producto = (Button) findViewById(R.id.button_buscar_producto);
        lvProductos = (ListView)findViewById(R.id.lvSeleccionador);
        cliente = new AsyncHttpClient();

        obtenerSeleccion();

        button_buscar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar_productos();
            }
        });



    }

    private void buscar_productos() {
        String base_usuario = spinner_base.getSelectedItem().toString();



        //obtenerSeleccionBase();
        //obtenerSeleccionBaseSustrato();
        obtenerSeleccionBaseSustratoFuncion();

    }

    public void obtenerSeleccion(){

        String base_php="0";
        String funcion_php="0";
        //String sector_php="0";
        String sustrato_php="0";

        String base_usuario = spinner_base.toString();

//            if (rack_valor.equals("")){
//
//            }else{
//                rack_php=rack_valor.getText().toString();
//                fila_php=fila_valor.getText().toString();
//                columna_php=columna_valor.getText().toString();
//            }

        String url ="https://appsionmovil.000webhostapp.com/obtener_datos_seleccionador.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    listarProductos(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    };


    public void obtenerSeleccionBase(){

        String base_php="0";
        String funcion_php="0";
        //String sector_php="0";
        String sustrato_php="0";

        String base_usuario = spinner_base.getSelectedItem().toString().replaceAll(" ","%20").replaceAll("ó","?");

//            if (rack_valor.equals("")){
//
//            }else{
//                rack_php=rack_valor.getText().toString();
//                fila_php=fila_valor.getText().toString();
//                columna_php=columna_valor.getText().toString();
//            }

        String url ="https://appsionmovil.000webhostapp.com/obtener_datos_seleccionador_base.php?Base="+"'"+base_usuario+"'";

        Toast.makeText(SeleccionadorActivity.this, url, Toast.LENGTH_SHORT).show();
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    listarProductos(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    };

    public void obtenerSeleccionBaseSustrato(){

        String base_php="0";
        String funcion_php="0";
       // String sector_php="0";
        String sustrato_php="0";

        String base_usuario = spinner_base.getSelectedItem().toString().replaceAll(" ","%20").replaceAll("ó","?");
        String base_usuario2 = "='"+base_usuario+"'";
        if (base_usuario.equals("Todas")){
            base_usuario2 = "=%27%%27";
        }



        String sustrato_usuario = spinner_sustrato.getSelectedItem().toString().replaceAll(" ","%20").replaceAll("ó","?");


        String url ="https://appsionmovil.000webhostapp.com/obtener_datos_seleccionador_base_sustrato.php?Base"+base_usuario2+"&Sustrato="+"'"+sustrato_usuario+"'";

        Toast.makeText(SeleccionadorActivity.this, url, Toast.LENGTH_LONG).show();
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    listarProductos(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    };

    public void obtenerSeleccionBaseSustratoFuncion(){

        String base_php="0";
        String funcion_php="0";
        // String sector_php="0";
        String sustrato_php="0";

        String base_usuario = spinner_base.getSelectedItem().toString().replaceAll(" ","%20").replaceAll("ó","?");
        if (base_usuario.equals("Todas")){
            base_usuario = "%25";
        }
        String base_usuario2 = "='"+base_usuario+"'";

        String sustrato_usuario = spinner_sustrato.getSelectedItem().toString().replaceAll(" ","%20").replaceAll("ó","?");
        if (sustrato_usuario.equals("Todos")){
            sustrato_usuario = "%25";
        }

        String sustrato_usuario2 = "='"+sustrato_usuario+"'";


        String funcion_usuario = spinner_funcion.getSelectedItem().toString().replaceAll(" ","%20").replaceAll("ó","?");
        if (funcion_usuario.equals("Todas")){
            funcion_usuario = "%25";
        }


        String funcion_usuario2 = "='"+funcion_usuario+"'";

        String url ="https://appsionmovil.000webhostapp.com/obtener_datos_seleccionador_base_sustrato_funcion.php?Base"+base_usuario2+"&Sustrato"+sustrato_usuario2+"&Funcion"+funcion_usuario2;

        Toast.makeText(SeleccionadorActivity.this, url, Toast.LENGTH_LONG).show();
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    listarProductos(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    };


    private void listarProductos (String respuesta){

        final ArrayList <Seleccion> lista = new ArrayList<Seleccion>();

        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i=0; i<jsonArreglo.length(); i++){
                Seleccion s = new Seleccion();
                s.setNombre(jsonArreglo.getJSONObject(i).getString("Nombre"));
                s.setFuncion(jsonArreglo.getJSONObject(i).getString("Funcion"));
                s.setSustrato(jsonArreglo.getJSONObject(i).getString("Sustrato"));
                s.setBase(jsonArreglo.getJSONObject(i).getString("Base"));
                lista.add(s);
            }

            ArrayAdapter <Seleccion> a = new ArrayAdapter(this,android.R.layout.simple_list_item_1, lista);
            lvProductos.setAdapter(a);

            lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Seleccion s = lista.get(position);
                    StringBuffer b = new StringBuffer();

                    b.append("Nombre: " + s.getNombre()+"\n");
                    b.append("Base: " + s.getBase()+"\n");
                    b.append("Función: "+s.getFuncion()+"\n");
                    b.append("Sustrato: "+s.getSustrato()+"\n");

                    AlertDialog.Builder a = new AlertDialog.Builder(SeleccionadorActivity.this);
                    a.setCancelable(true);
                    a.setTitle("Detalle");
                    a.setMessage(b.toString());
                    a.show();

                }
            });


        }catch (Exception e1){
            e1.printStackTrace();
        }

    }



}
