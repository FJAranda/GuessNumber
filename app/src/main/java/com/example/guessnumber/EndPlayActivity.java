package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndPlayActivity extends AppCompatActivity {

    TextView tvMensajeFinal;
    Button btnReiniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_play);

        tvMensajeFinal = findViewById(R.id.tvMensajeFinal);
        btnReiniciar = findViewById(R.id.btnReiniciar);
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndPlayActivity.this, ConfigActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        boolean adivinado = bundle.getBoolean("adivinado");
        String nombre = bundle.getString("nombre");
        String mensaje = bundle.getString("mensaje");
        int intentos = bundle.getInt("intentos");
        int numero = bundle.getInt("numero");
        if (adivinado) {
            tvMensajeFinal.setText(nombre + ", " + mensaje + " " + numero + " en tan solo " + intentos + " intentos.");
        }else{
            tvMensajeFinal.setText(nombre + ", " + mensaje + " " + numero + ". Has usado " + intentos + " intentos.");
        }
    }

}