package com.sauloab.androidborrador.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sauloab.androidborrador.R

class CategoriesViewHolder(view: View):RecyclerView.ViewHolder(view) {//Clase que resive una vista
    /*
    * Okey amigo eso quiere decir que lo que pasa con la vista despues de ser enviado al titular, lo
    * que pasa es que lo resivimos como paramtro, luego se esto se delcaron los componentes a ser
    * personalizados, estos se asignan con los valores de tiene la view que tenemos como parámetro y
    *  luego sugun el tipo de categorías que sea se pesanalizan los componentes de una manera
    * u otra, estoy en lo correcto?*/
    private val tvCategoryName:TextView = view.findViewById(R.id.tvCategoryName) //inicializando el texto donde se cambia el nombre
    private val divider:View = view.findViewById(R.id.divider) //inicializando linea de color para personalizar
    private val viewContainer:CardView = view.findViewById(R.id.viewContainer)

    fun render(borradorTaskCaregory: BorradorTaskCaregory, OnItemSelected: (Int) -> Unit){//clase render donde se clasifica y se personalizan las vistas de categorias

        val color = if(borradorTaskCaregory.isSelected){
            R.color.todo_background_card
        }else{
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))
        itemView.setOnClickListener{OnItemSelected(layoutPosition)}

        when(borradorTaskCaregory){
            BorradorTaskCaregory.Business ->{
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_businnes_category)
                )
            }
            BorradorTaskCaregory.Other ->{
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_other_category)
                )
            }
            BorradorTaskCaregory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )
            }

        }
    }
}