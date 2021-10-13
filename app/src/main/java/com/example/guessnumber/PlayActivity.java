package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private EditText etPosibleNumero;
    private Button btnComprobar;
    private TextView tvResultado;
    private String nombre;
    private String mensaje;
    private int intentos;
    private int contadorIntentos = 0;
    private String strValidacion;
    private final int numeroAleatorio = new Random().nextInt(100) +1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.println(Log.INFO, "Numero Aleatorio", String.valueOf(numeroAleatorio));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        etPosibleNumero = findViewById(R.id.etPosibleNumero);
        btnComprobar = findViewById(R.id.btnComprobar);
        tvResultado = findViewById(R.id.tvResultado);

        Bundle bundle = this.getIntent().getExtras();
        nombre = bundle.getString("nombre");
        intentos = bundle.getInt("intentos");

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentos > contadorIntentos) {
                    try {
                        int numero = Integer.parseInt(etPosibleNumero.getText().toString());
                        contadorIntentos++;
                        mensaje = setMensaje(numero);
                        if (numero != numeroAleatorio) {
                            tvResultado.setText(mensaje);
                        }else{
                            Intent intent = new Intent(PlayActivity.this, EndPlayActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("adivinado", true);
                            bundle.putString("nombre", nombre);
                            bundle.putString("mensaje", mensaje);
                            bundle.putInt("intentos", contadorIntentos);
                            bundle.putInt("numero", numeroAleatorio);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    } catch (NumberFormatException e) {
                        strValidacion = getResources().getString(R.string.strValidacionNumero);
                        Toast.makeText(PlayActivity.this, strValidacion, Toast.LENGTH_LONG).show();
                    }
                } else {
                    mensaje = getResources().getString(R.string.strNumeroNoAdivinado);
                    Intent intent = new Intent(PlayActivity.this, EndPlayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("adivinado", true);
                    bundle.putString("nombre", nombre);
                    bundle.putString("mensaje", mensaje);
                    bundle.putInt("intentos", intentos);
                    bundle.putInt("numero", numeroAleatorio);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


    }

    private String setMensaje(int numero) {
        String mensaje = "";
        if (numero > numeroAleatorio){
            mensaje = getResources().getString(R.string.strNumeroMenor);
        }else if (numero < numeroAleatorio){
            mensaje = getResources().getString(R.string.strNumeroMayor);
        }else if(numero == numeroAleatorio){
            mensaje = getResources().getString(R.string.strNumeroAdivinado);
        }
        return mensaje;
    }
}