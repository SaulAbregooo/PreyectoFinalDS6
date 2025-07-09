package com.sauloab.androidborrador.todoapp

import android.app.Dialog
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sauloab.androidborrador.R

class ToDoActivity : AppCompatActivity() {

    private val categories = listOf( //lista contenedora de categorias categorias, y esas categorias
        //para tener un control de ellas se creo una sealder class para que solo hayan las que definamos
        BorradorTaskCaregory.Other,
        BorradorTaskCaregory.Business,
        BorradorTaskCaregory.Personal,
        /*Gracias a esta lista es la cantidad de categorias
        * que tendremos en pantalla*/
    )

    private val task = mutableListOf(
        /*Creando una lista de objetods de tipos task o tareas deben llevar como parametros
        * el nombre y el tipo de categoria*/
        Task("PruebaBussines",BorradorTaskCaregory.Business),
        Task("PruebaPersonal",BorradorTaskCaregory.Personal),
        Task("PruebaOther",BorradorTaskCaregory.Other)
    )

    private lateinit var rvCategorys: RecyclerView
    private lateinit var categorysAdapter: CategoriesAdapter
    private lateinit var rvTask:RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var fabTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_to_do)
        initComponet()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabTask.setOnClickListener { showDialog() }
    }

    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTaks)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rdgCategories)

        btnAddTask.setOnClickListener{
            val currentTask = etTask.text.toString()
            if(currentTask.isNotEmpty()){
                val selectId = rgCategories.checkedRadioButtonId
                val selectedRadioButton:RadioButton = rgCategories.findViewById(selectId)
                val currentCategory:BorradorTaskCaregory =when(selectedRadioButton.text){
                    getString(R.string.todo_category_busines) -> BorradorTaskCaregory.Business
                    getString(R.string.todo_category_personal) ->BorradorTaskCaregory.Personal
                    else ->BorradorTaskCaregory.Other
                }
                task.add(Task(currentTask,currentCategory))
                updateTask()
                dialog.hide()
            }

        }
        dialog.show()
    }

    private fun initComponet() { //funcion para declar los objetos que estan en xml a la activity
        rvCategorys = findViewById(R.id.rvCategoris)
        /* para que un Recycle view funcione
        * consta de dos partes : Adapter y view holder
        * ADAPTER = Es una clase que sirve para enviar informacion al adapter
        * VIEW HOLDER = Es la clase que que le da color */
        rvTask = findViewById(R.id.rvTask)
        fabTask = findViewById(R.id.fabTask)
    }
    private fun initUI() {
        categorysAdapter = CategoriesAdapter(categories){position->upadateCategories(position)}
        rvCategorys.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false )
        rvCategorys.adapter = categorysAdapter

        taskAdapter = TaskAdapter(task){position ->onItemSelected(position)}
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = taskAdapter
    }


    private fun onItemSelected(position: Int){//funcion landa
        task[position].isSelected = !task[position].isSelected
        updateTask()
    }
    private fun upadateCategories(position:Int){
        categories[position].isSelected = !categories[position].isSelected
        categorysAdapter.notifyItemChanged(position)
        updateTask()
    }
    private fun updateTask(){
        val selectedCaregory:List<BorradorTaskCaregory> = categories.filter { it.isSelected }
        val newTask = task.filter { selectedCaregory.contains(it.category) }
        taskAdapter.task = newTask
        taskAdapter.notifyDataSetChanged()

    }
}
