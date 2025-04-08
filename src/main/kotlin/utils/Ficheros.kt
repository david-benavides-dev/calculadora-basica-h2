package org.example.utils

import java.io.File

class Ficheros(private val ruta: String) : IFicheros {
    private val listaTexto: MutableList<String> = mutableListOf()

    override fun cargarFichero(): Boolean {
        File(ruta).forEachLine { listaTexto.add(it) }
        return true
    }

    override fun mostrarFichero() {
        for (elemento in listaTexto) {
            println(elemento)
        }
    }

    override fun crearFichero(nombre: String): Boolean {
        if (!comprobarFichero()) {
            File(ruta).createNewFile()
        }
        return true
    }

    override fun modificarFichero(texto: String): Boolean {
        File(ruta).appendText(texto + "\n")
        return true
    }

    override fun comprobarDirectorio(): Boolean {
        return File(ruta).exists()
    }

    override fun comprobarFichero(): Boolean {
        return File(ruta).exists()
    }
}