package com.example.android.prueba1;


import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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

import com.google.firebase.auth.FirebaseAuth;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import cz.msebera.android.httpclient.Header;

public class SalidasFragment extends Fragment {
    Main5Activity adaptadores;
    SwipeRefreshLayout swipere;
    ListView lista2;
    AsyncHttpClient cliente;
    //Button boton1, boton2;
    EditText matepri2;
    EditText conpro2;
    String usuario;

    String tienda;

    //Usuario
    private FirebaseAuth mAuth;

    //Botón para actualizar
    Button btn_actualizar;

    ArrayAdapter a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View observar = inflater.inflate(R.layout.fragment_salidas, container, false);
        lista2 = (ListView) observar.findViewById(R.id.listasa);
       // boton1 = (Button) observar.findViewById(R.id.b12);
        //boton2 = (Button) observar.findViewById(R.id.b22);
        matepri2 = (EditText) observar.findViewById(R.id.matpri2);
        conpro2 = (EditText) observar.findViewById(R.id.conprod2);

        //Para que no se trabe
       // swipere = observar.findViewById(R.id.swiperefrescar);
        cliente = new AsyncHttpClient();

        //Usuario
        mAuth = FirebaseAuth.getInstance();

        tienda = getString(R.string.Tienda);

        btn_actualizar = (Button) observar.findViewById(R.id.btn_buscar_salidas);

        //usuario = "Javier Belausteguigoitia";
        //usuario = "Danya López";
        //usuario = "Tablet";
        //usuario = "Tablet2";
        //usuario = "Edgar Cruz";
        //usuario = "Juan Antonio Muñoz";
        //usuario = "Edgar Gallardo";

        usuario = mAuth.getCurrentUser().getEmail().toString();

        ObtenerAlmacen();
        return observar;
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

    private void ObtenerAlmacen1 (){

        String buscar1 = matepri2.getText().getFilters().toString();
        //Toast.makeText(getActivity(), buscar, Toast.LENGTH_SHORT).show();


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

    private void ObtenerAlmacen2(){

        String buscar2 = matepri2.getText().toString();

        //        //Prueba
//        String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_consultar2.php?MateriaPrima="+ buscar2;

        //Real
        String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_buscar_por_producto.php?MateriaPrima="+ buscar2+"&Tienda="+tienda;



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
                t.setLotemp(jsonarreglo.getJSONObject(i).getInt("LoteMP"));
                t.setTienda(jsonarreglo.getJSONObject(i).getString("Tienda"));
                t.setEnvase(jsonarreglo.getJSONObject(i).getString("Envase"));
                t.setCantidad(jsonarreglo.getJSONObject(i).getDouble("Cantidad"));
                t.setPersona(jsonarreglo.getJSONObject(i).getString("Persona"));
                t.setObservaciones(jsonarreglo.getJSONObject(i).getString("Observaciones"));
                t.setFechahora(jsonarreglo.getJSONObject(i).getString("FechayHora"));
                lista.add(t);
            }
            adaptadores = new Main5Activity(getActivity(), lista);
            lista2.setAdapter(adaptadores);

            matepri2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    ObtenerAlmacen1();
                    return false;
                }
            });
            ///////////////////////////////////
            //Probar con botón para que no se trabe


//            matepri2.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    //ObtenerAlmacen2();
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    ObtenerAlmacen2();
//                    adaptadores.getFilter().filter(s);
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    //ObtenerAlmacen2();
//                }
//            });

            ///////////////////////////////////////

            btn_actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ObtenerAlmacen2();
                }
            });

            //ToDo Modificar para que se pueda mover la lista en las dos direcciones

//            swipere.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    ObtenerAlmacen2();
//                    ConnectivityManager conectividad1 = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//                    NetworkInfo lanets = conectividad1.getActiveNetworkInfo();
//                    if(lanets != null && lanets.isConnected()) {
//                        matepri2.addTextChangedListener(new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//                            @Override
//                            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                                ObtenerAlmacen2();
//                                adaptadores.getFilter().filter(s);
//
//                            }
//                            @Override
//                            public void afterTextChanged(Editable s) { }
//                        });
//                        //ObtenerAlmacen();
//                        //boton1.setVisibility(View.VISIBLE);
//                        //boton2.setVisibility(View.VISIBLE);
//                        swipere.setRefreshing(false);
//                    }else{
//                        Toast.makeText(getActivity(), "No hay Internet, intentarlo más tarde o verifica su conexión",Toast.LENGTH_SHORT).show();
//                        //boton1.setVisibility(View.INVISIBLE);
//                        //boton2.setVisibility(View.INVISIBLE);
//                        swipere.setRefreshing(false);
//                    }
//                }
//            });

            /////////////

            lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Almacen valor1 = (Almacen) lista2.getItemAtPosition(position);
                    AlertDialog.Builder mibuild = new AlertDialog.Builder(getActivity());
                    final View mview = getLayoutInflater().inflate(R.layout.operacion, null);
                    mibuild.setTitle("Cantidades Restantes");
                    mibuild.setMessage("Surtido:");
                    final String rak = String.valueOf(valor1.getRack());
                    final String fil = String.valueOf(valor1.getFila());
                    final String col = String.valueOf(valor1.getColumna());
                    final String mp = valor1.getMateriaprima();
                    final String lmp = String.valueOf(valor1.getLotemp());
                    final String env = valor1.getEnvase();
                    final String tie = valor1.getTienda();

                    final Double primervalor = valor1.getCantidad();
                    final String per = valor1.getPersona();
                    final String cprod = conpro2.getText().toString();
                    final String val3 = String.valueOf(primervalor);
                    if (primervalor == 0) {
                        Toast.makeText(getActivity(), "Esta Operación se ha terminado", Toast.LENGTH_SHORT).show();
                    }

                    if(conpro2.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Ingresar número de salida", Toast.LENGTH_SHORT).show();

                    }

//                    if(conpro2.getText().toString().length()<5 || conpro2.getText().toString().length()>6){
//                        Toast.makeText(getActivity(), "Revisar número de salida", Toast.LENGTH_SHORT).show();
//
//                    }

                    if(conpro2.getText().toString().length()<4 ){
                        Toast.makeText(getActivity(), "Revisar número de salida", Toast.LENGTH_SHORT).show();

                    }

                    else {
                        final AlertDialog.Builder builder = mibuild.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                                EditText op1 = (EditText) mview.findViewById(R.id.operacion1);
                                Date fechahora = Calendar.getInstance().getTime();
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
                                String dias = dateFormat.format(fechahora);
                                String val1 = op1.getText().toString();
                                Double val2 = Double.parseDouble(val1);
                                Double restar = primervalor - val2;
                                String total = String.valueOf(restar);
                                if (val2 == 0) {
                                    Toast.makeText(getActivity(), "Es un valor nulo", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                } else if (val2 <= primervalor) {

//                                    //Prueba
//
//                                    String url = "https://appsionmovil.000webhostapp.com/AlmacenMP_CantidadOperacion.php?Rack=" + rak +
//                                            "&Fila=" + fil + "&Columna="+ col +"&MateriaPrima="+ mp.replaceAll(" ","%20")+
//                                            "&LoteMP="+ lmp + "&Envase=" + env + "&Cantidad=-"+ val1 +"&Persona="+ usuario.replaceAll(" ", "%20")+
//                                            "&Observaciones="+ cprod.replaceAll(" ","%20")+
//                                            "&FechayHora="+ dias.replaceAll(" ","%20");

                                    //Real
                                    String url = "http://appsionmovil.hostingerapp.com/Real/Inventarios_salidas.php?Rack=" + rak +
                                            "&Fila=" + fil + "&Columna="+ col +"&MateriaPrima="+ mp.replaceAll(" ","%20")+
                                            "&LoteMP="+ lmp + "&Envase=" + env + "&Cantidad=-"+ val1 +"&Persona="+ usuario.replaceAll(" ", "%20")+
                                            "&Observaciones="+ cprod.replaceAll(" ","%20")+
                                            "&FechayHora="+ dias.replaceAll(" ","%20")+
                                            "&Tienda=" + tie;


                                    cliente.post(url, new AsyncHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) { if (statusCode == 200) {
                                            Toast.makeText(getActivity(), "Sus datos se han guardado",Toast.LENGTH_SHORT).show();
                                        } }
                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
                                    });

                                } else {
                                    Toast.makeText(getActivity(), "Favor de Ingresar una cantidad menor", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        mibuild.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
                        });
                        mibuild.setView(mview);
                        AlertDialog dialog = mibuild.create();
                        dialog.show();
                    }
                }
            });





        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
