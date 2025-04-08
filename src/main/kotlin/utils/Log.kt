package org.example.utils

import org.example.service.ServicioCalc
import org.example.ui.Consola

// TODO inyectar log a APP en vez de tener los métodos aquí
class Log(val servicioCalc: ServicioCalc, val ficheros: Ficheros) : ILog {



    fun argumentosLoad(argumentos: Array<Any>) {
        when (argumentos.size) {
            /**
             *
             *     Buscar la carpeta ./log.
             *     Si no existe, crearla y mostrar: "Ruta ./log creada"
             *     Si existe y hay archivos, abrir el más reciente (logYYYYMMDDHHMMSS.txt) y mostrar su contenido línea por línea.
             *     Si no existen logs, mostrar: "No existen ficheros de Log"
             *
             */
            0 -> {
                if (!ficheros.cargarFichero()) {
                    ficheros.crearFichero("log")
                    // TODO Thinking
                    Consola().mostrarMensaje("Ruta creada")
                } else {
                    Consola().mostrarMensaje("")
                }
            }
            /**
             *
             *
             * Un argumento:
             *
             *     Se interpreta como la ruta de los logs.
             *     Mismo comportamiento que el caso anterior pero con la ruta proporcionada.
             *
             *
             */
            1 -> {
                println()
            }

            2 -> {
                println()
            }

            3 -> {
                println()
            }
            4 -> {
                println()
            }
        }
    }
}