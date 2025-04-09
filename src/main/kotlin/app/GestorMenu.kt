package org.example.app

import org.example.service.ServicioCalc
import org.example.ui.IEntradaSalida
import org.example.utils.Ficheros
import org.example.utils.IFicheros
import org.example.utils.Utils
import kotlin.system.exitProcess

/**
 * Clase que epresenta el menú principal de la calculadora que interactúa con
 * el usuario para realizar operaciones matemáticas básicas.
 *
 * @property calculadora Servicio que realiza las operaciones matemáticas.
 * @property consola Interfaz de E/S de datos del usuario.
 */
class GestorMenu(private val calculadora: ServicioCalc, private val consola: IEntradaSalida, private val fichero: IFicheros, private val args: Array<String>) {
    companion object {
        const val RUTA_DEFECTO = "./log"
    }
    /**
     *
     */
    fun iniciar() {
        when (args.size) {
            0 -> {
                // buscar carpeta
                if (!fichero.comprobarDirectorio(RUTA_DEFECTO)) {
                    fichero.crearFichero(RUTA_DEFECTO)
                    consola.mostrarMensaje("Ruta $RUTA_DEFECTO creada.")
                }
                menuCalculadora()
            }

            1 -> {
                menuCalculadora()
            }

            4 -> {
                menuCalculadora()
            }

            else -> exitProcess(0)
        }
    }


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
    private fun menuCalculadora() {
        var cont = 0
        consola.mostrarMensaje("*** Calculadora ***", salto = true)
        var opcion = consola.pedirOpcion("¿Desea realizar cálculos? s/n > ")

        while (opcion) {
            try {
                val num1 = consola.pedirNumero()
                val operador = consola.pedirOperador()
                val num2 = consola.pedirNumero()

                val resultado = when (operador) {
                    "+" -> calculadora.sumar(num1, num2)
                    "-" -> calculadora.restar(num1, num2)
                    "*" -> calculadora.multiplicar(num1, num2)
                    "/" -> calculadora.dividir(num1, num2)
                    else -> continue
                }
                consola.mostrarMensaje(Utils.redondearNumero(resultado), salto = true)
                fichero.modificarFichero(args[0], "Cálculo num $cont -> $num1 $operador $num2 = $resultado")
                cont++
            } catch (e: IllegalArgumentException) {
                consola.mostrarError(e.message.toString())
                fichero.modificarFichero(args[0], e.message.toString())
            }
            // Vuelve a preguntar si quiere continuar (s/n)
            opcion = consola.pedirOpcion("¿Desea seguir realizando cálculos? s/n > ")
        }
    }
}