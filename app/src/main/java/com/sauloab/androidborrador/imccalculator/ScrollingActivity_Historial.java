package com.sauloab.androidborrador.imccalculator;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.sauloab.androidborrador.R;

import java.util.List;

/**
 * Activity que muestra el historial de resultados IMC en una lista.
 * Utiliza un ListView con un ArrayAdapter basado en los datos obtenidos de la base de datos.
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
        List<IMCResult> resultados = bd_imc.obtenerResultados();

        // Configura el ArrayAdapter para mostrar los datos en el ListView
        ArrayAdapter<IMCResult> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                resultados
        );
        listViewHistorial.setAdapter(adapter);
    }
}
