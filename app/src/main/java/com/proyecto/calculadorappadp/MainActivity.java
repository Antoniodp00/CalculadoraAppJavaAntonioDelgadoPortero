package com.proyecto.calculadorappadp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pantalla;
    private static final String CLAVE_EXPRESION = "expresion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantalla = findViewById(R.id.displayTextView);

        // Asigna los listeners de clic a todos los botones
        int[] idsBotones = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnDot, R.id.btnClear, R.id.btnEquals
        };

        for (int id : idsBotones) {
            View boton = findViewById(id);
            if (boton != null) {
                boton.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        String expresionActual = pantalla.getText().toString();

        if (v.getId() == R.id.btnClear) {
            pantalla.setText("");
        } else if (v.getId() == R.id.btnEquals) {
            try {
                double resultado = EvaluadorDeExpresiones.evaluate(expresionActual);
                if (Double.isNaN(resultado)) {
                    pantalla.setText("Error");
                } else {
                    pantalla.setText(String.valueOf(resultado));
                }
            } catch (Exception e) {
                pantalla.setText("Error");
            }
        } else {
            Button boton = (Button) v;
            pantalla.append(boton.getText().toString());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guarda la expresión actual en el Bundle
        outState.putString(CLAVE_EXPRESION, pantalla.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restaura la expresión desde el Bundle
        pantalla.setText(savedInstanceState.getString(CLAVE_EXPRESION));
    }
}
