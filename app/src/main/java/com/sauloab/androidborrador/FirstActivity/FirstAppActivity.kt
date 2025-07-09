package com.sauloab.androidborrador.FirstActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sauloab.androidborrador.R

class FirstAppActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        val btnClick = findViewById<AppCompatButton>(R.id.btnClick)
        val etName = findViewById<EditText>(R.id.etName)


        btnClick.setOnClickListener {
            val Name = etName.text.toString()
            if (Name.isNotEmpty())
            {
            val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME",Name)
                startActivity(intent)
            /*Intent
             Es necesario saber que debemos iniciar el intent despues de hacer de ya haber hecho
             todas la acciones que queremos enviar.
             tenemos un metodo de intent que nos permite llevarnos parametros
             otra activity aqui basicamente lo que hago es craer un intent
             que es lo que nos permite viajar entrer activitys, este es un de los varios tipos de intent,
             el cual su objetivo pricipal es hacer un llamado desde un activity a otro para que este otro realize una acción
             */

            }
         }
        }
    }
