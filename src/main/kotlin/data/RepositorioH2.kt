package org.example.data

import org.example.utils.Database
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RepositorioH2 : IRepositorio {
    companion object {
        // PATTERN CON EL FORMATO DE LA FECHA
        val FORMATO_FECHA: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    }

    init {
        Database.initDatabase()
    }

    override fun agregar(fecha: LocalDateTime, mensaje: String): Boolean {
        val fechaFormateada = fecha.format(FORMATO_FECHA) // Aquí formatea la fecha

        Database.getConnection().use { conn ->
            val stmt = conn.prepareStatement("INSERT INTO log (fecha, mensaje) VALUES (?, ?)")
            stmt.setString(1, fechaFormateada)
            stmt.setString(2, mensaje)
            stmt.executeUpdate()
        }
        return true
    }

    override fun obtenerTodos(): List<String> {
        val logs = mutableListOf<String>()
        Database.getConnection().use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery("SELECT fecha, mensaje FROM log").use { rs ->
                    while (rs.next()) {
                        val fecha = rs.getString("fecha")
                        val mensaje = rs.getString("mensaje")
                        logs += "$fecha - $mensaje"
                    }
                }
            }
        }
        return logs
    }
}