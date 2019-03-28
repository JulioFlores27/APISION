package com.example.android.prueba1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ExampleDialog2 extends AppCompatDialogFragment {

    private EditText editTextCodigo;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog2, null );

        builder.setView(view)
                .setTitle("Ingresar cantidad surtida")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String ubicacion_tecleada_surtir = editTextCodigo.getText().toString();
                        listener.applyText(ubicacion_tecleada_surtir);

                    }
                });

        editTextCodigo = view.findViewById(R.id.edit_cantidad_surtida_mp);

        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
            "must implement ExampleDialogListener");
        }
    }

    public  interface ExampleDialogListener{
        void applyText(String codigo_barras);

    }
}
