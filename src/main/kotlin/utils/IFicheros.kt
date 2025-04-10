package org.example.utils

interface IFicheros {
    fun crearFichero(ruta: String, nombreFichero: String): String
    fun crearDirectorio(ruta: String): Boolean
    fun modificarFichero(ruta: String, nombreFichero: String, texto: String): Boolean
    fun comprobarDirectorio(ruta: String): Boolean
    fun esDirectorioVacio(ruta: String): Boolean
    fun obtenerFicheroReciente(ruta: String): String?
    fun comprobarFichero(ruta: String, nombreFichero: String): Boolean
    fun obtenerContenidoFichero(ruta: String, nombreFichero: String): List<String>
}