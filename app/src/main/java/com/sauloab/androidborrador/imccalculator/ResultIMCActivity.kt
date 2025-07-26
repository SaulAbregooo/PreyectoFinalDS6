package com.sauloab.androidborrador.imccalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sauloab.androidborrador.R
import com.sauloab.androidborrador.imccalculator.imcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button
    private lateinit var buttonGuardar: Button

    private var peso = -1.0
    private var altura = -1.0
    private var imc = -1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        // Obtener datos del intent
        peso = intent.getDoubleExtra("peso", -1.0)
        altura = intent.getDoubleExtra("altura", -1.0)
        imc = intent.getDoubleExtra(IMC_KEY, -1.0)

        initComponet()
        initIU(imc)
        initListener()
    }

    // Enlaza componentes
    private fun initComponet() {
        tvResult = findViewById(R.id.tvResult)
        tvImc = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        buttonGuardar = findViewById(R.id.buttonGuardar)
    }

    // Acciones de botones
    private fun initListener() {
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }

        buttonGuardar.setOnClickListener {
            guardarEnBD()
        }
    }

    // Guarda el resultado en la base de datos local
    private fun guardarEnBD() {
        if (peso > 0 && altura > 0 && imc > 0) {
            val db = BD_imc(this)
            db.insertarResultado(peso, altura, imc)
            Toast.makeText(this, "Resultado guardado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error: datos inválidos", Toast.LENGTH_SHORT).show()
        }
    }

    // Muestra el resultado y descripción
    @SuppressLint("SetTextI18n")
    private fun initIU(result: Double) {
        tvImc.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }

            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.title_bajo_normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }

            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.title_sobre_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.sobre_peso))
                tvDescription.text = getString(R.string.description_sobre_peso)
            }

            in 30.00..99.00 -> {
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obeso))
                tvDescription.text = getString(R.string.description_obesidad)
            }

            else -> {
                tvImc.text = getString(R.string.error)
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obeso))
                tvDescription.text = getString(R.string.description_obesidad)
            }
        }
    }
}
