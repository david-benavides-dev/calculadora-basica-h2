package org.example.utils

import java.io.File

class Ficheros : IFicheros {
    override fun obtenerContenidoFichero(ruta: String, nombreFichero: String): List<String> {
        val lineasFichero: MutableList<String> = mutableListOf()
        val listaTexto = File(ruta, nombreFichero).readLines()
        for (elemento in listaTexto) {
            lineasFichero.add(elemento)
        }
        return lineasFichero
    }

    override fun crearDirectorio(ruta: String): Boolean {
        if (!comprobarDirectorio(ruta)) {
            return File(ruta).mkdirs()
        }
        return false
    }

    override fun crearFichero(ruta: String, nombreFichero: String): String {
        if (!comprobarDirectorio(ruta)) {
            crearDirectorio(ruta)
        }

        if (!comprobarFichero(ruta, nombreFichero)) {
            File(ruta, nombreFichero).createNewFile()
            return nombreFichero
        }
        return ""
    }

    override fun modificarFichero(ruta: String, nombreFichero: String, texto: String): Boolean {
        if (!comprobarFichero(ruta, nombreFichero)) {
            return false
        }
        File(ruta, nombreFichero).appendText(texto + "\n")
        return true
    }

    override fun comprobarDirectorio(ruta: String): Boolean {
        return File(ruta).exists() && File(ruta).isDirectory
    }

    override fun comprobarFichero(ruta: String, nombreFichero: String): Boolean {
        return File(ruta, nombreFichero).exists() && File(ruta, nombreFichero).isFile
    }

    override fun esDirectorioVacio(ruta: String): Boolean {
        return File(ruta).listFiles()?.isEmpty() ?: true
    }

    override fun obtenerFicheroReciente(ruta: String): String {
        return File(ruta).listFiles()?.maxByOrNull { it.lastModified() }?.name.toString()
    }
}