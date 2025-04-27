package org.example.app

import org.example.data.IRepositorio
import org.example.service.ServicioCalc
import org.example.ui.IEntradaSalida
import org.example.utils.Database
import org.example.utils.Utils
import java.time.LocalDateTime

import kotlin.system.exitProcess

/**
 * Clase principal que representa el menú principal de la calculadora que interactúa con
 * el usuario para realizar operaciones matemáticas básicas.
 *
 * @property calculadora Servicio que realiza las operaciones matemáticas.
 * @property consola Interfaz de E/S de datos del usuario.
 * @property args Los parámetros que recibe al iniciarse el programa mediante main.
 */
class GestorMenu(private val calculadora: ServicioCalc, private val consola: IEntradaSalida, private val repositorio: IRepositorio, private val args: Array<String>) {


    /**
     * Inicia el programa y gestiona la lógica de entrada según el número de argumentos proporcionados.
     *
     * Este método realiza las siguientes acciones:
     * - Si no se proporcionan argumentos, utiliza una ruta por defecto y crea un nuevo directorio si no existe.
     * - Si se proporciona un argumento, se utiliza como ruta y se crea un nuevo directorio si no existe.
     * - Si el directorio está vacío, se crea un nuevo fichero de log.
     * - Si hay ficheros de log existentes, se muestra el contenido del fichero más reciente.
     * - Si se proporcionan exactamente cuatro argumentos, se realiza una operación matemática (suma, resta, multiplicación o división)
     *   con los dos primeros argumentos como operandos y el tercer argumento como operador. El resultado se muestra y se registra en el fichero de log.
     * - Si el número de argumentos no es válido, se muestra un mensaje de error y se termina el programa.
     *
     * @throws IllegalArgumentException Si se proporciona un operador no válido para la operación matemática.
     */
    fun iniciar() {
        when(args.size) {
            0 -> {
                menuCalculadora()
            }
            1 -> {}
            4 -> {}
            else -> {
                consola.mostrarMensaje("Saliendo del programa...")
                exitProcess(0)
            }
        }
    }

    /**
     * Inicia el menú de la calculadora, mostrando las opciones y permitiendo
     * al usuario ingresar dos números y un operador para realizar una operación.
     * El resultado de la operación se muestra al usuario, además de guardarse en un archivo log.
     * El proceso se repite hasta que el usuario decida salir.
     * También se manejan las excepciones que puedan ocurrir durante la operación.
     *
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
                repositorio.agregar(LocalDateTime.now(), "**Cálculo $cont** ===>> $num1 $operador $num2 = $resultado")
                cont++
            } catch (e: IllegalArgumentException) {
                consola.mostrarError(e.message.toString())
                repositorio.agregar(LocalDateTime.now(), e.message.toString())
            }
            // Vuelve a preguntar si quiere continuar (s/n)
            opcion = consola.pedirOpcion("¿Desea seguir realizando cálculos? s/n > ")
        }
        repositorio.obtenerTodos().forEach { println(it) }
    }
}