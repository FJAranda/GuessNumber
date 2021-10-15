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

import com.example.guessnumber.data.Game;

import java.util.Random;

/**
 * Activity que realiza la lógica del juego de adivinar el número.
 *
 * Esta activity recibe el objeto con los datos necesarios para adivinar el numero e implementa la lógica del juego
 *
 * @author Francisco Javier Aranda Caro
 * @Version 2021.1210
 */
public class PlayActivity extends AppCompatActivity {

    private EditText etPosibleNumero;
    private Button btnComprobar;
    private TextView tvResultado;
    private String mensaje;
    private int contadorIntentos = 0;
    private String strValidacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        etPosibleNumero = findViewById(R.id.etPosibleNumero);
        btnComprobar = findViewById(R.id.btnComprobar);
        tvResultado = findViewById(R.id.tvResultado);

        Bundle bundle = this.getIntent().getExtras();
        Game juego = (Game)bundle.getSerializable("juego");

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (juego.getIntentos() > contadorIntentos) {
                    try {
                        int numero = Integer.parseInt(etPosibleNumero.getText().toString());
                        contadorIntentos++;
                        mensaje = setMensaje(numero, juego.getNumeroAleatorio());
                        if (numero != juego.getNumeroAleatorio()) {
                            tvResultado.setText(mensaje);
                        }else{
                            Intent intent = new Intent(PlayActivity.this, EndPlayActivity.class);
                            Bundle bundle = new Bundle();
                            juego.setAdivinado(true);
                            bundle.putSerializable("juego", juego);
                            bundle.putInt("intentos", contadorIntentos);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    } catch (NumberFormatException e) {
                        strValidacion = getResources().getString(R.string.strValidacionNumero);
                        Toast.makeText(PlayActivity.this, strValidacion, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Intent intent = new Intent(PlayActivity.this, EndPlayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("juego", juego);
                    bundle.putInt("intentos", contadorIntentos);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


    }

    private String setMensaje(int numero, int numeroAleatorio) {
        String mensaje = "";
        if (numero > numeroAleatorio){
            mensaje = getResources().getString(R.string.strNumeroMenor);
        }else if (numero < numeroAleatorio){
            mensaje = getResources().getString(R.string.strNumeroMayor);
        }
        return mensaje;
    }
}