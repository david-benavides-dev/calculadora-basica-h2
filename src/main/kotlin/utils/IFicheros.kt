package org.example.utils

interface IFicheros {
    fun cargarFichero(): Boolean
    fun crearFichero(nombre: String): Boolean
    fun modificarFichero(texto: String): Boolean
    fun comprobarDirectorio(): Boolean
    fun comprobarFichero(): Boolean
    fun mostrarFichero()
}