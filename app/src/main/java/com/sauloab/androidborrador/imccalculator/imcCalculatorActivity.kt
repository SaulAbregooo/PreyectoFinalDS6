package com.sauloab.androidborrador.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.sauloab.androidborrador.R

@RequiresApi(Build.VERSION_CODES.N)
class imcCalculatorActivity : AppCompatActivity() {

    // Declaración de componentes de UI
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvAltura: TextView
    private lateinit var rsAltura: RangeSlider
    private lateinit var btSubtractWeight: FloatingActionButton
    private lateinit var btSumWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnSumAge: FloatingActionButton
    private lateinit var btnsubstractAge: FloatingActionButton
    private lateinit var btnCalculate: Button
    private lateinit var btnHistorial: Button

    // Variables para los valores actuales
    private var currentHeight: Int = 120
    private var currentWeight: Int = 70
    private var currentAge: Int = 25
    private var isMaleSelected: Boolean = false
    private var isFemaleSelected: Boolean = false

    // Constante para pasar el IMC entre actividades
    companion object {
        const val IMC_KEY = "IMC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponet()
        intListeners()
        intUI()
    }

    // Inicializa referencias de los elementos de UI
    private fun initComponet() {
        viewMale = findViewById(R.id.viewHombre)
        viewFemale = findViewById(R.id.viewMujer)
        tvAltura = findViewById(R.id.tvAltura)
        rsAltura = findViewById(R.id.rsAltura)
        btSubtractWeight = findViewById(R.id.btSubtractWeight)
        btSumWeight = findViewById(R.id.btSumWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnsubstractAge = findViewById(R.id.btnsubstractAge)
        btnSumAge = findViewById(R.id.btnSumAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
        btnHistorial = findViewById(R.id.btnHistorial)
    }

    // Asigna acciones a los botones y sliders
    private fun intListeners() {
        viewMale.setOnClickListener {
            if (!isMaleSelected) {
                isMaleSelected = true
                isFemaleSelected = false
                setGenderColor()
            }
        }

        viewFemale.setOnClickListener {
            if (!isFemaleSelected) {
                isMaleSelected = false
                isFemaleSelected = true
                setGenderColor()
            }
        }

        rsAltura.addOnChangeListener { _, value, _ ->
            val decimalformat = DecimalFormat("#")
            currentHeight = decimalformat.format(value).toInt()
            tvAltura.text = "$currentHeight cm"
        }

        btSumWeight.setOnClickListener {
            currentWeight += 1
            setWieght()
        }

        btSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWieght()
        }

        btnSumAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        btnsubstractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
        btnHistorial.setOnClickListener {
            val intent = Intent(this, ScrollingActivity_Historial::class.java)
            startActivity(intent)
        }
    }

    // Navega a la pantalla de resultados y pasa los datos
    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        intent.putExtra("peso", currentWeight.toDouble())
        intent.putExtra("altura", currentHeight.toDouble() / 100)
        startActivity(intent)
    }

    // Calcula el índice de masa corporal
    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    // Actualiza texto de edad
    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    // Actualiza texto de peso
    private fun setWieght() {
        tvWeight.text = currentWeight.toString()
    }

    // Cambia el color del género seleccionado
    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgraundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgraundColor(isFemaleSelected))
    }

    // Retorna el color correspondiente al estado del componente
    private fun getBackgraundColor(isSelectComponet: Boolean): Int {
        val colorReference = if (isSelectComponet) {
            R.color.background_component_selected
        } else {
            R.color.background_componet
        }
        return ContextCompat.getColor(this, colorReference)
    }

    // Inicializa la UI al iniciar
    private fun intUI() {
        setGenderColor()
        setWieght()
        setAge()
    }
}
