package org.example.utils

object Utils {

    /**
     * Redondea un número a una cantidad específica de decimales y lo devuelve como string.
     *
     * @param numero El número a redondear.
     * @param decimales El número de decimales a los que se debe redondear el número. Por defecto 2.
     * @return El número redondeado como una cadena con el formato especificado.
     */
    fun redondearNumero(numero: Double, decimales: Int = 2): String {
        return String.format("%.${decimales}f", numero)
    }
}