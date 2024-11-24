package com.cravyn.app.data.api

import android.icu.text.NumberFormat
import java.io.Serializable
import java.util.Locale

data class DisplayableNumber(
    val value: Double,
    val formatted: String
) : Serializable

fun Double.toDisplayableNumber(digitsAfterDecimal: Int = 2): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = digitsAfterDecimal
        maximumFractionDigits = digitsAfterDecimal
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}
