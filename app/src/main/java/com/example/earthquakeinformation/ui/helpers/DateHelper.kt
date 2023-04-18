package com.example.earthquakeinformation.ui.helpers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.setFormat(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}