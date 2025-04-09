package org.example.utils

interface IFicheros {
    fun crearFichero(ruta: String, nombreFichero: String): Boolean
    fun crearDirectorio(ruta: String): Boolean
    fun modificarFichero(ruta: String, texto: String): Boolean
    fun comprobarDirectorio(ruta: String): Boolean
    fun comprobarFichero(ruta: String, nombreFichero: String): Boolean
    fun mostrarFichero(ruta: String)
}