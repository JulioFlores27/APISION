package com.example.android.prueba1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity {

    Button btnShowLocation;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Crear ListArray de módulos
        //LinearLayout rootView =(LinearLayout)findViewById(R.id.rootView);



        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        //Obtener Toast con location: https://stackoverflow.com/questions/36377260/android-studio-fetching-gps-coordinates-of-the-current-marker-location-in-goog

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // Show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Create class object
                gps = new GPSTracker(MainActivity.this);

                // Check if GPS enabled
                if(gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                }
            }
        });








    }

    int number = 0;
    int i=0;

    public int roll (){
        int dice = (int) (random() * 6) +1;
        return dice;
    }

//    public void text_to_display(View view) {
//
//        int dice1 = roll();
//        int dice2 = roll();
//        int dice3 = roll();
//        int dice4 = roll();
//        int dice5 = roll();
//        int dice6 = roll();
//        int dice7 = roll();
//        int dice8 = roll();
//        int dice9 = roll();
//
//
//        number = dice1 + dice2 + dice3 + dice4 + dice5 + dice6 + dice7 + dice8 + dice9;
//
//        int i =0;
//
//        while (!(dice1 == dice2 && dice2 == dice3 && dice3 ==dice4 && dice4 == dice5 && dice5 == dice6 && dice6 == dice7 && dice7 == dice8 && dice8 == dice9)){
//            dice1 = roll();
//            dice2 = roll();
//            dice3 = roll();
//            dice4 = roll();
//            dice5 = roll();
//            dice6 = roll();
//            dice7 = roll();
//            dice8 = roll();
//            dice9 = roll();
//            i = i + 1;
//        }
//
//
//
//        display(number);
//        display2(i);
//    }





    public void text_to_display(View view) {

        int dice1 = roll();
        int dice2 = roll();
        int dice3 = roll();
        int dice4 = roll();
        int dice5 = roll();
        int dice6 = roll();
        int dice7 = roll();
        int dice8 = roll();
        int dice9 = roll();




        int i =0;

        while (!(dice1 == dice2 && dice2 == dice3 && dice3 ==dice4 && dice4 == dice5 && dice5 == dice6 && dice6 == dice7 && dice7 == dice8 && dice8 == dice9)){
            dice1 = roll();
            dice2 = roll();
            dice3 = roll();
            dice4 = roll();
            dice5 = roll();
            dice6 = roll();
            dice7 = roll();
            dice8 = roll();
            dice9 = roll();
            i = i + 1;
        }

        number = dice1 + dice2 + dice3 + dice4 + dice5 + dice6 + dice7 + dice8 + dice9;
        display(number);
        display2(i);
    }





    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.result);
        quantityTextView.setText("Total: "+number);
    }

    private void display2(int i) {
        TextView quantityTextView = findViewById(R.id.result2);
        quantityTextView.setText("Number of throws: "+i);
    }

    private void display3 (int i) {
        TextView quantityTextView = findViewById(R.id.result2);
        quantityTextView.setText("Number of throws: "+i);
    }

    public void add(View view) {

       number = number + 1;

        display3(number);
    }



    public void reset(View view) {

        number = 0;
        i = 0;

        display2(number);
        display(i);
    }

    public void buttonWasClicked (View button){
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();
    }

    public class ButtonClickedListener implements View.OnClickListener {
        public  void onClick (View view){
            Toast.makeText(MainActivity.this, "Button clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeName (View view) {
        EditText nameEditText =(EditText) findViewById(R.id.nameEditText);
        TextView displayTextView = (TextView) findViewById(R.id.displayTextView);

        String name = nameEditText.getText().toString();
        displayTextView.setText("Hello "+name+"!");
    }

    public void moveToMaps (View view){
        Intent mapsActivity = new Intent(this, MapsActivity.class);
        startActivity(mapsActivity);
    }

    public void moveToLogIn (View view){
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    public void moveToModules (View view){
        Intent modulesActivity = new Intent(this, ModulesActivity.class);
        startActivity(modulesActivity);
    }

    public void moveToModulos (View view){
        Intent modulosActivity = new Intent(this, ModulosActivity.class);
        startActivity(modulosActivity);
    }

    public void moveToPedidos (View view){
        Intent pedidosActivity = new Intent(this, PedidosActivity.class);
        startActivity(pedidosActivity);
    }

    public void moveToMain (View view){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }






}


