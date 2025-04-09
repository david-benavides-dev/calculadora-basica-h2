package org.example

import org.example.app.GestorMenu
import org.example.model.Calculadora
import org.example.service.ServicioCalc
import org.example.ui.Consola
import org.example.utils.Ficheros

fun main(args: Array<String>) {
//    // Pruebas launching con parametros
//    when (args.size) {
//        0 -> {
//            println("ARG0")
//        }
//
//        1 -> {
//            println("ARG1")
//        }
//
//        4 -> {
//            println("ARG4")
//        }
//
//        else -> {
//            println("Saliendo del programa.")
//            return
//        }
//    }
//
        GestorMenu(ServicioCalc(Calculadora()), Consola(), Ficheros("src/main/kotlin/res/log.txt")).iniciar()

}