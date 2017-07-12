package com.example.danielramosrivera.appatt4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class opciones extends AppCompatActivity {

    Button btn_Lista , btn_EditLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        //Intentn para ir a la pantalla Lista
        btn_Lista = (Button)findViewById(R.id.bLista);
        btn_Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(opciones.this,lista.class);
                startActivity(i);
            }
        });

        //Intentn para ir a la pantalla EditLista
        btn_EditLista = (Button)findViewById(R.id.bEditLista);
        btn_EditLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(opciones.this, editLista.class);
                startActivity(i);
            }
        });

    }
}
