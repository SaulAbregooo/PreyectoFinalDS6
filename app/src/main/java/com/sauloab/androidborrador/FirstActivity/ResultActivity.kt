package com.sauloab.androidborrador.FirstActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sauloab.androidborrador.R

class ResultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
    /*¿Que pasa aqui?
        bueno nosotros ya tenemos predeterminado un intent en nuestra Activity, por esta activity
        podemos acceder a los putextras que se envia de otras activitys, ahora me pregunto ¿como
        compilador sabrá defirenciar lo que le mandan las activitys?, eso se logra por medio de una
        key o clave que definimos en este caso la key es = "EXTRA_NAME".*/
        tvResult.text = "Hola papu $name"
        }
    }
