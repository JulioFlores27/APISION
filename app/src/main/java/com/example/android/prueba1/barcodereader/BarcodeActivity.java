/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.prueba1.barcodereader;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.prueba1.Escaneo;
import com.example.android.prueba1.ExampleDialog;
import com.example.android.prueba1.MainActivity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.example.android.prueba1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.loopj.android.http.*;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class BarcodeActivity extends AppCompatActivity implements View.OnClickListener, ExampleDialog.ExampleDialogListener{

// use a compound button so either checkbox or switch widgets work.
//private CompoundButton autoFocus;
//private CompoundButton useFlash;
private Boolean autoFocus;
private Boolean useFlash;

private TextView statusMessage;
private TextView ubicacion;
private TextView barcodeValue;
private TextView producto;
private TextView envase;
private TextView lote;
private TextView consecutivo;
private EditText cantidad;

private Button teclear_ubicacion;
private Button teclear_producto;

//Para guardar en BD remota
// https://www.youtube.com/watch?v=TTIiDVQKCeg

private Button guardar_escaneo;
private Button guardar_tarima;
private ListView lvEscaneos;
private AsyncHttpClient cliente;
private ProgressDialog mLoginProgress;

//Agregar fecha y hora a la bd
Calendar calendar;
    private int mhour;
    private int mminute;

    //Current user

    private FirebaseAuth mAuth;

    //Intentar leer productos de un rack
    private TextView rack_valor;
    private TextView fila_valor;
    private TextView columna_valor;





private static final int RC_BARCODE_CAPTURE = 9001;
private static final String TAG = "BarcodeMain";

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        statusMessage = (TextView)findViewById(R.id.status_message);
        ubicacion = (TextView)findViewById(R.id.ubicación_barcode_value);

        barcodeValue = (TextView)findViewById(R.id.barcode_value);
        producto = (TextView)findViewById(R.id.producto_value);
        envase = (TextView)findViewById(R.id.envase_value);
        lote = (TextView)findViewById(R.id.lote_value);
        consecutivo = (TextView)findViewById(R.id.consecutivo_value);
        cantidad = (EditText)findViewById(R.id.cantidad_value);
        lvEscaneos = (ListView)findViewById(R.id.lvEscaneos);
        cliente = new AsyncHttpClient();
    mLoginProgress = new ProgressDialog(this);

    //Current user

    mAuth = FirebaseAuth.getInstance();

    //Rack para buscar
    rack_valor = (TextView) findViewById(R.id.rack_value);
    fila_valor = (TextView) findViewById(R.id.fila_value);
    columna_valor = (TextView) findViewById(R.id.columna_value);

    obtenerProductos();

        //botonAlmacenar();


//        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
//        useFlash = (CompoundButton) findViewById(R.id.use_flash);
        autoFocus = true;
        useFlash = false;
//Botones escanear
        findViewById(R.id.read_barcode).setOnClickListener(this);
        findViewById(R.id.ubicacion_read_barcode).setOnClickListener(this);
//Botones ingresar con teclado
    teclear_ubicacion = (Button) findViewById(R.id.teclear_barcode);
    teclear_producto = (Button) findViewById(R.id.ubicacion_teclear_barcode);

    //Botones guardar

    guardar_escaneo = (Button) findViewById(R.id.btn_guardar_escaneo);
    guardar_tarima = (Button) findViewById(R.id.btn_guardar_tarima);

    //Acciones

    teclear_ubicacion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDialog();

            if (ubicacion.getText().toString().isEmpty()){

            }else {

            }

        }
    });
    teclear_producto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDialog();

        }
    });

    guardar_escaneo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (cantidad.getText().toString().isEmpty()){
                Toast.makeText(BarcodeActivity.this, "Debes agregar la cantidad de envases", Toast.LENGTH_SHORT).show();
            }else {
            set_datos_escaneo();
        }}
    });

    guardar_tarima.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ubicacion.setText("");

            rack_valor.setText("");
            fila_valor.setText("");
            columna_valor.setText("");
            set_datos_escaneo();
            obtenerProductos();

        }
    });

        }

    private void set_datos_escaneo() {


        //TODO agregar los demás
        if (ubicacion.getText().toString().isEmpty()){
            Toast.makeText(BarcodeActivity.this, "Debes tener una ubicación",Toast.LENGTH_SHORT).show();
        } else{
            Escaneo e = new Escaneo();


            calendar = Calendar.getInstance();
            mhour = calendar.get(Calendar.HOUR);
            mminute = calendar.get(Calendar.MINUTE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
            Date currentTime = Calendar.getInstance().getTime();
            String hora = currentTime.toString();
            String ubicacion_db = ((TextView) findViewById(R.id.ubicación_barcode_value)).getText().toString();






            String producto_db="";
            String descripcion_producto_db="";
            String envase_db ="";
            String lote_db = "";
            String consecutivo_db ="";

            if(barcodeValue.getText().toString().isEmpty()){



            }else{
                producto_db = ((TextView) findViewById(R.id.producto_value)).getText().toString();
                envase_db = ((TextView) findViewById(R.id.envase_value)).getText().toString();
                lote_db = ((TextView) findViewById(R.id.lote_value)).getText().toString();
                consecutivo_db = ((TextView) findViewById(R.id.consecutivo_value)).getText().toString();
                descripcion_producto_db="Polyner 75";
            }







            int cantidad_db = 0;

            if (cantidad.getText().toString().isEmpty()){
                cantidad_db = 0;
            }else {
                cantidad_db = (Integer.parseInt(cantidad.getText().toString()));
            }







            String [] hora_parts = hora.split(" ");
            String hora_part1 = hora_parts[0];
            String hora_part2 = hora_parts[1];
            String hora_part3 = hora_parts[2];
            String hora_part4 = hora_parts[3];
            String hora_part5 = hora_parts[4];
            String hora_part6 = hora_parts[5];


            String hora_db = hora_part4;
            String fecha_db = hora_part3+"/"+hora_part2+"/"+hora_part6;

            //Ubicación desglosada

            String [] ubicacion_parts = ubicacion_db.split("-");
            String ubicacion_part1 = ubicacion_parts[0];
            String ubicacion_part2 = ubicacion_parts[1];
            String ubicacion_part3 = ubicacion_parts[2];

            String current_user_db = mAuth.getCurrentUser().getEmail().toString();








//                e.setID_INVENTARIOMES("00000003");
            e.setID_USUARIOMES(current_user_db);
            e.setFECHAMES(fecha_db);



            e.setHORAMES(hora_db);
            e.setRACKMES(Integer.parseInt(ubicacion_part1));
            e.setFILAMES(Integer.parseInt(ubicacion_part2));
            e.setCOLUMNAMES(Integer.parseInt(ubicacion_part3));
            e.setID_PRODUCTOMES(producto_db);
            e.setDESCRIPCION_PRODUCTOMES(descripcion_producto_db.replaceAll(" ","%20"));
            e.setID_ENVASEMES(envase_db);
            e.setENVASEMES("Bote");
            e.setLOTEMES(lote_db);
            e.setCAPACIDADMES("4");
            e.setCONSECUTIVOMES(consecutivo_db);
            e.setCANTIDADMES(cantidad_db);

            Toast.makeText(BarcodeActivity.this,ubicacion_part1,Toast.LENGTH_SHORT).show();

            mLoginProgress.setTitle("Guardando producto");
            mLoginProgress.setMessage("Por favor espera");
            mLoginProgress.setCanceledOnTouchOutside(false);
            mLoginProgress.show();

            agregarEscaneo(e);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            obtenerProductos();

        }

    }




    private void agregarEscaneo (Escaneo e){
            String url = "https://appsionmovil.000webhostapp.com/agregar_inventario.php?";
            String parametros =
//                    "ID_INVENTARIOMES=" + e.getID_INVENTARIOMES() + "&ID_USUARIOMES="
                            "ID_USUARIOMES="+ e.getID_USUARIOMES() + "&FECHAMES=" + e.getFECHAMES()
                    + "&HORAMES=" + e.getHORAMES() + "&RACKMES=" + e.getRACKMES() + "&FILAMES=" + e.getFILAMES() + "&COLUMNAMES=" + e.getCOLUMNAMES() + "&ID_PRODUCTOMES=" + e.getID_PRODUCTOMES() +
                    "&DESCRIPCION_PRODUCTOMES=" + e.getDESCRIPCION_PRODUCTOMES() + "&ID_ENVASEMES=" + e.getID_ENVASEMES() + "&ENVASEMES="+e.getENVASEMES()+ "&LOTEMES=" + e.getLOTEMES()
                    + "&CAPACIDADMES=" + e.getCAPACIDADMES() + "&CONSECUTIVOMES=" + e.getCONSECUTIVOMES() + "&CANTIDADMES=" + e.getCANTIDADMES();
            cliente.post(url + parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if(statusCode == 200){
                        Toast.makeText(BarcodeActivity.this,"Escaneo agregado", Toast.LENGTH_SHORT).show();

                        barcodeValue.setText("");
                        producto.setText("");
                        envase.setText("");
                        lote.setText("");
                        consecutivo.setText("");
                        cantidad.setText("");
                        mLoginProgress.dismiss();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }


        //Llenar listview

    public void obtenerProductos(){

    String rack_php="0";
    String fila_php="0";
    String columna_php="0";

    if (rack_valor.equals("")){

    }else{
        rack_php=rack_valor.getText().toString();
        fila_php=fila_valor.getText().toString();
        columna_php=columna_valor.getText().toString();
    }

    String url ="https://appsionmovil.000webhostapp.com/obtener_datos_inventario.php?RACKMES="+rack_php+"&FILAMES="+fila_php+"&COLUMNAMES="+columna_php;
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

    }

    private  void listarProductos(String respuesta){
        final ArrayList <Escaneo> lista = new ArrayList<Escaneo>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i = 0; i<jsonArreglo.length(); i++){
                Escaneo e =new Escaneo();
                //e.setID_INVENTARIOMES(jsonArreglo.getJSONObject(i).getInt("ID_INVENTARIOMES"));
                e.setID_USUARIOMES(jsonArreglo.getJSONObject(i).getString("ID_USUARIOMES"));
                e.setFECHAMES(jsonArreglo.getJSONObject(i).getString("FECHAMES"));
                e.setHORAMES(jsonArreglo.getJSONObject(i).getString("HORAMES"));
                e.setRACKMES(jsonArreglo.getJSONObject(i).getInt("RACKMES"));
                e.setFILAMES(jsonArreglo.getJSONObject(i).getInt("FILAMES"));
                e.setCOLUMNAMES(jsonArreglo.getJSONObject(i).getInt("COLUMNAMES"));
                e.setID_PRODUCTOMES(jsonArreglo.getJSONObject(i).getString("ID_PRODUCTOMES"));
                e.setDESCRIPCION_PRODUCTOMES(jsonArreglo.getJSONObject(i).getString("DESCRIPCION_PRODUCTOMES"));
                e.setID_ENVASEMES(jsonArreglo.getJSONObject(i).getString("ID_ENVASEMES"));
                e.setENVASEMES(jsonArreglo.getJSONObject(i).getString("ENVASEMES"));
                e.setLOTEMES(jsonArreglo.getJSONObject(i).getString("LOTEMES"));
                e.setCAPACIDADMES(jsonArreglo.getJSONObject(i).getString("CAPACIDADMES"));
                e.setCONSECUTIVOMES(jsonArreglo.getJSONObject(i).getString("CONSECUTIVOMES"));
                e.setCANTIDADMES(jsonArreglo.getJSONObject(i).getInt("CANTIDADMES"));
                lista.add(e);

            }

            ArrayAdapter<Escaneo> a =new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
            lvEscaneos.setAdapter(a);

            //No agarra el id, por eso no funciona

//            lvEscaneos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                    Escaneo e = lista.get(position);
//
//
//                    String url = "https://appsionmovil.000webhostapp.com/eliminar_inventario.php?ID_INVENTARIOMES="+e.getID_INVENTARIOMES();
//
//                    Toast.makeText(BarcodeActivity.this,url,Toast.LENGTH_SHORT).show();
//
//                    cliente.post(url, new AsyncHttpResponseHandler() {
//                        @Override
//                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                            if (statusCode==200){
//                                Toast.makeText(BarcodeActivity.this,"Producto eliminado",Toast.LENGTH_SHORT).show();
//                                try {
//                                    Thread.sleep(1000);
//                                } catch (InterruptedException e1) {
//                                    e1.printStackTrace();
//                                }
//
//                                obtenerProductos();
//
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//                        }
//                    });
//                    return true;
//                }
//            });


            lvEscaneos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Escaneo e = lista.get(position);
                    StringBuffer b = new StringBuffer();

                    b.append("ID_INVENTARIOMES: " + e.getID_INVENTARIOMES()+"\n");
                    b.append("ID_USUARIOMES: " + e.getID_USUARIOMES()+"\n");
                    b.append("FECHAMES: "+e.getFECHAMES()+"\n");
                    b.append("HORAMES: "+e.getHORAMES()+"\n");
                    b.append("RACKMES: "+e.getRACKMES()+"\n");
                    b.append("FILAMES: "+e.getFILAMES()+"\n");
                    b.append("COLUMNAMES: "+e.getCOLUMNAMES()+"\n");
                    b.append("ID_PRODUCTOMES: "+e.getID_PRODUCTOMES()+"\n");
                    b.append("DESCRIPCION_PRODUCTOMES: "+e.getDESCRIPCION_PRODUCTOMES()+"\n");
                    b.append("ID_ENVASEMES: "+e.getID_ENVASEMES()+"\n");
                    b.append("ENVASEMES: "+e.getENVASEMES()+"\n");
                    b.append("LOTEMES: "+e.getLOTEMES()+"\n");
                    b.append("CAPACIDADMES: "+e.getCAPACIDADMES()+"\n");
                    b.append("CONSECUTIVOMES: "+e.getCONSECUTIVOMES()+"\n");
                    b.append("CANTIDADMES: "+e.getCANTIDADMES()+"\n");

                    AlertDialog.Builder a = new AlertDialog.Builder(BarcodeActivity.this);
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


    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    /**
 * Called when a view has been clicked.
 *
 * @param v The view that was clicked.
 */
@Override
public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
        // launch barcode activity.
        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
//        intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
//        intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

        startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }

    if (v.getId() == R.id.ubicacion_read_barcode) {
        // launch barcode activity.
        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
//        intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
//        intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

        startActivityForResult(intent, RC_BARCODE_CAPTURE);
    }

        }



/**
 * Called when an activity you launched exits, giving you the requestCode
 * you started it with, the resultCode it returned, and any additional
 * data from it.  The <var>resultCode</var> will be
 * {@link #RESULT_CANCELED} if the activity explicitly returned that,
 * didn't return any result, or crashed during its operation.
 * <p/>
 * <p>You will receive this call immediately before onResume() when your
 * activity is re-starting.
 * <p/>
 *
 * @param requestCode The integer request code originally supplied to
 *                    startActivityForResult(), allowing you to identify who this
 *                    result came from.
 * @param resultCode  The integer result code returned by the child activity
 *                    through its setResult().
 * @param data        An Intent, which can return result data to the caller
 *                    (various data can be attached to Intent "extras").
 * @see #startActivityForResult
 * @see #createPendingResult
 * @see #setResult(int)
 */
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
        if (resultCode == CommonStatusCodes.SUCCESS) {
        if (data != null) {
        Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
        statusMessage.setText(R.string.barcode_success);


        String escaneo = barcode.displayValue.toString();
        if (escaneo.length()>26){

                String [] parts = escaneo.split("-");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];
                String part4 = parts[3];
                String part5 = parts[4];
                String part6 = parts[5];


                String producto_string = part1+"-"+part2+"-"+part3;

            barcodeValue.setText(barcode.displayValue);

                producto.setText(producto_string);
                envase.setText(part4);
                lote.setText(part5);
                consecutivo.setText(part6);}

            if (escaneo.length()<15){

//                String [] parts = escaneo.split("-");
//                String part1 = parts[0];
//                String part2 = parts[1];
//                String part3 = parts[2];
//                String part4 = parts[3];
//                String part5 = parts[4];
//                String part6 = parts[5];
//
//
//                String producto_string = part1+"-"+part2+"-"+part3;
//
//                producto.setText(producto_string);
//                envase.setText(part4);
//                lote.setText(part5);
//                consecutivo.setText(part6);
                String [] parts = escaneo.split("-");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];

                String ubicacion_string = part1+"-"+part2+"-"+part3;
                ubicacion.setText(ubicacion_string);
                rack_valor.setText(part1);
                fila_valor.setText(part2);
                columna_valor.setText(part3);
                obtenerProductos();


        }



                else {
//            producto.setText("no encontrado");
//            envase.setText("no encontrado");
//            lote.setText("no encontrado");
//            consecutivo.setText("no encontrado");
        }


        Log.d(TAG, "Barcode read: " + barcode.displayValue);
        } else {
        statusMessage.setText(R.string.barcode_failure);
        Log.d(TAG, "No barcode captured, intent data is null");
        }
        } else {
        statusMessage.setText(String.format(getString(R.string.barcode_error),
        CommonStatusCodes.getStatusCodeString(resultCode)));
        }
        }
        else {
        super.onActivityResult(requestCode, resultCode, data);
        }
        }

    @Override
    public void applyText(String codigo_barras) {
    //barcodeValue.setText(codigo_barras);

        if (codigo_barras.length()>26){

            String [] parts = codigo_barras.split("-");
            String part1 = parts[0];
            String part2 = parts[1];
            String part3 = parts[2];
            String part4 = parts[3];
            String part5 = parts[4];
            String part6 = parts[5];


            String producto_string = part1+"-"+part2+"-"+part3;

            barcodeValue.setText(codigo_barras);

            producto.setText(producto_string);
            envase.setText(part4);
            lote.setText(part5);
            consecutivo.setText(part6);}

        if (codigo_barras.length()<15){

//                String [] parts = escaneo.split("-");
//                String part1 = parts[0];
//                String part2 = parts[1];
//                String part3 = parts[2];
//                String part4 = parts[3];
//                String part5 = parts[4];
//                String part6 = parts[5];
//
//
//                String producto_string = part1+"-"+part2+"-"+part3;
//
//                producto.setText(producto_string);
//                envase.setText(part4);
//                lote.setText(part5);
//                consecutivo.setText(part6);
            String [] parts = codigo_barras.split("-");
            String part1 = parts[0];
            String part2 = parts[1];
            String part3 = parts[2];

            String ubicacion_string = part1+"-"+part2+"-"+part3;
            ubicacion.setText(ubicacion_string);

            String rack_php="0";
            String fila_php="0";
            String columna_php="0";

            rack_valor.setText(part1);
            fila_valor.setText(part2);
            columna_valor.setText(part3);
            obtenerProductos();


        }

    }
}

