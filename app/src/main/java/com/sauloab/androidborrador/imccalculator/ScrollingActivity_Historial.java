package com.sauloab.androidborrador.imccalculator;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.sauloab.androidborrador.R;

import java.util.List;
import java.util.Locale;

/**
 * Activity que muestra el historial de resultados IMC en una lista.
 * Permite modificar y eliminar registros mediante un diálogo.
 */
public class ScrollingActivity_Historial extends AppCompatActivity {

    private BD_imc bd_imc;             // Instancia de la base de datos
    private ListView listViewHistorial; // ListView para mostrar el historial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_historial);

        // Configura la barra de herramientas y el título colapsable
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Historial IMC");

        // Inicializa el ListView
        listViewHistorial = findViewById(R.id.listViewHistorial);

        // Obtiene los datos de la base de datos
        bd_imc = new BD_imc(this);
        refrescarLista();

        // Listener de clics en elementos del ListView
        listViewHistorial.setOnItemClickListener((parent, view, position, id) -> {
            IMCResult seleccionado = (IMCResult) parent.getItemAtPosition(position);
            mostrarOpcionesRegistro(seleccionado);
        });
    }

    private void mostrarOpcionesRegistro(IMCResult seleccionado) {
        String[] opciones = {"Modificar", "Eliminar", "Cancelar"};

        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Seleccione una opción")
                .setItems(opciones, (dialog, which) -> {
                    switch (which) {
                        case 0: // Modificar
                            mostrarDialogoModificar(seleccionado);
                            break;
                        case 1: // Eliminar
                            eliminarRegistro(seleccionado);
                            break;
                        case 2: // Cancelar
                            dialog.dismiss();
                            break;
                    }
                })
                .show();
    }

    private void eliminarRegistro(IMCResult seleccionado) {
        int filas = bd_imc.eliminarResultado(seleccionado.getId());
        if (filas > 0) {
            Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
            refrescarLista();
        } else {
            Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDialogoModificar(IMCResult seleccionado) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Modificar Registro");

        final android.view.View dialogView = getLayoutInflater().inflate(R.layout.dialog_editar_imc, null);
        builder.setView(dialogView);

        EditText etPeso = dialogView.findViewById(R.id.etPeso);
        EditText etAltura = dialogView.findViewById(R.id.etAltura);

        etPeso.setText(String.valueOf(seleccionado.getPeso()));
        etAltura.setText(String.valueOf(seleccionado.getAltura()));

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            double nuevoPeso = Double.parseDouble(etPeso.getText().toString());
            double nuevaAltura = Double.parseDouble(etAltura.getText().toString());

            // Recalcular el IMC con dos decimales
            double nuevoResultado = nuevoPeso / (nuevaAltura * nuevaAltura);
            String resultadoFormateado = String.format(Locale.getDefault(), "%.2f", nuevoResultado);
            nuevoResultado = Double.parseDouble(resultadoFormateado);

            int filas = bd_imc.actualizarResultado(seleccionado.getId(), nuevoPeso, nuevaAltura, nuevoResultado);
            if (filas > 0) {
                Toast.makeText(this, "Registro modificado", Toast.LENGTH_SHORT).show();
                refrescarLista();
            } else {
                Toast.makeText(this, "Error al modificar", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void refrescarLista() {
        List<IMCResult> resultados = bd_imc.obtenerResultados();
        ArrayAdapter<IMCResult> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                resultados
        );
        listViewHistorial.setAdapter(adapter);
    }
}
