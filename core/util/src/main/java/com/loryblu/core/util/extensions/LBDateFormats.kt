package com.loryblu.core.util.extensions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toDateFormat(): String {
    val currentLocale = Locale.getDefault()

    val languagePatterns = mapOf(
        "pt" to "dd/MM/yyyy",
        "en" to "MM/dd/yyyy"
    )

    val pattern = languagePatterns[currentLocale.language] ?: "dd/MM/yyyy"

    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, currentLocale
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

fun Long.toHeadLineDateFormat(): String {
    val currentLocale = Locale.getDefault()

    val languagePatterns = mapOf(
        "pt" to "E, MMM d",
        "en" to "E, MMM d"
    )

    val pattern = languagePatterns[currentLocale.language] ?: "E, MMM d"

    val date = Date(this)
    val formatter = SimpleDateFormat(pattern, currentLocale).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }

    val formattedString = formatter.format(date).replace(".", "")

    return formattedString.split(" ")
        .joinToString(" ") {
            it.replaceFirstChar { char ->
                if (char.isLowerCase()) char.titlecase(currentLocale) else char.toString()
            }
        }
}

fun String.toApiFormat() : String {
    val currentLocale = Locale.getDefault()
    val oldFormat = mapOf(
        "pt" to DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        "en" to DateTimeFormatter.ofPattern("MM/dd/yyyy")
    )
    val date = LocalDate.parse(
        this,
        oldFormat[currentLocale.language]
            ?: DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return date.format(formatter)
}