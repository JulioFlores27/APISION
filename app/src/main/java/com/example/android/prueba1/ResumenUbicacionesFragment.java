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



public class ResumenUbicacionesFragment extends Fragment {

    ListaUbicacionesContadas adaptadores;

    ListView lista2;
    AsyncHttpClient cliente;
    Button boton1;

    String usuario;
    String tienda;



    //Usuario
    private FirebaseAuth mAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View conteo = inflater.inflate(R.layout.fragment_resumen_ubicaciones, container, false);
        lista2 = (ListView) conteo.findViewById(R.id.listaresumen);
        boton1 = (Button) conteo.findViewById(R.id.b1_resumen);

        //Usuario
        mAuth = FirebaseAuth.getInstance();

        tienda = getString(R.string.Tienda);

        cliente = new AsyncHttpClient();

        usuario = mAuth.getCurrentUser().getEmail().toString();

        ObtenerResumen();
        return conteo;
    }

    private void ObtenerResumen(){

//        //Prueba
//            String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_resumen_ubicaciones.php";

        //Real
        String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_resumen_ubicaciones.php"+"?Tienda="+tienda;


            cliente.post(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) { listaralmacen(new String(responseBody)); }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
            });



    }




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
                t.setTienda(jsonarreglo.getJSONObject(i).getString("Tienda"));

                t.setLotemp(jsonarreglo.getJSONObject(i).getInt("LoteMP"));
                t.setCantidad(jsonarreglo.getJSONObject(i).getDouble("Cantidad"));
                t.setPersona(jsonarreglo.getJSONObject(i).getString("Persona"));
                t.setObservaciones(jsonarreglo.getJSONObject(i).getString("Observaciones"));
                t.setFechahora(jsonarreglo.getJSONObject(i).getString("FechayHora"));
                lista.add(t);
            }
            adaptadores = new ListaUbicacionesContadas(getActivity(), lista);
            lista2.setAdapter(adaptadores);
           ;



        }catch (Exception e1){
            e1.printStackTrace();
        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista2.setVisibility(View.INVISIBLE);
                ObtenerResumen();
                lista2.setVisibility(View.VISIBLE);
            }
        });
    }
}
