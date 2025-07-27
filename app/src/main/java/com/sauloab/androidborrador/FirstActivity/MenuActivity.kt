package com.sauloab.androidborrador.FirstActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sauloab.androidborrador.R
import com.sauloab.androidborrador.imccalculator.imcCalculatorActivity
import com.sauloab.androidborrador.imccalculator.activity_creditos
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu2)

        // Botón para ir a IMC
        val btnIMC = findViewById<Button>(R.id.IMCapp)
        btnIMC.setOnClickListener { navegartoIMCApp() }

        // Botón para ir a créditos
        val btnCreditos = findViewById<Button>(R.id.navegarToCreditos)
        btnCreditos.setOnClickListener { navegarToCreditos() }
    }


    private fun navegartoIMCApp() {
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }
    private fun navegarToCreditos() {
        val intent = Intent(this, activity_creditos::class.java)
        startActivity(intent)
    }

}
