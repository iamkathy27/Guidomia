package com.directory.conslist

import java.text.DecimalFormat

/**
 * @author katherine.sobejano on 11/18/2021.
 */
object Utils {
    /**
     * @param price
     *
     * This method is to format the car's market price.
     */
    @JvmStatic
    fun formatPrice(price: Double): String {
        val numValue = price.toLong()
        val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
        val base = value / 3
        return DecimalFormat("#0").format(numValue / Math.pow(10.0, (base * 3).toDouble())) + "k"
    }
}