package com.sauloab.androidborrador.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sauloab.androidborrador.R

class CategoriesAdapter(private val categories:List<BorradorTaskCaregory>,private val OnItemSelected:(Int)->Unit): // Es una clase que resive mi lista de categorias
    RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
        /*función para crear una nueva  vista, donde toma un xlm y la convierte
        en una view y se la envia al Holder donde se personaliza de manera controlada
        ya que se sabe los tipos y cantidad de categorias
         */
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        /*
        Funcion para la clasificacion y manejo de las categorias, dependiendo del tipo se pasa al
        funcion render que ahi personaliza o se reliza acciones en un When sobre el xml que ya es
        una vista lista para ser personalizada
         */
        holder.render(categories[position], OnItemSelected)
    }

    override fun getItemCount(): Int = categories.size
    /*
    Se usa para medir el tamaño de mi lista de categorias esta menera tendra un control de la veces
    a hacer los los llamados de para la creación, clasificacion de las categorias como un RecyclerView
    que ah sido trasformado a una vista
     */
}