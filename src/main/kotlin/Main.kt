package org.example

import org.example.app.GestorMenu
import org.example.data.RepositorioH2
import org.example.model.Calculadora
import org.example.service.ServicioCalc
import org.example.ui.Consola

fun main(args: Array<String>) {
    GestorMenu(ServicioCalc(Calculadora()), Consola(), RepositorioH2(), args).iniciar()
}