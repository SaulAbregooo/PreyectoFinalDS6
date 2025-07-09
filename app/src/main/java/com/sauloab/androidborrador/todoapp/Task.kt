package com.sauloab.androidborrador.todoapp

data class Task(val name:String, val category:BorradorTaskCaregory,var isSelected:Boolean = false) {
}