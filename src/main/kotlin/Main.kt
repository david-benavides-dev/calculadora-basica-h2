package org.example

import org.example.app.Menu
import org.example.model.Calculadora
import org.example.service.ServicioCalc
import org.example.ui.Consola
import org.example.utils.Ficheros
import org.example.utils.Log

fun main() {
    //
    val ruta = ""

    //
    Log(ServicioCalc(Calculadora()), Ficheros(ruta))

    //
    Menu(ServicioCalc(Calculadora()), Consola()).iniciar()
}