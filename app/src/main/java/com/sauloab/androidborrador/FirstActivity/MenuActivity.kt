package com.sauloab.androidborrador.FirstActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sauloab.androidborrador.R
import com.sauloab.androidborrador.imccalculator.imcCalculatorActivity
import com.sauloab.androidborrador.todoapp.ToDoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu2)

        val btnSaludaAPP = findViewById<Button>(R.id.btnSaludaAPP)
        val btnIMC = findViewById<Button>(R.id.IMCapp)
        val btnToDo = findViewById<Button>(R.id.btnToDo)

        btnSaludaAPP.setOnClickListener { navegarSaludaApp() }
        btnIMC.setOnClickListener { navegartoIMCApp() }
        btnToDo.setOnClickListener { navegateToDoApp() }
    }

    private fun navegarSaludaApp() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navegartoIMCApp() {
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navegateToDoApp() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
    }
}
