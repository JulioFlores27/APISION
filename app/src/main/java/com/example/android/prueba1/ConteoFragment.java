package com.example.android.prueba1;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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


public class ConteoFragment extends Fragment {


    Main5Activity adaptadores;
    SwipeRefreshLayout swipere;
    ListView lista2;
    AsyncHttpClient cliente;
    Button boton1, boton2, btn_checar_ubicacion;
    EditText matepri2;
    EditText ubicacion;
    EditText lote, envase, cantidad;
    String usuario;
    TextView envase_tv;

    String tienda;

    //ArrayAdapter a;

    //Usuario
    private FirebaseAuth mAuth;

//    Handler customHandler = new Handler();
//    public class contar extends CountDownTimer {
//        public contar(long milienfuturo, long countdowninterval){
//            super(milienfuturo,countdowninterval);
//        }
//        @Override
//        public void onTick(long millisUntilFinished) {
//            if(matepri2.getText().toString().isEmpty()){
//                ObtenerAlmacen();
//            }else{
//                customHandler.removeCallbacks(actualizartimer);
//                customHandler.removeCallbacks(actualizartimer);
//            }
//        }
//        public void onFinish(){
//            //Toast.makeText(getActivity(), "Actualizado",Toast.LENGTH_SHORT).show();
//        }
//    }
//    private Runnable actualizartimer = new Runnable() {
//        @Override
//        public void run() {
//            contar tiempo = new contar(5000, 5000);
//            if(matepri2.getText().toString().isEmpty()) {
//                tiempo.start();
//                customHandler.postDelayed(this, 1000);
//            }else{
//                tiempo.cancel();
//            }
//        }
//    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View conteo = inflater.inflate(R.layout.fragment_conteo, container, false);
        lista2 = (ListView) conteo.findViewById(R.id.listaconteo);
        boton1 = (Button) conteo.findViewById(R.id.b12conteo);
        boton2 = (Button) conteo.findViewById(R.id.b22conteo);
        //customHandler.postDelayed(actualizartimer, 1000);

        //Usuario
        mAuth = FirebaseAuth.getInstance();

        //Tienda
        tienda = getString(R.string.Tienda);

        //TODO decidir si se queda o se va el botón de checar
        btn_checar_ubicacion = (Button) conteo.findViewById(R.id.btn_checar_ubicacion);
        btn_checar_ubicacion.setVisibility(View.INVISIBLE);

        matepri2 = (EditText) conteo.findViewById(R.id.matpri3);
        matepri2.setVisibility(View.INVISIBLE);
        ubicacion = (EditText) conteo.findViewById(R.id.ubicacion_conteo);
        lote = (EditText) conteo.findViewById(R.id.lote3);
        lote.setVisibility(View.INVISIBLE);

        envase_tv = (TextView) conteo.findViewById(R.id.envase3_textview);
        envase_tv.setVisibility(View.INVISIBLE);

        envase = (EditText) conteo.findViewById(R.id.envase3);
        envase.setVisibility(View.INVISIBLE);

        cantidad = (EditText) conteo.findViewById(R.id.cantidad_conteo);
        cantidad.setVisibility(View.INVISIBLE);
        swipere = conteo.findViewById(R.id.swiperefrescar);
        cliente = new AsyncHttpClient();
        //usuario = "Javier Belausteguigoitia";
        //usuario = "Danya López";
        //usuario = "Tablet";
        //usuario = "Tablet2";
        //usuario = "Edgar Cruz";
        //usuario = "Juan Antonio Muñoz";
        //usuario = "Edgar Gallardo";

        usuario = mAuth.getCurrentUser().getEmail().toString();

        ObtenerAlmacen();
        return conteo;
    }

    private void ObtenerAlmacen(){
        if (matepri2.getText().toString().isEmpty()) {

//            //Prueba
//            String url = "https://appsionmovil.000webhostapp.com/Almacenmp.php";

            //Real
            String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios.php"+"?Tienda="+tienda;


            cliente.post(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) { listaralmacen(new String(responseBody)); }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
            });
        }else{
            CharSequence buscar;
            String buscar1 = matepri2.getText().getFilters().toString();

//            //Prueba
//            String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_consultar.php?MateriaPrima="+ buscar1;

            //Real
            String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_consultar.php?MateriaPrima="+ buscar1 +"&Tienda="+tienda;


            cliente.post(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) { listaralmacen(new String(responseBody)); }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
            });
        }
    }

    private void ObtenerAlmacen3(){

        String buscar2 = matepri2.getText().toString();
        String ubicacion_array [] = ubicacion.getText().toString().split("-");
        String rack = ubicacion_array [0];
        String fila = ubicacion_array [1];
        String columna = ubicacion_array [2];

//
//        //Prueba
//        String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_consultar3.php?MateriaPrima="+ buscar2+"&rack="+rack+"&fila="+fila +"&columna="+columna;

        //Real
        final String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_consultar_ubicacion.php?MateriaPrima="+ buscar2+"&Rack="+rack+"&Fila="+fila +"&Columna="+columna+"&Tienda="+tienda;



        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) { listaralmacen(new String(responseBody));

                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
        });

    }

    private void InsertarConteo(){
        String obs = "Conteo";
        Date fechahora = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String dias = dateFormat.format(fechahora);
        String currentString = ubicacion.getText().toString();
        String[] separated = currentString.split("-");
        String a = separated[0];
        String b = separated[1];
        String c = separated[2];

//        //Prueba https://appsionmovil.000webhostapp.com/AlmacenMP_consultar3
//
//        String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_insertar_conteo.php?Rack="+ a.replaceAll(" ", "%20") +
//                "&Fila="+ b.replaceAll(" ", "%20") +
//                "&Columna="+ c.replaceAll(" ", "%20") +
//                "&MateriaPrima="+ matepri2.getText().toString().replaceAll(" ", "%20") +
//                "&LoteMP="+ lote.getText().toString().replaceAll(" ", "%20") +
//                "&Envase="+ envase.getText().toString().replaceAll(" ", "%20") +
//                "&Cantidad="+ cantidad.getText().toString().replaceAll(" ", "%20") +
//                "&Persona=" + usuario.replaceAll(" ", "%20") +
//                "&Observaciones=" + obs.replaceAll(" ", "%20") +
//                "&FechayHora=" + dias.replaceAll(" ", "%20")+
//                ;

        //Real

        String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_insertar_conteo.php?Rack="+ a.replaceAll(" ", "%20") +
                "&Fila="+ b.replaceAll(" ", "%20") +
                "&Columna="+ c.replaceAll(" ", "%20") +
                "&MateriaPrima="+ matepri2.getText().toString().replaceAll(" ", "%20") +
                "&LoteMP="+ lote.getText().toString().replaceAll(" ", "%20") +
                "&Envase="+ envase.getText().toString().replaceAll(" ", "%20") +
                "&Cantidad="+ cantidad.getText().toString().replaceAll(" ", "%20") +
                "&Persona=" + usuario.replaceAll(" ", "%20") +
                "&Observaciones=" + obs.replaceAll(" ", "%20") +
                "&FechayHora=" + dias.replaceAll(" ", "%20")+
                "&Tienda=" + tienda
                ;
        lista2.setVisibility(View.INVISIBLE);

        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) { if(statusCode == 200){
                Toast.makeText(getActivity(), "Sus datos se han guardado",Toast.LENGTH_SHORT).show();
                lista2.setVisibility(View.VISIBLE);
            } }
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
            adaptadores = new Main5Activity(getActivity(), lista);
            lista2.setAdapter(adaptadores);
            matepri2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adaptadores.getFilter().filter(s);
                }
                @Override
                public void afterTextChanged(Editable s) { }
            });
            swipere.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    if (ubicacion.getText().toString().isEmpty()) {
                        ObtenerAlmacen();
                        swipere.setRefreshing(false);
                    } else {

                        if (ubicacion.getText().toString().matches(".-.-.") || ubicacion.getText().toString().matches("..-.-.") || ubicacion.getText().toString().matches(".-.-..")|| ubicacion.getText().toString().matches("..-.-..")) {

                            ObtenerAlmacen3();
                            ConnectivityManager conectividad1 = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo lanets = conectividad1.getActiveNetworkInfo();
                            if (lanets != null && lanets.isConnected()) {
                                matepri2.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        adaptadores.getFilter().filter(s);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });

                                boton1.setVisibility(View.VISIBLE);
                                boton2.setVisibility(View.VISIBLE);
                                swipere.setRefreshing(false);
                            } else {
                                Toast.makeText(getActivity(), "No hay Internet, intentarlo más tarde o verifica su conexión", Toast.LENGTH_SHORT).show();
                                boton1.setVisibility(View.INVISIBLE);
                                boton2.setVisibility(View.INVISIBLE);
                                swipere.setRefreshing(false);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Revisar ubicación", Toast.LENGTH_LONG).show();
                            swipere.setRefreshing(false);

                        }
                    }
                }

            });

            lista2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {



                    final Almacen valor1 = (Almacen) lista2.getItemAtPosition(position);
                    final AlertDialog.Builder mibuild = new AlertDialog.Builder(getActivity());
                    final View mview = getLayoutInflater().inflate(R.layout.dialogo_conteo, null);
                    mibuild.setTitle("Corregir");

                    final String rak = String.valueOf(valor1.getRack());
                    final String fil = String.valueOf(valor1.getFila());
                    final String col = String.valueOf(valor1.getColumna());
                    final String ubicacion_prueba = rak +"-"+ fil +"-"+col;
                    final String mp = valor1.getMateriaprima();
                    final String lmp = String.valueOf(valor1.getLotemp());
                    final Double primervalor = valor1.getCantidad();
                    final String per = valor1.getPersona();
                    final String env = valor1.getEnvase();
                    final String tie = valor1.getTienda();

                    final String cprod = "Editado conteo";
                    final String val3 = String.valueOf(primervalor);
                    final String valor = String.valueOf(primervalor);

                    final EditText cantidad_editada = mview.findViewById(R.id.cantidad_editar_conteo);
                    final EditText ubicacion_editada = mview.findViewById(R.id.ubicacion_editar_conteo);
                    final EditText lote_editado = mview.findViewById(R.id.lote_editar_conteo);

                    //final Button eliminar_conteo_btn = (Button) mview.findViewById(R.id.btn_eliminar_conteo);



                    ubicacion_editada.setText(ubicacion_prueba);
                    lote_editado.setText(lmp);

                    cantidad_editada.setText(valor);
                    if (primervalor == 0) {
                        Toast.makeText(getActivity(), "Esta Operación se ha terminado", Toast.LENGTH_SHORT).show();
                    } else {
                        final AlertDialog.Builder builder = mibuild.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String cantidad_nueva = cantidad_editada.getText().toString();

                                cantidad_editada.setText(cantidad_nueva);
                                //ubicacion_editada.setText(ubicacion_prueba);

                                //op2.setText(ubicacion_nueva);
                                String ubibacion_nueva = ubicacion_editada.getText().toString();
                                String lote_nuevo = lote_editado.getText().toString();


                                String[] ubicacion = ubibacion_nueva.split("-");
                                String rack_nuevo = ubicacion[0];
                                String fila_nueva = ubicacion[1];
                                String columna_nueva = ubicacion[2];



                                Date fechahora = Calendar.getInstance().getTime();
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
                                String dias = dateFormat.format(fechahora);
                                String val1 = cantidad_editada.getText().toString();
                                Double valor_negativo = Double.parseDouble(val1)*-1;
                                Double valor_anterior = primervalor*-1;


                                String valor_neg_string = valor_negativo.toString();
                                Double val2 = Double.parseDouble(val1);
                                Double restar = primervalor - val2;
                                String total = String.valueOf(restar);
                                lista2.setVisibility(View.INVISIBLE);
                                if (val2 == 0) {
                                    Toast.makeText(getActivity(), "Es un valor nulo", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    lista2.setVisibility(View.VISIBLE);
                                } else {

                                    //Prueba

                                    //Real

                                    final String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_editar_conteo.php?Rack=" + rak +
                                            "&Fila=" + fil + "&Columna="+ col +"&MateriaPrima="+ mp.replaceAll(" ","%20")+
                                            "&LoteMP="+ lmp + "&Envase=" + env +"&Cantidad="+ valor_anterior +"&Persona="+ usuario.replaceAll(" ", "%20")+
                                            "&Observaciones="+ cprod.replaceAll(" ","%20")+
                                            "&FechayHora="+ dias.replaceAll(" ","%20")+"&RackNuevo="+rack_nuevo+"&FilaNueva="+fila_nueva+"&ColumnaNueva="+columna_nueva+"&CantidadNueva="+val1 + "&LoteNuevo=" + lote_nuevo +"&Tienda=" + tie;
//                                    ObtenerAlmacen3();
                                    cliente.post(url, new AsyncHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) { if (statusCode == 200) {
                                            ObtenerAlmacen();
                                            Toast.makeText(getActivity(),"Información actualizada",Toast.LENGTH_SHORT).show();
                                            lista2.setVisibility(View.VISIBLE);
                                        } }
                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
                                    });


                                }

                            }
                        });

                        mibuild.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });


                        mibuild.setNegativeButton("Eliminar registro", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //////////

                                Date fechahora = Calendar.getInstance().getTime();
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
                                String dias = dateFormat.format(fechahora);
                                double segundovalor = primervalor*-1;

//                                //Prueba
//                                final String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_CantidadOperacion.php?Rack=" + rak +
//                                        "&Fila=" + fil + "&Columna="+ col +"&MateriaPrima="+ mp.replaceAll(" ","%20")+
//                                        "&LoteMP="+ lmp + "&Envase=" + env +"&Cantidad="+ segundovalor +"&Persona="+ usuario.replaceAll(" ", "%20")+
//                                        "&Observaciones="+ "Eliminado conteo".replaceAll(" ","%20")+
//                                        "&FechayHora="+ dias.replaceAll(" ","%20");


                                //Real

                                final String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_anular_conteo.php?Rack=" + rak +
                                        "&Fila=" + fil + "&Columna="+ col +"&MateriaPrima="+ mp.replaceAll(" ","%20")+
                                        "&LoteMP="+ lmp + "&Envase=" + env +"&Cantidad="+ segundovalor +"&Persona="+ usuario.replaceAll(" ", "%20")+
                                        "&Observaciones="+ "Eliminado conteo".replaceAll(" ","%20")+
                                        "&FechayHora="+ dias.replaceAll(" ","%20")+
                                        "&Tienda="+ tie.replaceAll(" ","%20");


                                lista2.setVisibility(View.INVISIBLE);
                                cliente.post(url, new AsyncHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) { if (statusCode == 200) {

                                        Toast.makeText(getActivity(),"Información actualizada",Toast.LENGTH_SHORT).show();
                                        ObtenerAlmacen();
                                        //dialog.cancel();
                                        mibuild.setView(mview);
                                        AlertDialog dia = mibuild.create();
                                        dia.cancel();
                                        lista2.setVisibility(View.VISIBLE);
                                    } }
                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
                                });


                                /////////

                                dialog.cancel(); }
                        });


                        mibuild.setView(mview);
                        AlertDialog dialog = mibuild.create();
                        dialog.show();
                    }
                    return false;
                }

            });



            boton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if(matepri2.getText().toString().isEmpty() || lote.getText().toString().isEmpty() || cantidad.getText().toString().isEmpty() || ubicacion.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Favor de ingresar datos",Toast.LENGTH_SHORT).show();
                    }

                    else {


                        if (matepri2.getText().toString().length() < 5 || matepri2.getText().toString().length() > 25) {
                            Toast.makeText(getActivity(), "Revisar MP", Toast.LENGTH_SHORT).show();

                        }

                        if ((lote.getText().toString().length() < 5 && lote.getText().toString().length() > 1) || lote.getText().toString().length() > 10) {
                            Toast.makeText(getActivity(), "Revisar lote", Toast.LENGTH_SHORT).show();

                        }


                        if ((lote.getText().toString().length() == 10 || lote.getText().toString().length() == 5) && (cantidad != null) && (matepri2.getText().toString().length() == 5 || matepri2.getText().toString().length() > 14) && (ubicacion.getText().toString().matches(".-.-.") || ubicacion.getText().toString().matches("..-.-.") || ubicacion.getText().toString().matches(".-.-..") || ubicacion.getText().toString().matches("..-.-.."))) {

                            InsertarConteo();

                            ubicacion.setText("");
                            ubicacion.setVisibility(View.INVISIBLE);

                            matepri2.setText("");
                            matepri2.setVisibility(View.INVISIBLE);
                            lote.setText("");
                            lote.setVisibility(View.INVISIBLE);

                            envase_tv.setVisibility(View.INVISIBLE);
                            envase.setText("");
                            envase.setVisibility(View.INVISIBLE);

                            cantidad.setText("");
                            cantidad.setVisibility(View.INVISIBLE);

                            ObtenerAlmacen();
                            ubicacion.setVisibility(View.VISIBLE);
                            ubicacion.requestFocus();

                        } else {

                            Toast.makeText(getActivity(), "Revisar ubicación", Toast.LENGTH_SHORT).show();


                        }
                    }
                }
            });


            boton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(matepri2.getText().toString().isEmpty() || lote.getText().toString().isEmpty() || cantidad.getText().toString().isEmpty() || ubicacion.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Favor de ingresar datos",Toast.LENGTH_SHORT).show();
                    }

                    else {


                        if (matepri2.getText().toString().length() < 5 || matepri2.getText().toString().length() > 25) {
                            Toast.makeText(getActivity(), "Revisar MP", Toast.LENGTH_SHORT).show();
                            matepri2.requestFocus();

                        }

                        if ((lote.getText().toString().length() < 5 && lote.getText().toString().length() >1) || lote.getText().toString().length() > 10) {
                            Toast.makeText(getActivity(), "Revisar lote", Toast.LENGTH_SHORT).show();
                            lote.requestFocus();

                        }


                        if ((lote.getText().toString().length() == 10 || lote.getText().toString().length() == 5) && (matepri2.getText().toString().length() == 5 || matepri2.getText().toString().length() > 14) && (ubicacion.getText().toString().matches(".-.-.") || ubicacion.getText().toString().matches("..-.-.") || ubicacion.getText().toString().matches(".-.-..") || ubicacion.getText().toString().matches("..-.-.."))) {

                            InsertarConteo();
                            matepri2.setText("");
                            lote.setText("");
                            lote.setVisibility(View.INVISIBLE);

                            envase_tv.setVisibility(View.INVISIBLE);
                            envase.setText("");
                            envase.setVisibility(View.INVISIBLE);

                            cantidad.setText("");
                            cantidad.setVisibility(View.INVISIBLE);
                            ObtenerAlmacen3();
                            matepri2.requestFocus();

                        } else {

                            Toast.makeText(getActivity(), "Revisar datos", Toast.LENGTH_SHORT).show();


                        }
                    }
                }
            });

            matepri2.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_ENTER) {

                        String codigo = matepri2.getText().toString();
                        int guiones_codigo = codigo.length() - codigo.replace("-", "").length();

                        if (guiones_codigo == 3 && codigo.length()>17) {




                        if (matepri2.getText().toString().length() < 4) {
                            Toast.makeText(getActivity(), "Revisar MP/Producto", Toast.LENGTH_SHORT).show();
                            matepri2.requestFocus();

                        }

                        if (matepri2.getText().toString().length() == 5) {
                            lote.setVisibility(View.VISIBLE);

                        }

                        //ToDo revisar el de abajo

                        if (matepri2.getText().toString().length() > 6 && matepri2.getText().toString().length() < 24) {
                            Toast.makeText(getActivity(), "Error en código", Toast.LENGTH_SHORT).show();
                            matepri2.setText("");
                            matepri2.requestFocus();

                        }

                        if (matepri2.getText().toString().length() > 24) {
                            String[] partes_codigo = matepri2.getText().toString().split("-");
                            String mp_codigo = partes_codigo[0];
                            String lote_codigo = partes_codigo[3].trim();

                            lote.setVisibility(View.VISIBLE);
                            cantidad.setVisibility(View.VISIBLE);

                            matepri2.setText(mp_codigo);
                            lote.setText(lote_codigo);
                            lote.requestFocus();


                        } else {

                        }
                    }

                    if (codigo.length()==5 || (codigo.length()<19 && codigo.length()>14) && guiones_codigo<4 ){
                        lote.setVisibility(View.VISIBLE);
                    }

                    if ((codigo.length()<19 && codigo.length()>14) && guiones_codigo<4 ){
                            lote.setVisibility(View.VISIBLE);
                            lote.requestFocus();
                            envase.setVisibility(View.VISIBLE);
                            envase_tv.setVisibility(View.VISIBLE);
                        }



                    if (guiones_codigo > 4) {


                            String[] partes_codigo_pt = matepri2.getText().toString().split("-");
                            String mp_codigo = partes_codigo_pt[0]+"-"+partes_codigo_pt[1]+"-"+partes_codigo_pt[2];
                            String lote_codigo = partes_codigo_pt[4].trim();
                            String envase_codigo = partes_codigo_pt[3];

                            lote.setVisibility(View.VISIBLE);
                            cantidad.setVisibility(View.VISIBLE);

                            matepri2.setText(mp_codigo);
                            lote.setText(lote_codigo);
                            envase_tv.setVisibility(View.VISIBLE);
                            envase.setVisibility(View.VISIBLE);
                            envase.setText(envase_codigo);

                            lote.requestFocus();


                        }

                        else {

                           // Toast.makeText(getActivity(), "Revisar datos", Toast.LENGTH_SHORT).show();

                        }

                }

                    return false;
                }
            });

            envase.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {


                    if (keyCode == KeyEvent.KEYCODE_ENTER){

                            cantidad.setVisibility(View.VISIBLE);

                    }

                    return false;
                }
            });

            lote.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_ENTER){


                        if (lote.getText().length()==5 || lote.getText().length()==10){
                            cantidad.setVisibility(View.VISIBLE);
                        }

                        if (lote.getText().length()==5){
//                            lote.requestFocus();
//                            envase.requestFocus();
                            matepri2.requestFocus();
                        }

                        else {
                           // Toast.makeText(getActivity(),"Revisar lote",Toast.LENGTH_SHORT).show();
                        }
                    }

                    return false;
                }
            });

            ubicacion.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_ENTER){

                        if (ubicacion.getText().toString().isEmpty()) {
                            ObtenerAlmacen();
                            swipere.setRefreshing(false);
                        } else {

                            if (ubicacion.getText().toString().matches(".-.-.") || ubicacion.getText().toString().matches("..-.-.") || ubicacion.getText().toString().matches(".-.-..")|| ubicacion.getText().toString().matches("..-.-..")) {

                                ObtenerAlmacen3();
                                matepri2.setVisibility(View.VISIBLE);
                                ConnectivityManager conectividad1 = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo lanets = conectividad1.getActiveNetworkInfo();
                                if (lanets != null && lanets.isConnected()) {
                                    matepri2.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            adaptadores.getFilter().filter(s);
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {
                                        }
                                    });

                                    boton1.setVisibility(View.VISIBLE);
                                    boton2.setVisibility(View.VISIBLE);
                                    swipere.setRefreshing(false);
                                } else {
                                    Toast.makeText(getActivity(), "No hay Internet, intentarlo más tarde o verifica su conexión", Toast.LENGTH_SHORT).show();
                                    boton1.setVisibility(View.INVISIBLE);
                                    boton2.setVisibility(View.INVISIBLE);
                                    swipere.setRefreshing(false);
                                }
                            } else {
                                Toast.makeText(getActivity(), "Revisar ubicación", Toast.LENGTH_LONG).show();
                                swipere.setRefreshing(false);
                            }
                        }

                    }

                    return false;
                }
            });


            btn_checar_ubicacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ubicacion.getText().toString().isEmpty()) {
                        ObtenerAlmacen();
                        swipere.setRefreshing(false);
                    } else {

                        if (ubicacion.getText().toString().matches(".-.-.") || ubicacion.getText().toString().matches("..-.-.") || ubicacion.getText().toString().matches(".-.-..")|| ubicacion.getText().toString().matches("..-.-..")) {

                            ObtenerAlmacen3();
                            ConnectivityManager conectividad1 = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo lanets = conectividad1.getActiveNetworkInfo();
                            if (lanets != null && lanets.isConnected()) {
                                matepri2.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        adaptadores.getFilter().filter(s);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });

                                boton1.setVisibility(View.VISIBLE);
                                boton2.setVisibility(View.VISIBLE);
                                swipere.setRefreshing(false);
                            } else {
                                Toast.makeText(getActivity(), "No hay Internet, intentarlo más tarde o verifica su conexión", Toast.LENGTH_SHORT).show();
                                boton1.setVisibility(View.INVISIBLE);
                                boton2.setVisibility(View.INVISIBLE);
                                swipere.setRefreshing(false);
                            }
                        } else {

                            swipere.setRefreshing(false);
                        }
                    }


                }
            });




        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
