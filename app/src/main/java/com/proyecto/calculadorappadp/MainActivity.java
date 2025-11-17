package com.proyecto.calculadorappadp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText entradaNumero1;
    private EditText entradaNumero2;
    private RadioGroup grupoTipoCalculo;
    private CardView tarjetaCalculoUnico;
    private CardView tarjetaCalculoMultiple;
    private RadioGroup grupoRadioOperaciones;
    private Button botonCalcular1;
    private CheckBox casillaSuma;
    private CheckBox casillaResta;
    private CheckBox casillaMultiplicacion;
    private CheckBox casillaDivision;
    private Button botonCalcular2;
    private EditText campoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        entradaNumero1 = findViewById(R.id.entradaNumero1);
        entradaNumero2 = findViewById(R.id.entradaNumero2);
        grupoTipoCalculo = findViewById(R.id.grupoTipoCalculo);
        tarjetaCalculoUnico = findViewById(R.id.tarjetaCalculoUnico);
        tarjetaCalculoMultiple = findViewById(R.id.tarjetaCalculoMultiple);
        grupoRadioOperaciones = findViewById(R.id.grupoRadioOperaciones);
        botonCalcular1 = findViewById(R.id.botonCalcular1);
        casillaSuma = findViewById(R.id.casillaSuma);
        casillaResta = findViewById(R.id.casillaResta);
        casillaMultiplicacion = findViewById(R.id.casillaMultiplicacion);
        casillaDivision = findViewById(R.id.casillaDivision);
        botonCalcular2 = findViewById(R.id.botonCalcular2);
        campoResultado = findViewById(R.id.campoResultado);

        // Asignar listeners
        botonCalcular1.setOnClickListener(this);
        botonCalcular2.setOnClickListener(this);

        grupoTipoCalculo.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioBotonUnico) {
                tarjetaCalculoUnico.setVisibility(View.VISIBLE);
                tarjetaCalculoMultiple.setVisibility(View.GONE);
            } else if (checkedId == R.id.radioBotonMultiple) {
                tarjetaCalculoUnico.setVisibility(View.GONE);
                tarjetaCalculoMultiple.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // Primero, obtener los números de los EditText
        String strNumero1 = entradaNumero1.getText().toString();
        String strNumero2 = entradaNumero2.getText().toString();

        if (strNumero1.isEmpty() || strNumero2.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_enter_both_numbers), Toast.LENGTH_SHORT).show();
            return;
        }

        double numero1;
        double numero2;
        try {
            numero1 = Double.parseDouble(strNumero1);
            numero2 = Double.parseDouble(strNumero2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.error_invalid_numbers), Toast.LENGTH_SHORT).show();
            return;
        }

        int id = v.getId();
        if (id == R.id.botonCalcular1) {
            calcularOperacionUnica(numero1, numero2);
        } else if (id == R.id.botonCalcular2) {
            calcularOperacionesMultiples(numero1, numero2);
        }
    }

    private void calcularOperacionUnica(double numero1, double numero2) {
        int selectedId = grupoRadioOperaciones.getCheckedRadioButtonId();
        double resultado = 0;
        boolean operacionSeleccionada = true;

        if (selectedId == R.id.radioBotonSuma) {
            resultado = numero1 + numero2;
        } else if (selectedId == R.id.radioBotonResta) {
            resultado = numero1 - numero2;
        } else if (selectedId == R.id.radioBotonMultiplicacion) {
            resultado = numero1 * numero2;
        } else if (selectedId == R.id.radioBotonDivision) {
            if (numero2 == 0) {
                campoResultado.setText(getString(R.string.error_division_by_zero));
                return;
            }
            resultado = numero1 / numero2;
        } else {
            operacionSeleccionada = false;
            Toast.makeText(this, getString(R.string.error_select_operation), Toast.LENGTH_SHORT).show();
        }

        if(operacionSeleccionada) {
            campoResultado.setText(String.format("%.2f", resultado));
        }
    }

    private void calcularOperacionesMultiples(double numero1, double numero2) {
        StringBuilder resultados = new StringBuilder();
        boolean algunaSeleccionada = false;

        if (casillaSuma.isChecked()) {
            resultados.append(getString(R.string.result_sum_is, numero1 + numero2));
            algunaSeleccionada = true;
        }
        if (casillaResta.isChecked()) {
            resultados.append(getString(R.string.result_subtraction_is, numero1 - numero2));
            algunaSeleccionada = true;
        }
        if (casillaMultiplicacion.isChecked()) {
            resultados.append(getString(R.string.result_multiplication_is, numero1 * numero2));
            algunaSeleccionada = true;
        }
        if (casillaDivision.isChecked()) {
            if (numero2 == 0) {
                resultados.append(getString(R.string.result_division_error));
            } else {
                resultados.append(getString(R.string.result_division_is, numero1 / numero2));
            }
            algunaSeleccionada = true;
        }

        if (!algunaSeleccionada) {
            Toast.makeText(this, getString(R.string.error_select_at_least_one_operation), Toast.LENGTH_SHORT).show();
            campoResultado.setText("");
        } else {
            // Eliminar el último salto de línea
            if (resultados.length() > 0) {
                resultados.setLength(resultados.length() - 1);
            }
            campoResultado.setText(resultados.toString());
        }
    }
}
