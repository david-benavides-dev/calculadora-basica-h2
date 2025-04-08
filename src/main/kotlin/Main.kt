package org.example

import org.example.app.Menu
import org.example.model.Calculadora
import org.example.service.ServicioCalc
import org.example.ui.Consola

fun main() {
    Menu(ServicioCalc(Calculadora()), Consola()).iniciar()
}