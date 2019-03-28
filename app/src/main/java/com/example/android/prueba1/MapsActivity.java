package com.example.android.prueba1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CameraUpdate camara;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    Calendar calendar;
    private int mhour;
    private int mminute;
    Date currentTime = Calendar.getInstance().getTime();
    private FirebaseAuth mAuth;

    //Agregar a DB remota
    private AsyncHttpClient cliente;




    //Obtener coordenadas https://stackoverflow.com/questions/36377260/android-studio-fetching-gps-coordinates-of-the-current-marker-location-in-goog
    Button btnShowLocation;


    // GPSTracker class
    GPSTracker gps;

    EditText txtRemision;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        txtRemision = (EditText) findViewById(R.id.numeroRemision) ;

        mAuth = FirebaseAuth.getInstance();

        //Agregar datos DB remota
        cliente = new AsyncHttpClient();


        //Override --------

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {



            if (txtRemision.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Agregar número de remisión, traspaso o Cemex", Toast.LENGTH_LONG).show();
                }



                else {

                int largo = txtRemision.length();

                if (largo == 1){
                    Toast.makeText(getApplicationContext(), "Número inválido, agregar número de remisión, traspaso o Cemex", Toast.LENGTH_LONG).show();
                }
                else {




                String remision_string = txtRemision.getText().toString();
                String primeros_2_numeros = remision_string.substring(0, 2);
                String primer_numero = remision_string.substring(0, 1);


//                if (primeros_2_numeros.equals("18")) {
//                    Toast.makeText(getApplicationContext(), "Recuerda que va la REMISIÓN, no el pedido", Toast.LENGTH_LONG).show();
//                }

//                if ((largo > 7) || (largo == 6) || (largo < 4)) {
//                    Toast.makeText(getApplicationContext(), "Número inválido, revisa que no tenga ceros al principio y que esté completo", Toast.LENGTH_LONG).show();
//                }


                if (primer_numero.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Revisa que no tenga ceros al principio", Toast.LENGTH_LONG).show();
                }

                if ((largo == 4 && !primer_numero.equals("7") || (largo == 4 && !primer_numero.equals("8")))|| (largo == 5 && !primeros_2_numeros.equals("26"))) {
                    Toast.makeText(getApplicationContext(), "Revisa el número de factura", Toast.LENGTH_LONG).show();
                }

                if ((largo == 7 && !primeros_2_numeros.equals("17"))) {
                    Toast.makeText(getApplicationContext(), "Revisa el número de remisión", Toast.LENGTH_LONG).show();
                }

                if ((largo == 7 && primeros_2_numeros.equals("17")) || (largo == 8 && primeros_2_numeros.equals("18")) ||  (largo == 8 && primeros_2_numeros.equals("19")) || (largo == 7 && primeros_2_numeros.equals("15"))|| (largo == 4 && primer_numero.equals("7")) || (largo == 4 && primer_numero.equals("8")) || (largo == 4 && primer_numero.equals("3")) ||  (largo == 5 && primeros_2_numeros.equals("26"))) {


                    // Create class object
                    gps = new GPSTracker(MapsActivity.this);

                    // Check if GPS enabled
                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        Date currentTime = Calendar.getInstance().getTime();

                        //Obtener fecha y hora
                        calendar = Calendar.getInstance();
                        mhour = calendar.get(Calendar.HOUR);
                        mminute = calendar.get(Calendar.MINUTE);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
                        System.out.println(sdf.format(calendar.getTime()));
                        System.out.println("hour \t\t: " + mhour);


                        // \n is for new line
                        //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                        //Obtener número de pedido desde pedidos
                        String numPedido = getIntent().getStringExtra("estePedido");
                        //String corrPedido = getIntent().getStringExtra("esteCorreo");

                        //Actualizar en la primera versión de prueba, después borrar

                        numPedido = txtRemision.getText().toString();

                        //corrPedido = "javierb@nervion.com.mx"; //Borrar
                        //String usuario = "Juan Darío Tapia Fajardo";
                        //String usuario = "Luis Alberto García Martínez";
                        String usuario = "José Benjamín Osornio Cruz";
                        //String usuario = "Ricardo Soria Sánchez";
                        //String usuario = "Luis Enrique Miranda García";
                        //String usuario = "Víctor García Hernández";
                        //String usuario = "Guillermo Rueda Cruz";
                        //String usuario = "Gerardo Bárcenas Uribe";
                        //String usuario = "Ana Karen Torres Hernández";
                        //String usuario = "Luis Alfredo García Rosas";
                        //String usuario = "Miriam Nallely Monroy Muñoz";
                        //String usuario = "Amadeo Santos Santiago";
                        //String usuario = "Emily Gómez Ramos";
                        //String usuario = "Francisco Javier Pérez Gómez";
                        //String usuario = "Javier Belausteguigoitia Ibarrola";
                        //String usuario = "Tablet1";





                        //
                        String aEmailList[] = {"javierb@nervion.com.mx",  "felipe.moreno@nervion.com.mx"};




                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Entrega de remisión " + numPedido);
                            intent.putExtra(Intent.EXTRA_EMAIL, aEmailList);
                            intent.putExtra(Intent.EXTRA_TEXT, "Remisión: " + numPedido +
                                    "\n Entregado por: " + usuario +
                                    "\n LatLong: " + latitude + ", " + longitude + "\n Fecha:" + currentTime
                                    + "\n" + "\n" + "\n" + numPedido + "," + usuario + "," + latitude + "," + longitude + "," + currentTime)

                            ;

                        String current_user_db = mAuth.getCurrentUser().getEmail().toString();



                        String hora = currentTime.toString();

                        String [] hora_parts = hora.split(" ");
                        String hora_part1 = hora_parts[0];
                        String hora_part2 = hora_parts[1];
                        String hora_part3 = hora_parts[2];
                        String hora_part4 = hora_parts[3];
                        String hora_part5 = hora_parts[4];
                        String hora_part6 = hora_parts[5];


                        String hora_db = hora_part4;
                        String fecha_db = hora_part3+"/"+hora_part2+"/"+hora_part6;


                            Entrega e = new Entrega();
                            e.setSolicitud(Integer.parseInt(numPedido));
                            e.setNombre_Entrega(current_user_db);
                            e.setLatitud(latitude);
                            e.setLongitud(longitude);
                            e.setFecha(fecha_db);
                            e.setHora(hora_db);

                            agregarEntrega(e);





                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }



                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();
                    }


                }
            }

        }

        }



    });



        //Override ----------



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void agregarEntrega(Entrega e) {

        String current_user_db = mAuth.getCurrentUser().getEmail().toString();


        final String url = "https://appsionmovil.000webhostapp.com/agregar_entrega.php?";
        String parametros = "Solicitud="+e.getSolicitud()+"&Nombre_Entrega="+current_user_db+
                "&Latitud="+e.getLatitud()+"&Longitud="+e.getLongitud()+"&Fecha="+e.getFecha()+"&Hora="+e.getHora();



        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    //Toast.makeText(MapsActivity.this, url,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    //Conectar con BD remota




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        double latNer= 19.519379;
//        double longNer=-99.211373;

        gps = new GPSTracker(MapsActivity.this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //Cambiar el tipo de mapa

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Añadir marker en Nervion



        LatLng nervion = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions().position(nervion).title("Nervion"));
        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        camara = CameraUpdateFactory.newLatLngZoom(nervion, 17);
        mMap.animateCamera(camara);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }

        }
    }

    //mMap.getUiSettings().setZoomControlsEnabled(true);

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This app requires location permission to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }


    public void onLocationChanged(Location location) {
        // New location has now been determined
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }


    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    public void composeEmail(View view) {

        //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Entrega de pedido 18050411");
        intent.putExtra(Intent.EXTRA_TEXT, "Pedido: 18050411"+
                "\n Entregado por: Javier Belausteguigoitia"+
                "\nLat: "+
                "\n Fecha: 22/05/2018, 11:45:08");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
