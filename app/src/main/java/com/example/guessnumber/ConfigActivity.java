package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity Inicial de configuración del juego de adivinar el número.
 *
 * Esta activity recibe el nombre del jugador y el numero de intentos para adivinar el numero y envía estos datos a PlayActivity.
 *
 * @author Francisco Javier Aranda Caro
 * @Version 2021.1210
 */
public class ConfigActivity extends AppCompatActivity {

    private EditText etNombreJugador;
    private EditText etNumeroIntentos;
    private Button btnJugar;
    private String strValidacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        etNombreJugador = findViewById(R.id.etNombreJugador);
        etNumeroIntentos = findViewById(R.id.etNumeroIntentos);
        btnJugar = findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int intentos = Integer.parseInt(etNumeroIntentos.getText().toString());
                    setValidation(etNombreJugador.getText().toString(), intentos);
                }catch (NumberFormatException e){
                    strValidacion = getResources().getString(R.string.strValidacionIntentos);
                    Toast.makeText(ConfigActivity.this, strValidacion, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setValidation(String nombre, int intentos) {
            if (nombre.isEmpty()){
                strValidacion = getResources().getString(R.string.strValidacionNombre);
                Toast.makeText(this, strValidacion, Toast.LENGTH_LONG).show();
            }else if(intentos < 1){
                strValidacion = getResources().getString(R.string.strValidacionIntentos);
                Toast.makeText(this, strValidacion, Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(ConfigActivity.this, PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombre);
                bundle.putInt("intentos", intentos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
    }


}