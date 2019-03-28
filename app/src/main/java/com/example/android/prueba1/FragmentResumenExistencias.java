package com.example.android.prueba1;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.flags.impl.DataUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

import cz.msebera.android.httpclient.Header;


public class FragmentResumenExistencias extends Fragment {

    ListaExistencias adaptadores;

    ListView lista2;
    AsyncHttpClient cliente;
    Button boton1;
    String usuario;


    //Prueba para tienda
    String tienda;



    //Usuario
    private FirebaseAuth mAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View conteo = inflater.inflate(R.layout.fragment_fragment_resumen_existencias, container, false);
        lista2 = (ListView) conteo.findViewById(R.id.listaresumen_existencias);
        boton1 = (Button) conteo.findViewById(R.id.b1_resumen_existencias);
        tienda = getString(R.string.Tienda);

        //Usuario
        mAuth = FirebaseAuth.getInstance();


        cliente = new AsyncHttpClient();

        usuario = mAuth.getCurrentUser().getEmail().toString();

        ObtenerResumenExistencias();
        return conteo;

    }



    private void ObtenerResumenExistencias(){

//        //Prueba
//        String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_resumen_existencias.php";

        //Real
        String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_resumen_existencias.php"+"?Tienda="+tienda;

        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) { listaralmacen(new String(responseBody)); }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
        });



    }




//Hosting prueba
    public void listaralmacen(String respuesta) {
        final ArrayList<Almacen> lista = new ArrayList<Almacen>();
        try{
            final JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i=0; i<jsonarreglo.length(); i++){
                Almacen t = new Almacen();
                t.setId(jsonarreglo.getJSONObject(i).getInt("ID"));
                t.setRack(jsonarreglo.getJSONObject(i).getInt("Rack"));
                t.setFila(jsonarreglo.getJSONObject(i).getInt("Fila"));
                t.setColumna(jsonarreglo.getJSONObject(i).getInt("Columna"));
                t.setMateriaprima(jsonarreglo.getJSONObject(i).getString("MateriaPrima"));
                t.setEnvase(jsonarreglo.getJSONObject(i).getString("Envase"));

                t.setLotemp(jsonarreglo.getJSONObject(i).getInt("LoteMP"));
                t.setCantidad(jsonarreglo.getJSONObject(i).getDouble("Cantidad"));
                t.setPersona(jsonarreglo.getJSONObject(i).getString("Persona"));
                t.setObservaciones(jsonarreglo.getJSONObject(i).getString("Observaciones"));
                t.setFechahora(jsonarreglo.getJSONObject(i).getString("FechayHora"));
                lista.add(t);
            }
            adaptadores = new ListaExistencias(getActivity(), lista);
            lista2.setAdapter(adaptadores);
            ;



        }catch (Exception e1){
            e1.printStackTrace();
        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObtenerResumenExistencias();
            }
        });
    }


//    //Cambio a hosting real
//
//    public void listarexistencia(String respuesta) {
//        final ArrayList<Existencia> lista = new ArrayList<Existencia>();
//        try{
//            final JSONArray jsonarreglo = new JSONArray(respuesta);
//            for (int i=0; i<jsonarreglo.length(); i++){
//                Existencia t = new Existencia();
//                t.setId(jsonarreglo.getJSONObject(i).getInt("ID"));
//                t.setRack(jsonarreglo.getJSONObject(i).getInt("Rack"));
//                t.setFila(jsonarreglo.getJSONObject(i).getInt("Fila"));
//                t.setColumna(jsonarreglo.getJSONObject(i).getInt("Columna"));
//                t.setProducto(jsonarreglo.getJSONObject(i).getString("Producto"));
//                t.setEnvase(jsonarreglo.getJSONObject(i).getString("Envase"));
//
//                t.setLote(jsonarreglo.getJSONObject(i).getInt("Lote"));
//                t.setCantidad(jsonarreglo.getJSONObject(i).getDouble("Cantidad"));
//                t.setUsuario(jsonarreglo.getJSONObject(i).getString("Usuario"));
//                t.setObservaciones(jsonarreglo.getJSONObject(i).getString("Observaciones"));
//                t.setFecha(jsonarreglo.getJSONObject(i).getString("Fecha"));
//                t.setHora(jsonarreglo.getJSONObject(i).getString("Hora"));
//                t.setTienda(jsonarreglo.getJSONObject(i).getString("Tienda"));
//
//                lista.add(t);
//            }
//            adaptadores = new ListaExistencias2(getActivity(), lista);
//            lista2.setAdapter(adaptadores);
//            ;
//
//
//
//        }catch (Exception e1){
//            e1.printStackTrace();
//        }
//
//        boton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObtenerResumenExistencias();
//            }
//        });
//    }
//



}
