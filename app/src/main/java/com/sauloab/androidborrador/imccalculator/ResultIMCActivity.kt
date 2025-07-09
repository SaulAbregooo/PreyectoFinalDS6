package com.sauloab.androidborrador.imccalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sauloab.androidborrador.R
import com.sauloab.androidborrador.imccalculator.imcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var tvResult:TextView
    private lateinit var tvImc:TextView
    private lateinit var tvDescription:TextView
    private lateinit var  btnRecalculate:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        val result = intent.extras?.getDouble(IMC_KEY) ?:-1.0
        /*Si tienes algun valor en extras
        * damelo en formato double, con la clave IMC y si no la tines
        * dulveme en -1.0 para que siempre sea un double*/
        initComponet()
        initIU(result)
        initListener()
        }

    private fun initListener() {
        btnRecalculate.setOnClickListener{
          onBackPressed()
        }
    }

    private fun initComponet() {
        tvResult = findViewById(R.id.tvResult)
        tvImc = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
    @SuppressLint("SetTextI18n")
    private fun initIU(result: Double) {
        tvImc.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {//Bajo peso
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }

            in 18.51..24.99 -> {//Peso normal
                tvResult.text = getString(R.string.title_bajo_normal)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }

            in 25.00..29.99 -> {//sobrepeso
                tvResult.text = getString(R.string.title_sobre_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.sobre_peso))
                tvDescription.text = getString(R.string.description_sobre_peso)
            }

            in 30.00..99.00 -> {//obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obeso))
                tvDescription.text = getString(R.string.description_obesidad)
            }

            else -> {
                tvImc.text = getString(R.string.error)
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obeso))
                tvDescription.text = getString(R.string.description_obesidad)
            }
        }
    }

}


