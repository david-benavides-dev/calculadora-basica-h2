package org.example.utils

import java.io.File

/**
 * Clase que implementa la interfaz [IFicheros] y proporciona métodos para gestionar ficheros y directorios.
 */
class Ficheros : IFicheros {

    /**
     * Obtiene el contenido de un fichero y lo devuelve como una lista de líneas.
     *
     * @param ruta La ruta del directorio donde se encuentra el fichero.
     * @param nombreFichero El nombre del fichero del cual se desea obtener el contenido.
     * @return Una lista de cadenas que representan las líneas del fichero.
     */
    override fun obtenerContenidoFichero(ruta: String, nombreFichero: String): List<String> {
        val lineasFichero: MutableList<String> = mutableListOf()
        val listaTexto = File(ruta, nombreFichero).readLines()
        for (elemento in listaTexto) {
            lineasFichero.add(elemento)
        }
        return lineasFichero
    }

    /**
     * Crea un directorio en la ruta especificada si no existe.
     *
     * @param ruta La ruta donde se desea crear el directorio.
     * @return true si se creó el directorio, false si ya existía.
     */
    override fun crearDirectorio(ruta: String): Boolean {
        if (!comprobarDirectorio(ruta)) {
            return File(ruta).mkdirs()
        }
        return false
    }

    /**
     * Crea un fichero en la ruta especificada con el nombre dado.
     *
     * @param ruta La ruta donde se desea crear el fichero.
     * @param nombreFichero El nombre del fichero a crear.
     * @return El nombre del fichero creado, o una cadena vacía si ya existía.
     */
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

    /**
     * Modifica un fichero existente añadiendo texto al final.
     *
     * @param ruta La ruta del directorio donde se encuentra el fichero.
     * @param nombreFichero El nombre del fichero a modificar.
     * @param texto El texto a añadir al fichero.
     * @return true si se modificó el fichero, false si no existía.
     */
    override fun modificarFichero(ruta: String, nombreFichero: String, texto: String): Boolean {
        if (!comprobarFichero(ruta, nombreFichero)) {
            return false
        }
        File(ruta, nombreFichero).appendText(texto + "\n")
        return true
    }

    /**
     * Comprueba si un directorio existe y es un directorio válido.
     *
     * @param ruta La ruta del directorio a comprobar.
     * @return true si el directorio existe, false en caso contrario.
     */
    override fun comprobarDirectorio(ruta: String): Boolean {
        return File(ruta).exists() && File(ruta).isDirectory
    }

    /**
     * Comprueba si un fichero existe y es un fichero válido.
     *
     * @param ruta La ruta del directorio donde se encuentra el fichero.
     * @param nombreFichero El nombre del fichero a comprobar.
     * @return true si el fichero existe, false en caso contrario.
     */
    override fun comprobarFichero(ruta: String, nombreFichero: String): Boolean {
        return File(ruta, nombreFichero).exists() && File(ruta, nombreFichero).isFile
    }

    /**
     * Comprueba si un directorio está vacío.
     *
     * @param ruta La ruta del directorio a comprobar.
     * @return true si el directorio está vacío, false si contiene ficheros.
     */
    override fun esDirectorioVacio(ruta: String): Boolean {
        return File(ruta).listFiles()?.isEmpty() ?: true
    }

    /**
     * Obtiene el nombre del fichero más reciente en un directorio.
     *
     * @param ruta La ruta del directorio donde se busca el fichero.
     * @return El nombre del fichero más reciente, o una cadena vacía si no hay ficheros.
     */
    override fun obtenerFicheroReciente(ruta: String): String {
        return File(ruta).listFiles()?.maxByOrNull { it.lastModified() }?.name.toString()
    }
}