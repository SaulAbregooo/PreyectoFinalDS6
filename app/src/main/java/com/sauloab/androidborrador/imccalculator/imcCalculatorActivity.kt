package com.sauloab.androidborrador.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.sauloab.androidborrador.R
@RequiresApi(Build.VERSION_CODES.N)
class imcCalculatorActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView  // estoy declarando unas funciones globales que se inicia más tardes
    private lateinit var viewFemale: CardView // las declaro aqui y no en la funcion principal para que otros metodos
    private lateinit var tvAltura: TextView //txv dodne se van a cambair los cm segun movamos el rs
    private lateinit var rsAltura: RangeSlider //rs que dara el numero ah poner dentro del txv
    private lateinit var btSubtractWeight: FloatingActionButton //basicamente se trata de un tipo diferente de button
    private lateinit var btSumWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var tvAge:TextView
    private lateinit var btnSumAge:FloatingActionButton
    private lateinit var btnsubstractAge:FloatingActionButton
    private lateinit var btnCalculate:Button

    private var currentHeight :Int = 120
    private var currentWeight :Int = 70
    private var currentAge :Int = 25
    //tengan acceso a esta dentro de mi clase, por eso el private
    private var isMaleSelected: Boolean = false
    private var isFemaleSelected: Boolean = false

    companion object{//este se declara par para que todos los vean esta propiedad
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

    }


    private fun intListeners() {
        viewMale.setOnClickListener {
            if (!isMaleSelected) {//SI mi hombre es false haz los siguiente, si es true no hagas nada
                isMaleSelected = true
                isFemaleSelected = false
                /*¿Porque de esto?
            lo declaro antes de cambiar los colores, cambio los generos cuadno hago click
            al cardview y activo el listener del genero  cambiando el valor del boleand del genero para que
            este pueda funcionar correctamente en mi funcion.
            */
                setGenderColor()
            }
        }
        viewFemale.setOnClickListener {//SI mi mujer es false haz los siguiente, si es true no hagas nada
            if (!isFemaleSelected) {
                isMaleSelected = false
                isFemaleSelected = true
                setGenderColor()
            }
        }

        rsAltura.addOnChangeListener() { _, value, _ ->

            val decimalformat = DecimalFormat("#.##")
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

        btnSumAge.setOnClickListener{
            currentAge +=1
            setAge()
        }
        btnsubstractAge.setOnClickListener {
            currentAge -=1
            setAge()
        }

        btnCalculate.setOnClickListener{
           val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC() : Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return  df.format(imc).toDouble()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWieght() {
     tvWeight.text = currentWeight.toString()
    }


    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgraundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgraundColor(isFemaleSelected))

    }

    private fun getBackgraundColor(isSelectComponet: Boolean):Int { //si es true
        val colorReference = if (isSelectComponet) {
            R.color.background_component_selected
        } else {
            R.color.background_componet
        }
       return  ContextCompat.getColor(this, colorReference)
    }

    private fun intUI() {
     setGenderColor()
        setWieght()
        setAge()
    }
}
