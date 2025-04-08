package org.example.app

import org.example.service.ServicioCalc
import org.example.ui.IEntradaSalida
import org.example.utils.Utils

/**
 * Clase que epresenta el menú principal de la calculadora que interactúa con
 * el usuario para realizar operaciones matemáticas básicas.
 *
 * @property calculadora Servicio que realiza las operaciones matemáticas.
 * @property consola Interfaz de E/S de datos del usuario.
 */
class Menu(private val calculadora: ServicioCalc, private val consola: IEntradaSalida) {

    /**
     * Inicia el menú de la calculadora, mostrando las opciones y permitiendo
     * al usuario ingresar dos números y un operador para realizar una operación.
     * El resultado de la operación se muestra al usuario, y el proceso se repite
     * hasta que el usuario decida salir.
     *
     * Interactúa con la consola, solicita los números y el operador,
     * y ejecuta la operación correspondiente a través del servicio `calculadora`.
     * Además, maneja las excepciones que puedan ocurrir durante la operación.
     */
    fun iniciar() {
        consola.mostrarMensaje("*** Calculadora ***", salto = true)
        var opcion = consola.pedirOpcion("¿Desea realizar cálculos? s/n > ")

        while (opcion) {
            try {
                val num1 = consola.pedirNumero()
                val num2 = consola.pedirNumero()
                val operador = consola.pedirOperador()
                val resultado = when (operador) {
                    "+" -> calculadora.sumar(num1, num2)
                    "-" -> calculadora.restar(num1, num2)
                    "*" -> calculadora.multiplicar(num1, num2)
                    "/" -> calculadora.dividir(num1, num2)
                    else -> continue
                }
                consola.mostrarMensaje(Utils.redondearNumero(resultado), salto = true)
            } catch (e: IllegalArgumentException) {
                consola.mostrarError(e.message.toString())
            }
            // Vuelve a preguntar si quiere continuar (s/n)
            opcion = consola.pedirOpcion("¿Desea seguir realizando cálculos? s/n > ")
        }
    }
}