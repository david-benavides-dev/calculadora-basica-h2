package org.example.utils

import java.io.File

class Ficheros : IFicheros {

    override fun mostrarFichero(ruta: String) {
        val listaTexto = File(ruta).readLines()
        for (elemento in listaTexto) {
            println(elemento)
        }
    }

    override fun crearDirectorio(ruta: String): Boolean {
        if (comprobarDirectorio(ruta)) {
            return File(ruta).mkdirs()
        }
        return false
    }

    override fun crearFichero(ruta: String, nombreFichero: String): Boolean {
        if (!comprobarFichero(ruta, nombreFichero)) {
            File(ruta).createNewFile()
        }
        return true
    }

    override fun modificarFichero(ruta: String, texto: String): Boolean {
        File(ruta).appendText(texto + "\n")
        return true
    }

    override fun comprobarDirectorio(ruta: String): Boolean {
        return File(ruta).exists()
    }

    override fun comprobarFichero(ruta: String, nombreFichero: String): Boolean {
        return File(ruta, nombreFichero).exists()
    }
}