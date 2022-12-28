package com.dst.dailyjournal.core.util

import java.text.SimpleDateFormat
import java.util.*

object DateTools {


    fun dateToString(format: String, date: Date): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        return dateFormatter.format(date)
    }
}