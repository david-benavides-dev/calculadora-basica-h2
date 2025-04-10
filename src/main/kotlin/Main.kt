package org.example

import org.example.app.GestorMenu
import org.example.model.Calculadora
import org.example.service.ServicioCalc
import org.example.ui.Consola
import org.example.utils.Ficheros

fun main(args: Array<String>) {
    GestorMenu(ServicioCalc(Calculadora()), Consola(), Ficheros(), args).iniciar()
}