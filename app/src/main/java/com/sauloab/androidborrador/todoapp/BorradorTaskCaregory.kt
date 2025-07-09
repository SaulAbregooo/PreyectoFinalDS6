package com.sauloab.androidborrador.todoapp

sealed class BorradorTaskCaregory(var isSelected:Boolean = true) { // clase que contiene las categorias para mantener un orden
    object Personal: BorradorTaskCaregory()
    object Business:BorradorTaskCaregory()
    object Other: BorradorTaskCaregory()
    /*¿Porque?
    * Bueno basicamente esta es una clase sellada eso quiere decir
    * que no podems crear instancias de de esta clase desde otra,
    * pero si podemos utilizar los objetos ya creados dentro de la clase
    * para ser llamados desde otras clases, esto nos permite tenr un control de los
    * objetos por lo tanto nos dan la posiblidadd de utilizar el When, ya que
    * no habran casos que no hayamos controlado previamente*/
}
