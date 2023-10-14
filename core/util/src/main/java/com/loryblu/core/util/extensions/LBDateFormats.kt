package com.loryblu.core.util.extensions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toBrazilianDateFormat(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        "dd/MM/yyyy", Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

fun String.toApiFormat() : String {
    val oldFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = LocalDate.parse(this, oldFormat)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return date.format(formatter)
}