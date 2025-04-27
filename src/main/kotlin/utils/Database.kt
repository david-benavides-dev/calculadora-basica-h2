package org.example.utils

import java.sql.*

object Database {
    private const val DBURL = "jdbc:h2:file:./data/log"
    private const val USUARIO = "sa"
    private const val PWD = ""

    fun getConnection(): Connection {
        return DriverManager.getConnection(DBURL, USUARIO, PWD)
    }

    fun initDatabase() {
        getConnection().use { conn ->
            val stmt: Statement = conn.createStatement()
            stmt.executeUpdate(
                """
                CREATE TABLE IF NOT EXISTS log (
                    fecha VARCHAR(14) NOT NULL,
                    mensaje VARCHAR(255) NOT NULL
                );
            """.trimIndent()
            )
        }
    }
}