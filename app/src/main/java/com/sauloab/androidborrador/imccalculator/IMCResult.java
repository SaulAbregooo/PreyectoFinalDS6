package com.sauloab.androidborrador.imccalculator;

/**
 * Clase modelo que representa un registro de IMC (peso, altura, resultado y fecha).
 * Sirve como contenedor de datos para los resultados almacenados en la base de datos.
 */
public class IMCResult {
    // Atributos del registro de IMC
    private int id;
    private double peso;
    private double altura;
    private double resultado;
    private String fecha;

    // Constructor para inicializar todos los campos
    public IMCResult(int id, double peso, double altura, double resultado, String fecha) {
        this.id = id;
        this.peso = peso;
        this.altura = altura;
        this.resultado = resultado;
        this.fecha = fecha;
    }

    // Métodos getter para acceder a los valores
    public int getId() { return id; }
    public double getPeso() { return peso; }
    public double getAltura() { return altura; }
    public double getResultado() { return resultado; }
    public String getFecha() { return fecha; }

    // Métodos setter (opcional, para modificar los valores)
    public void setId(int id) { this.id = id; }
    public void setPeso(double peso) { this.peso = peso; }
    public void setAltura(double altura) { this.altura = altura; }
    public void setResultado(double resultado) { this.resultado = resultado; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    /**
     * Devuelve una cadena formateada con los datos del registro.
     */
    @Override
    public String toString() {
        return "Fecha: " + fecha +
                "\nPeso: " + peso + " kg" +
                "\nAltura: " + altura + " m" +
                "\nIMC: " + resultado;
    }
}
