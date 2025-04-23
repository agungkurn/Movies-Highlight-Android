package id.ak.movieshighlight.core.ext

import java.text.NumberFormat
import java.util.Locale

fun Number.formatLocalNumber(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 1
    }
    return formatter.format(this)
}

fun Number.formatCurrency(locale: Locale=Locale.getDefault()): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(this)
}