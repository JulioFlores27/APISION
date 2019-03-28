package com.example.android.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.prueba1.barcodereader.ui.camera.SurtirActivity;


public class SeleccionarPedidoActivity extends AppCompatActivity {

    private Button seleccionar_pedido_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_pedido);

        seleccionar_pedido_btn = (Button) findViewById(R.id.button_seleccionar_solicitud);
        seleccionar_pedido_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent surtirPedidos = new Intent(SeleccionarPedidoActivity.this, SurtirActivity.class);
                startActivity(surtirPedidos);

            }
        });

    }
}
