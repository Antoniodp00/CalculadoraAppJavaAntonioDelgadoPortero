package com.proyecto.calculadorappadp;

public class EvaluadorDeExpresiones {

    public static double evaluate(String expresion) {
        // Este es un evaluador básico para expresiones simples. No soporta paréntesis ni operaciones complejas.
        String[] componentes = expresion.split("(?=[-+*/])|(?<=[-+*/])");

        if (componentes.length != 3) {
            return Double.NaN; // Formato no válido para esta calculadora básica
        }

        try {
            double primerOperando = Double.parseDouble(componentes[0]);
            String operador = componentes[1];
            double segundoOperando = Double.parseDouble(componentes[2]);

            switch (operador) {
                case "+":
                    return primerOperando + segundoOperando;
                case "-":
                    return primerOperando - segundoOperando;
                case "*":
                    return primerOperando * segundoOperando;
                case "/":
                    if (segundoOperando == 0) {
                        return Double.NaN; // División por cero
                    }
                    return primerOperando / segundoOperando;
                default:
                    return Double.NaN; // Operador desconocido
            }
        } catch (NumberFormatException e) {
            return Double.NaN; // Error al parsear los números
        }
    }
}
