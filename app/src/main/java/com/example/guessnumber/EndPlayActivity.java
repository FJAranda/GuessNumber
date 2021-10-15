package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guessnumber.data.Game;

import java.text.MessageFormat;


/**
 * Activity final del juego de adivinar el número.
 *
 * Esta activity recibe los datos del resultado del juego y se los muestra al usuario, además tiene un boton para volver a jugar.
 *
 * @author Francisco Javier Aranda Caro
 * @Version 2021.1510
 */
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
        Game juego = (Game)bundle.getSerializable("juego");
        int intentos = bundle.getInt("intentos");
        if (juego.isAdivinado()) {
            tvMensajeFinal.setText(getResources().getString(R.string.strEstupendo)+ juego.getNombre() + getResources().getString(R.string.strNumeroAdivinar) + juego.getNumeroAleatorio() + getResources().getString(R.string.strNumeroIntentosUsados) + intentos);
        }else{
            tvMensajeFinal.setText(getResources().getString(R.string.strPena)+ juego.getNombre() + getResources().getString(R.string.strNumeroAdivinar) + juego.getNumeroAleatorio() + getResources().getString(R.string.strNumeroIntentosUsados) + intentos);
        }
    }

}