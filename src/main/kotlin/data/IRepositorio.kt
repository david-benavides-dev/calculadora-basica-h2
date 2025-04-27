package org.example.data

import java.time.LocalDateTime

interface IRepositorio {
    fun agregar(fecha: LocalDateTime, mensaje: String): Boolean
    fun obtenerTodos(): List<String>
}